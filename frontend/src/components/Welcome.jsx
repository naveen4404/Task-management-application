import { Link } from "react-router-dom";

import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import apiClient from "../api/apiClient";

function Welcome() {
  const [username, setUsername] = useState("");
  const navigate = useNavigate();
  useEffect(() => {
    async function getuser() {
      try {
        const response = await apiClient.get("/api/v1/auth/me");
        // console.log(response);
        setUsername(response.data.email);
      } catch {
        navigate("/login");
      }
    }
    getuser();
  }, []);
  return (
    <div>
      {!username ? (
        <h1 className="text-center loading">Loading...</h1>
      ) : (
        <div className="container text-center p-3">
          <h1>Welcome {username.split("@")[0].toUpperCase()}!</h1>
          <div className="mt-4">
            <Link to="/todos" className="btn btn-primary btn-lg">
              View Todos
            </Link>
          </div>
        </div>
      )}
    </div>
  );
}

export default Welcome;
