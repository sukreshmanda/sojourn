import axios from "axios";
import React, { useCallback, useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../App";

function LoginComponent() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigation = useNavigate();
  const { isAuthenticated, setToken, setIsAuthenticated } =
    useContext(AuthContext);

  const handleUsernameChange = useCallback((event) => {
    setUsername(event.target.value);
  }, []);

  const handlePasswordChange = useCallback((event) => {
    setPassword(event.target.value);
  }, []);

  const loginAction = useCallback(() => {
    axios
      .post(`${BACK_END_URL}/user/authenticate`, {
        username: username,
        password: password,
      })
      .then((res) => {
        setIsAuthenticated(true);
        setToken(res.data["token"]);
      })
      .catch((err) => {
        console.log(err);
      });
  }, [username, password, setIsAuthenticated, setToken]);

  const handleSubmit = useCallback(
    (event) => {
      event.preventDefault();
      loginAction(username, password);
    },
    [username, password, loginAction]
  );

  if (isAuthenticated) navigation("/");

  return (
    <form onSubmit={handleSubmit} className="login-form">
      <input
        type="text"
        placeholder="username"
        onChange={handleUsernameChange}
      />
      <input
        type="password"
        placeholder="password"
        onChange={handlePasswordChange}
      />
      <button type="submit">Login</button>
    </form>
  );
}

export default LoginComponent;
