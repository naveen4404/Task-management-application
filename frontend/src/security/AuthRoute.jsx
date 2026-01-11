import { Navigate } from "react-router-dom";
import { useAuth } from "./AuthContextCreate";

function AuthRoute({ children }) {
  const authContext = useAuth();
  // console.log(authContext);
  // console.log(authContext.authenticated);
  if (authContext.authenticated) {
    return children;
  } else {
    return <Navigate to="/login" />;
  }
}

export default AuthRoute;
