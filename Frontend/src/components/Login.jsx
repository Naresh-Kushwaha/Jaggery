import { useState } from "react";

export default function Login(){

    const [email,setEmail]=useState("");
    const [password,setPassword]=useState("");
    const [error,setError]=useState("");
    const [success,setSuccess]=useState("");
        const handleLogin=async(e)=>{
            e.preventDefault();
            try{
                const response=await fetch("/api/login",{
                    method:"POST",
                    headers:{
                        "Content-type":"application/json"
                    },
                    body: JSON.stringify({email,password})
                });
                if(response.ok){
                    const data=await response.json();
                    const token=data.token;
                    localStorage.setItem("token",token);
                    setSuccess("Login successful!");
                    setError("");
                }
                else{
                    const errData=await response.json();
                    setError(errData.message || "Login failed. Please try again.");
                    setSuccess("");

                }
                }catch(err){
                    setError("Login failed. Please try again.");
                    setSuccess("");
                }
            };
            return(
               <div className="min-h-screen flex items-center justify-center bg-gray-50 px-4 ">
                <form
                 onSubmit={handleLogin}
                 className="bg-gray-200 p-8 rounded-lg shadow-md w-full max-w-md"
                 >
                    <h2 className="text-2xl font-bold mb-6 text-gray-800">Login</h2>
                    {error&&<p>{error}</p>}
                    {success&&<p>{success}</p>}
                    <div className="mb-6">
                        <label className="block text-gray-700 mb-1">Email</label>
                        <input 
                        type="text"
                        value={email}
                        onChange={((e)=>setEmail(e.target.value))}
                        required
                        className="w-full  px-4 py-2 border rounnded-md bg-gray-100 "

                        ></input>
                    </div>
                    <div className="mb-6">
                        <label className="block text-gray-700 mb-1">Password</label>
                        <input 
                        type="password"
                        value={email}
                        onChange={((e)=>setEmail(e.target.value))}
                        required
                        className="w-full  px-4 py-2 border rounnded-md bg-gray-100 "
                        ></input>
                    </div>
                    <button 
                    type="submit"
                    className="w-full bg-blue-600 text-white py-2 rounded-md hover:bg-blue-700 "
                    
                    >Login</button>
                </form>
               </div>
            )

        }
    
