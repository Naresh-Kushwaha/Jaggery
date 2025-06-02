
import { jwtDecode } from "jwt-decode";
import { createContext, useEffect, useState } from "react";

export const AuthContext = createContext();
export const AuthProvider=({children})=>{
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
        localStorage.setItem("token",token);
        const decode=jwtDecode(token);
        setUser(decode);  
    };
    const logout=()=>{
        localStorage.removeItem("token");
        setUser(null);
    };
    return(
        <AuthContext.Provider value={{user,login,logout}}>
            {children}
        </AuthContext.Provider>
    )
}