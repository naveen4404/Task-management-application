import { Link } from "react-router-dom";
import { useAuth } from "../security/AuthContextCreate";
import { useState } from "react";

function Header() {
  const authContext = useAuth();
  const [isNavCollapsed, setIsNavCollapsed] = useState(true);

  function handleLogOut() {
    authContext.logout();
  }

  function handleNavCollapse() {
    setIsNavCollapsed(!isNavCollapsed);
  }

  return (
    <header className="border-bottom border-light border-5 mb-5 p-2">
      <div className="container">
        <div className="row">
          <nav className="navbar navbar-expand-lg">
            <Link
              className="navbar-brand ms-2 fs-2 fw-bold text-black"
              to={`/`}
            >
              ODOT
            </Link>

            {/* Hamburger menu toggle for mobile */}
            <button
              className="navbar-toggler"
              type="button"
              onClick={handleNavCollapse}
              aria-controls="navbarNav"
              aria-expanded={!isNavCollapsed}
              aria-label="Toggle navigation"
            >
              <span className="navbar-toggler-icon"></span>
            </button>

            <div className={`${isNavCollapsed ? 'collapse' : ''} navbar-collapse`} id="navbarNav">
              <ul className="navbar-nav">
                <li className="nav-item fs-5">
                  {authContext.authenticated && (
                    <Link className="nav-link" to={`/`} onClick={handleNavCollapse}>
                      Home
                    </Link>
                  )}
                </li>
                <li className="nav-item fs-5">
                  {authContext.authenticated && (
                    <Link className="nav-link" to="/todos" onClick={handleNavCollapse}>
                      Todos
                    </Link>
                  )}
                </li>
              </ul>
              <ul className="navbar-nav ms-auto">
                <li className="nav-item fs-5">
                  {!authContext.authenticated && (
                    <Link className="nav-link" to="/signup" onClick={handleNavCollapse}>
                      Signup
                    </Link>
                  )}
                </li>
                <li className="nav-item fs-5">
                  {!authContext.authenticated && (
                    <Link className="nav-link" to="/login" onClick={handleNavCollapse}>
                      Login
                    </Link>
                  )}
                </li>
                <li className="nav-item fs-5">
                  {authContext.authenticated && (
                    <Link
                      className="nav-link"
                      to="/logout"
                      onClick={() => {
                        handleLogOut();
                        handleNavCollapse();
                      }}
                    >
                      Logout
                    </Link>
                  )}
                </li>
              </ul>
            </div>
          </nav>
        </div>
      </div>
    </header>
  );
}

export default Header;
