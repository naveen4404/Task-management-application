import TodoComponent from "./UpdateComponent";
import apiClient from "../api/apiClient";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../security/AuthContextCreate";
import toast from "react-hot-toast";
export default function AddComponent() {
  const [todo, setTodo] = useState({
    description: "",
    targetDate: "",
    done: false,
  });
  const [errMsg, setErrMsg] = useState("");
  const navigate = useNavigate();
  const userEmail = useAuth().userEmail;
  const handleDescription = (e) => {
    setTodo({ ...todo, description: e.target.value });
  };

  const handleDate = (e) => {
    setTodo({ ...todo, targetDate: e.target.value });
  };

  // update a todo
  const handleSubmit = (e) => {
    e.preventDefault();
    const addTodo = async function () {
      try {
        await apiClient.post("api/v1/todos", todo, {
          headers: {
            Useremail: userEmail,
          },
        });
        toast.success("Your todo is added!");
        navigate("/todos");
      } catch (err) {
        // console.log(err);
        setErrMsg(err.response.data.message);
      }
    };
    addTodo();
  };
  return (
    <div className="container">
      <h3 style={{ textDecoration: "underline" }}>Add here</h3>
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
          style={{ width: 360 }}
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
          value={todo.targetDate}
          className="form-control form-control-sm"
          style={{ width: 360 }}
          onChange={handleDate}
        />
        <br />
        <hr />
        <input
          type="submit"
          value="Add"
          className="btn btn-success"
          onSubmit={handleSubmit}
        />
      </form>
    </div>
  );
}
