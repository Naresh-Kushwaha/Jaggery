import React from "react";

const ProductCard = () => {
  return (
    <div className="max-w-4xl mx-auto p-4">
       <div className="flex flex-col md:flex-row bg-white rounded-2xl shadow-md overflow-hidden">
        
        {/* Product Image */}
        <div className="md:w-1/2">
          <img
            src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTG74BEag3ody_-LYTsqljYLZk0VBeNp8_UVw&s"
            alt="Product"
            className="w-full h-full object-cover"
          />
        </div>

        {/* Product Info */}
        <div className="md:w-1/2 m-1 scroll  flex flex-col justify-between">
          <div>
            <h2 className="text-2xl font-bold mb-2 text-gray-800">iPhone 15 Pros</h2>
            <p className="text-gray-600 mb-4 ">
              The all-new iPhone 15 Pro is lighter, faster, and built with aerospace-grade titanium. Experience next-gen performance with A17 Pro chip.
            </p>
            <p className="text-xl font-semibold text-indigo-600">$1199</p>
          </div>

          <button className="mt-6 bg-indigo-600 text-white px-4 py-2 rounded-xl hover:bg-indigo-700 transition-all">
            Buy Now
          </button>
        </div>

      </div> 

    </div>
  );
};

export default ProductCard;
