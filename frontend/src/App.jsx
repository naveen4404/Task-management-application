import { BrowserRouter, Routes, Route } from "react-router-dom";
import LoginForm from "./components/LoginForm";
import Welcome from "./components/Welcome";
import ErrorPage from "./components/ErrorPage";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Todos from "./components/Todos";
import LogOutPage from "./components/LogoutPage";
import AuthProvider from "./security/AuthContext";
import AuthRoute from "./security/AuthRoute";
import "./App.css";
import TodoComponent from "./components/UpdateComponent";
import AddComponent from "./components/AddComponent";
import { Toaster } from "react-hot-toast";
import SignUp from "./components/SignUp";

function App() {
  return (
    <>
      <div className="container">
        <AuthProvider>
          <BrowserRouter>
            <Toaster />
            <Header />
            <Routes>
              <Route path="/" element={<LoginForm />} />
              <Route path="/login" element={<LoginForm />} />
              <Route path="/signup" element={<SignUp />} />

              <Route
                path="/welcome/:username"
                element={
                  <AuthRoute>
                    <Welcome />
                  </AuthRoute>
                }
              />

              <Route
                path="/todos"
                element={
                  <AuthRoute>
                    <Todos />
                  </AuthRoute>
                }
              />

              <Route
                path="/logout"
                element={
                  <AuthRoute>
                    <LogOutPage />
                  </AuthRoute>
                }
              />
              <Route
                path="/update/:id"
                element={
                  <AuthRoute>
                    <TodoComponent />
                  </AuthRoute>
                }
              />

              <Route
                path="/add"
                element={
                  <AuthRoute>
                    <AddComponent />
                  </AuthRoute>
                }
              />
              <Route path="*" element={<ErrorPage />} />
            </Routes>
            {/* <Footer /> */}
          </BrowserRouter>
        </AuthProvider>
      </div>
    </>
  );
}

export default App;
