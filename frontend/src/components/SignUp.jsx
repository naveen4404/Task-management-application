import { useState } from "react";
import { useNavigate } from "react-router-dom";
import apiClient from "../api/apiClient";
import toast from "react-hot-toast";

export default function SignUp() {
  const [error, setError] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [disable, setDisable] = useState(false);
  const navigate = useNavigate();
  function handleEmail(e) {
    setEmail(e.target.value);
  }
  function handlePassword(e) {
    setPassword(e.target.value);
  }

  function handleConfirmPassword(e) {
    setConfirmPassword(e.target.value);
  }
  function handleSignUp(e) {
    e.preventDefault();
    setDisable(true);
    if (!email || !password || !confirmPassword) {
      setError("Please enter valid details!");
      setDisable(false);
      return;
    }
    if (password !== confirmPassword) {
      setError("Passwords did not match!");
      setDisable(false);
      return;
    }

    const getSignedIn = async function () {
      try {
        await apiClient.post("/api/v1/auth/register", {
          email,
          password,
        });
        toast.success("Signed up successfully!");
        navigate("/login");
      } catch (err) {
        setError(err.response.data.message || "Sign up failed!");
        setDisable(false);
      }
    };
    getSignedIn();
  }
  return (
    <form
      className="login-component container"
      onSubmit={handleSignUp}
      method="post"
    >
      <h3 style={{ textDecoration: "underline" }}>Sign up here</h3>
      {error && <div className="error-message">{error}</div>}
      <label htmlFor="email" style={{ fontSize: 20, fontWeight: "bold" }}>
        Email:
      </label>
      <br />
      <input
        type="email"
        name="email"
        className="form-control form-control-sm"
        id="email"
        placeholder="Enter email..."
        value={email}
        onChange={handleEmail}
      />
      <br />
      <label
        htmlFor="password"
        className="form-label"
        style={{ fontSize: 20, fontWeight: "bold" }}
      >
        Password:
      </label>
      <br />
      <input
        type="password"
        className="form-control form-control-sm"
        name="password"
        id="password"
        placeholder="Enter password (at least 5 characters)"
        value={password}
        onChange={handlePassword}
      />
      <br />
      <label
        htmlFor="confirmPassword"
        className="form-label"
        style={{ fontSize: 20, fontWeight: "bold" }}
      >
        Confirm Password:
      </label>
      <br />
      <input
        type="password"
        className="form-control form-control-sm"
        name="confirmPassword"
        id="confirmPassword"
        placeholder="confirm password"
        value={confirmPassword}
        onChange={handleConfirmPassword}
      />
      <br />
      <input
        type="submit"
        className="btn btn-success mt-2"
        value="Sign up"
        disabled={disable}
        onSubmit={handleSignUp}
      />
    </form>
  );
}
