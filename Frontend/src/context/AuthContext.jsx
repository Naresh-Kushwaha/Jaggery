
import { jwtDecode } from "jwt-decode";
import { createContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const navigate = useNavigate();
  const [userDetails, setUserDetails] = useState(null);

  // 🔑 Restore userDetails on page load from localStorage
  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      try {
        const decoded =jwtDecode(token);
       
        localStorage.setItem("userDetails",JSON.stringify(decoded))
          
      } catch (e) {
        console.error("Failed to decode token:", e);
        // Optionally remove invalid token
        localStorage.removeItem("token");
      }
    }
  }, []);

  const login = async (token) => {
    localStorage.setItem("token", token);
   const decoded =jwtDecode(token); 
  localStorage.setItem("userDetails",JSON.stringify(decoded))      
  };

  const logout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("userDetails");
    navigate("/login");
    setUserDetails(null);
  };

  return (
    <AuthContext.Provider value={{ userDetails, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};
