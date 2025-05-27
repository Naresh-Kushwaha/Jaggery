import React from "react";

const Profile = () => {
  return (
    <div className="profile flex items-center gap-4">
      <div className="profile-icon">
        <img
          src="https://via.placeholder.com/40"
          alt="Profile"
          className="w-10 h-10 rounded-full"
        />
      </div>
      <span className="text-gray-900 dark:text-gray-100">User</span>
    </div>
  );
};

export default Profile;
