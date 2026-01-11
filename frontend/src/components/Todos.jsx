import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import apiClient from "../api/apiClient";
import MarkComponent from "./MarkComponent";

function Todos() {
  const [todos, setTodos] = useState(null);
  const [isDeleted, setIsDeleted] = useState(false);
  const [message, setMessage] = useState(null);

  const navigate = useNavigate();

  // Refresh to-dos
  const refreshTodos = () => {
    apiClient
      .get("/api/v1/todos")
      .then((res) => setTodos(res.data))
      .catch(() => {
        navigate("/login");
      });
  };

  useEffect(() => {
    refreshTodos();
  }, []);

  // Delete todo
  const handleDelete = (id) => {
    apiClient
      .delete(`/api/v1/todos/${id}`)
      .then(() => {
        refreshTodos();
        setIsDeleted(true);
        setMessage(`Todo is deleted.`);
        setTimeout(() => {
          setMessage(null);
        }, 2000);
      })
      .catch((err) => {
        setIsDeleted(false);
        setMessage(err.data.message);
      });
  };

  // Handling update
  const handleUpdate = (id) => {
    navigate(`/update/${id}`);
  };

  return (
    <div>
      {!todos ? (
        <h1 className="text-center loading">Loading...</h1>
      ) : (
        <div className="container">
          <h1>Things I have to do...</h1>
          {todos.length == 0 && (
            <p className="container">Start making to-dos!</p>
          )}
          {isDeleted && message && (
            <p className="alert alert-warning container">{message}</p>
          )}

          {/* Responsive table wrapper for mobile horizontal scrolling */}
          <div className="table-responsive">
            <table className="table container">
              <thead>
                <tr>
                  <th>Mark as completed</th>
                  <th>Id</th>
                  <th>Description</th>
                  <th>Status</th>
                  <th>Target date</th>
                  <th>Delete</th>
                  <th>Update</th>
                </tr>
              </thead>
              <tbody>
                {todos.map((todo, i) => {
                  return (
                    <tr
                      key={todo.id}
                      style={{
                        textDecoration: todo.done ? "line-through" : "none",
                        color: todo.done ? "#666" : "#000",
                      }}
                    >
                      <td>
                        <MarkComponent
                          id={todo.id}
                          setMessage={setMessage}
                          status={todo.done}
                          refreshTodos={refreshTodos}
                        />
                      </td>
                      <td>{i + 1}</td>
                      <td>{todo.description}</td>
                      <td>{`${todo.done ? "Completed" : "Pending"}`}</td>
                      <td>{todo.targetDate}</td>
                      <td>
                        <button
                          type="button"
                          className="btn btn-warning"
                          onClick={() => {
                            handleDelete(todo.id);
                          }}
                        >
                          Delete
                        </button>
                      </td>
                      <td>
                        <button
                          type="button"
                          className="btn btn-success"
                          onClick={() => {
                            handleUpdate(todo.id);
                          }}
                        >
                          Update
                        </button>
                      </td>
                    </tr>
                  );
                })}
              </tbody>
            </table>
          </div>

          <div className="mt-3">
            <button
              className="btn btn-success"
              type="button"
              onClick={() => {
                navigate("/add");
              }}
            >
              Add Todo
            </button>
          </div>
        </div>
      )}
    </div>
  );
}

export default Todos;
