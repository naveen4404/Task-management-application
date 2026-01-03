import { Link } from "react-router-dom";
import { useAuth } from "../security/AuthContextCreate";

function Header() {
  const authContext = useAuth();

  function handleLogOut() {
    authContext.logout();
  }
  return (
    <header className="border-bottom border-light border-5 mb-5 p-2">
      <div className="container">
        <div className="row">
          <nav className="navbar navbar-expand-lg">
            <Link
              className="navbar-brand ms-2 fs-2 fw-bold text-black"
              to={`/welcome/${authContext.userEmail}`}
            >
              ODOT
            </Link>
            <div className="collapse navbar-collapse">
              <ul className="navbar-nav">
                <li className="nav-item fs-5">
                  {authContext.authenticated && (
                    <Link
                      className="nav-link"
                      to={`/welcome/${authContext.userEmail}`}
                    >
                      Home
                    </Link>
                  )}
                </li>
                <li className="nav-item fs-5">
                  {authContext.authenticated && (
                    <Link className="nav-link" to="/todos">
                      Todos
                    </Link>
                  )}
                </li>
              </ul>
            </div>
            <ul className="navbar-nav">
              <li className="nav-item fs-5">
                {!authContext.authenticated && (
                  <Link className="nav-link" to="/signup">
                    Signup
                  </Link>
                )}
              </li>
              <li className="nav-item fs-5">
                {!authContext.authenticated && (
                  <Link className="nav-link" to="/login">
                    Login
                  </Link>
                )}
              </li>
              <li className="nav-item fs-5">
                {authContext.authenticated && (
                  <Link
                    className="nav-link"
                    to="/logout"
                    onClick={handleLogOut}
                  >
                    Logout
                  </Link>
                )}
              </li>
            </ul>
          </nav>
        </div>
      </div>
    </header>
  );
}

export default Header;
