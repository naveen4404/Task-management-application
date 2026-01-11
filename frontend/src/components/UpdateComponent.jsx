import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import apiClient from "../api/apiClient";
export default function TodoComponent() {
  const [todo, setTodo] = useState({
    description: "",
    targetDate: "",
  });
  const [errMsg, setErrMsg] = useState("");

  const { id } = useParams();
  const navigate = useNavigate();
  // get todo details by id
  useEffect(() => {
    const fetchTodo = async function () {
      try {
        const response = await apiClient.get(`api/v1/todos/${id}`);
        setTodo(response.data);
      } catch (err) {
        // console.log(err);
        setErrMsg(err.response.data.message);
      }
    };
    fetchTodo();
  }, [id]);

  const handleDescription = (e) => {
    setTodo({ ...todo, description: e.target.value });
  };

  const handleDate = (e) => {
    setTodo({ ...todo, targetDate: e.target.value });
  };

  // update a todo
  const handleSubmit = (e) => {
    e.preventDefault();
    const updateTodo = async function () {
      try {
        console.log(todo);
        await apiClient.put(`api/v1/todos/${id}`, todo);
        navigate("/todos");
      } catch (err) {
        // console.log(err);
        setErrMsg(err.response.data.message);
      }
    };
    updateTodo();
  };

  return (
    <div className="container">
      <h3 style={{ textDecoration: "underline" }}>Update here</h3>
      {errMsg && <p style={{ color: "red" }}>{errMsg}</p>}
      <form action="" onSubmit={handleSubmit}>
        <label
          htmlFor="description"
          style={{ fontSize: 20, fontWeight: "bold" }}
        >
          Description
        </label>
        <br />
        <input
          type="text"
          name="description"
          id="description"
          value={todo.description}
          className="form-control form-control-sm"
          onChange={handleDescription}
        />
        <br />
        <label
          htmlFor="target-date"
          style={{ fontSize: 20, fontWeight: "bold" }}
        >
          Target date
        </label>
        <br />
        <input
          type="date"
          name="target-date"
          id="target-date"
          className="form-control form-control-sm"
          value={todo.targetDate}
          onChange={handleDate}
        />
        <br />
        <hr />
        <input
          type="submit"
          value="Update"
          className="btn btn-success"
          onSubmit={handleSubmit}
        />
      </form>
    </div>
  );
}
