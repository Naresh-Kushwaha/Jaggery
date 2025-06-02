import { useState } from "react";

export default function Register(){
    const [email,setEmail]=useState("");
    const [password,setPassword]=useState("");
    const [name,setName]=useState("");
    const [error,setError]=useState("");
    const [success,setSuccess]=useState("");

    const handleRegister=async(e)=>{
        e.preventDefault();
        try{
            const response=await fetch("/api/register",{
                method:"POST",
              header:{
                "content-type":"application/json"
              },
              body:JSON.stringify({
                name,email,password
              })
            });
            if(response.ok){
                const data=await response.json();
                const token=data.token;
                localStorage.setItem("token",token);
                setSuccess("Registration successfull!");
                setError("");
            }
        }
        catch(err){
            console.error("Registration failed",err);
            setError("Registration failed. Please try again.");
            setSuccess("");
        }
    };
    return(
        <div className="min-h-screen flex items-center justify-center bg-gray-100  px-4 ">
            <form 
            onSubmit={handleRegister}
              className="bg-white p-8 rounded-lg shadow-md w-full max-w-md"
              >
               <h2 className="text-2xl font-bold mb-6 text-gray-800 ">Register</h2>

               <div className="mb-4">
                <label className=" block text-gray-700 mb-1">Name</label>
                <input
                type="text"
                className="w-full px-4 py-2 border rounded-md bg-gray-100  "
                vale={name}
                onChange={(e)=>setName(e.target.value)}
                required
                ></input>
               </div>

               <div className="mb-4">
                <label className=" block text-gray-700 mb-1">Email</label>
                <input
                type="eamil"
                className="w-full px-4 py-2 border rounded-md bg-gray-100  "
                value={email}
                onChange={(e)=>setEmail(e.target.value)}
                required
                ></input>
                </div> 

                <div className="mb-4">
                    <label className=" block text-gray-700 mb-1">Password</label>
                    <input
                    type="password"
                    className="w-full px-4 py-2 border rounded-md bg-gray-100  "
                    value={password}
                    onChange={(e)=>setPassword(e.target.value)}
                    required
                    ></input>
                </div>
               <button 
               type="submit"
               className=" w-full bg-blue-600 text-white py-2 rounded-md hover:bg-blue-700"
               >Register</button>

            </form>
        </div>
    )
    

}