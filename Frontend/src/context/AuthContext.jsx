import Cookies from "js-cookie";
import { jwtDecode } from "jwt-decode";
import { createContext, useEffect, useState } from "react";
import { Navigate, useNavigate } from "react-router-dom";
export const AuthContext = createContext();
export const AuthProvider=({children})=>{
    const navigate=useNavigate();
    const[user,setUser]=useState(null);
    useEffect(()=>{
        const token=localStorage.getItem("token");
        if(token){
            try{
                const decode=jwtDecode(token);
                setUser(decode);
            }
            catch(error){
                console.log("Invalid token",error);  
                setUser(null);
            }
        }
    },[]);
    const login=(token)=>{
        Cookies.set("token", token, { expires: 7 }); // Store token in cookies for 7 days
        const decode=jwtDecode(token);
        
        setUser(decode);  
    };
    const logout=()=>{
       Cookies.remove("token");
    navigate("/login");
        setUser(null);

    };
    return(
        <AuthContext.Provider value={{user,login,logout}}>
            {children}
        </AuthContext.Provider>
    )
}