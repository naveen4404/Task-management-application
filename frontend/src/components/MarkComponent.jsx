import { useState } from "react";
import apiClient from "../api/apiClient";

export default function MarkComponent({
  id,
  setMessage,
  status,
  refreshTodos,
}) {
  const [done, setDone] = useState(status);
  // Handling Marking
  const handleMark = (e) => {
    const newValue = e.target.checked;
    setDone(newValue);
    // console.log(e.target.checked);
    // console.log(done);
    const markTodo = async function () {
      try {
        await apiClient.put(`/api/v1/todos/mark/${id}`, { done: newValue });
        refreshTodos();
      } catch (err) {
        setMessage(err.response.data.message || "Something went wrong!");
      }
    };

    markTodo();
  };
  return (
    <input
      type="checkbox"
      name="check-box"
      id="check-box"
      checked={done}
      onChange={handleMark}
    />
  );
}
