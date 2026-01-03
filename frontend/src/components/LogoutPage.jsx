import { AuthContext, useAuth } from "../security/AuthContextCreate";
function LogOutPage() {
  const authContext = useAuth();
  return (
    <>
      {authContext.number}
      <h1 className="container">Logged out successfully!!!</h1>
    </>
  );
}

export default LogOutPage;
