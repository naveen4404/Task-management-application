import { useParams, Link } from "react-router-dom";

function Welcome() {
  const { username } = useParams();
  return (
    <div>
      <h1>Welcome {username.split("@")[0].toUpperCase()} </h1>
      <Link to="/todos">Todos</Link>
    </div>
  );
}

export default Welcome;
