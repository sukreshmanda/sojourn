import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import HomeComponent from "./components/HomeComponent";
import DataComponent from "./components/DataComponent";
import TopPanel from "./components/TopPanel";
import AddData from "./components/AddData";
import { createContext, useState } from "react";
import LoginComponent from "./components/LoginComponent";

export const AuthContext = createContext();

function App() {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [token, setToken] = useState("");
  return (
    <AuthContext.Provider
      value={{ isAuthenticated, token, setToken, setIsAuthenticated }}
    >
      <div className="App">
        <BrowserRouter>
          {isAuthenticated ? (
            <div>
              <TopPanel />
              <Routes>
                <Route path="/" element={<HomeComponent />} />
                <Route path="/home" element={<HomeComponent />} />
                <Route path="/data" element={<DataComponent />} />
                <Route path="/add-data" element={<AddData />} />
              </Routes>
            </div>
          ) : (
            <Routes>
              <Route path="*" element={<LoginComponent />} />
            </Routes>
          )}
        </BrowserRouter>
      </div>
    </AuthContext.Provider>
  );
}

export default App;
