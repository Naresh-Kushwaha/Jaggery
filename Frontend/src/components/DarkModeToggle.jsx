import React from "react";

const DarkModeToggle = ({ darkMode, toggleDarkMode }) => {
  return (
    <button
      className="px-3 py-2 bg-gray-300 dark:bg-gray-700 rounded text-gray-900 dark:text-gray-100 hover:bg-gray-400 dark:hover:bg-gray-600 transition-colors"
      onClick={toggleDarkMode}
    >
      {darkMode ? "Switch to Light Mode" : "Switch to Dark Mode"}
    </button>
  );
};

export default DarkModeToggle;
