
import React, { useContext, useEffect, useState } from "react";
import { AuthContext } from "../context/AuthContext";
import axios from "axios";

const UserProfile = () => {
  const {logout} = useContext(AuthContext);
  const token=localStorage.getItem("token");
  const [loading,setLoading]=useState();
const userDetails=JSON.parse(localStorage.getItem("userDetails"));
const backendApi=import.meta.env.VITE_BACKEND_URL;
const [userData,setUserData]=useState();
useEffect(() => {
  const fetchCustomer = async () => {
    setLoading(true);
    try {
      const response = await fetch(`${backendApi}/customer/getCustomer/${userDetails.email}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Authorization": "Bearer " + token
        }
      });

      if (response.ok) {
        const data = await response.json();
        setUserData(data);
      } else {
        console.error("Failed to fetch customer data:", response.status);
      }
    } catch (err) {
      console.error("Fetch error:", err);
    } finally {
      setLoading(false);
    }
  };

  fetchCustomer();
}, []);
  console.log(userData)
  
  const addAddress=()=>{
    
  }
  return (
    <div className="flex justify-center items-center min-h-screen bg-gray-100 p-4">
      <div className="max-w-md w-full bg-white shadow-lg rounded-lg p-8">
        <h2 className="text-2xl font-bold mb-6 text-center text-gray-700">
          User Profile
        </h2>

        <div className="space-y-4">
          <div className="flex justify-between border-b pb-2">
            <span className="font-medium text-gray-600">Username:</span>
            <span className="text-gray-800">{userDetails.name}</span>
          </div>

          <div className="flex justify-between border-b pb-2">
            <span className="font-medium text-gray-600">Email:</span>
            <span className="text-gray-800">{userDetails.email}</span>
          </div>

          <div className="flex justify-between border-b pb-2">
            <span className="font-medium text-gray-600">Phone:</span>
            <span className="text-gray-800">{userDetails.mobile}</span>
          </div>
{userData ? (
  <div>
    <span className="font-medium text-gray-600">Address:</span>
    {userData.address.map((addr, index) => (
      <div className="">
      <div key={index} className="flex justify-between  pb-2">
        <h2>House No.</h2>
        <span className="text-gray-800">{addr.houseNo}</span>
         </div>
       <div key={index} className="flex justify-between pb-2 ">
        <h2>Street</h2>
        <span className="text-gray-800">{addr.street}</span>
         </div>
         <div key={index} className="flex justify-between pb-2 ">
        <h2>Landmark</h2>
        <span className="text-gray-800">{addr.landmark}</span>
         </div>
         <div key={index} className="flex justify-between pb-2 ">
        <h2>District</h2>
        <span className="text-gray-800">{addr.district}</span>
         </div>
         <div key={index} className="flex justify-between pb-2 ">
        <h2>State</h2>
        <span className="text-gray-800">{addr.state}</span>
         </div>
         <div key={index} className="flex justify-between pb-2 ">
        <h2>Country</h2>
        <span className="text-gray-800">{addr.country}</span>
         </div>
         <div key={index} className="flex justify-between pb-2 ">
        <h2>pincode</h2>
        <span className="text-gray-800">{addr.pinCode}</span>
         </div>
      </div>
      
      
    ))}
  </div>
) : null}
          <div className="flex justify-between border-b pb-2">
            <span className="font-medium text-gray-600">Address:</span>
           <button className="bg-blue-500 p-2 rounded-lg hover:bg-blue-600"
            onClick={addAddress}>Add new Address</button>
          </div>
  <div className="flex justify-between ">
      <button onClick={logout} className="bg-blue-500 rounded p-2 px-3 hover:bg-blue-600 w-full">Logout</button>
     </div>
        </div>
      </div>
   
        
    </div>
  );
};

export default UserProfile;
