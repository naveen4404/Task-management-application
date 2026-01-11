import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../security/AuthContextCreate";
import toast from "react-hot-toast";

function LoginForm() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [disable, setDisable] = useState(false);
  const [error, setError] = useState(false);
  const navigate = useNavigate();
  const authContext = useAuth();

  function handleEmail(e) {
    setEmail(e.target.value);
  }

  function handlePassword(e) {
    setPassword(e.target.value);
  }

  async function handleAuth(e) {
    e.preventDefault();
    setDisable(true);
    if (!email || !password) {
      setError(true);
      setDisable(false);
      return;
    }
    const success = await authContext.login(email, password);
    if (success) {
      toast.success("Successfully Logged in...");
      setError(false);
      navigate(`/welcome`);
    } else {
      setError(true);
      setDisable(false);
    }
  }

  return (
    <form
      className="login-component container"
      onSubmit={handleAuth}
      method="post"
    >
      <h3 style={{ textDecoration: "underline" }}>Log in here</h3>
      {error && (
        <div className="error-message">
          Authentication failed. Please check the credentials!!
        </div>
      )}
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
        placeholder="Enter password"
        value={password}
        onChange={handlePassword}
      />
      <br />
      <input
        type="submit"
        className="btn btn-success mt-2"
        value="Log in"
        disabled={disable}
        onSubmit={handleAuth}
      />
    </form>
  );
}

export default LoginForm;
