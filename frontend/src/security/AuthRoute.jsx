import { Navigate } from "react-router-dom";
import { useAuth } from "./AuthContextCreate";

function AuthRoute({ children }) {
  const authContext = useAuth();

  if (authContext.authenticated) {
    return children;
  } else {
    return <Navigate to="/login" />;
  }
}

export default AuthRoute;
