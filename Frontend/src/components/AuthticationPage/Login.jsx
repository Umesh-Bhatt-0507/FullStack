import {useState} from "react";
import React from "react";
import './Login.css'
import { useNavigate } from "react-router-dom";

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate=useNavigate();

    const handleLogin =()=>{
        let role="";
        if (username==="admin" && password==="1234") {
            role="admin";

        } 
        else if(username==="editor" && password==="1234") {
            role="editor";
        } 
        else if (username==="viewer" && password==="1234") {
            role= "viewer";
        } 
        else {
            alert("Invalid credentials");
            return;
        }

        localStorage.setItem("role", role);
        console.log("Saved role:", role);
        navigate("/landing")
    };

    return (
        <div className="login-container">
            <h1>Login</h1>
            <input
                placeholder="Username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
            />
            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />
            <button onClick={handleLogin}>
                Login
            </button>
        </div>
    );
}

export default Login;