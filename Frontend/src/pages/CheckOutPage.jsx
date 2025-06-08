import { CartContext } from "../context/CartContext";
import Header from "../components/Header";
import Footer from "../components/Footer";
import { useContext, useState } from "react";
import { Navigate, useNavigate } from "react-router-dom";

export default function CheckoutPage() {
    const navigate=useNavigate();
  const { cartItems, clearCart } = useContext(CartContext);

  const [address, setAddress] = useState ({
    name: "",
    phone: "",
    street: "",
    city: "",
    state: "",
    zip: "",
  });

  const total = cartItems.reduce(
    (sum, item) => sum + item.price * item.quantity,
    0
  );

  const handleChange = (e) => {
    setAddress((prev) => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const placeOrder = () => {
    const order={
        id:"ORD"+ Date.now(),
        cartItems,
        address,
        total,
    };
    navigate("/order-confirmation", { state: { order } });
    // Normally you'd send this to the backend
    console.log("Order Placed:", { cartItems, address });
    alert("🎉 Order placed successfully!");

    clearCart(); // Clear cart after placing order
  };

  return (
    <>
 
      <div className="max-w-6xl mx-auto px-4 py-10">
        <h1 className="text-2xl font-bold text-yellow-800 mb-8">Checkout</h1>

        <div className="grid grid-cols-1 md:grid-cols-2 gap-10">
          {/* Address Form */}
          <div className="space-y-4">
            <h2 className="text-xl font-semibold text-gray-700">Shipping Address</h2>

            <input
              type="text"
              name="name"
              placeholder="Full Name"
              value={address.name}
              onChange={handleChange}
              className="w-full border px-4 py-2 rounded"
              required
            />
            <input
              type="text"
              name="phone"
              placeholder="Phone Number"
              value={address.phone}
              onChange={handleChange}
              className="w-full border px-4 py-2 rounded"
              required
            />
            <input
              type="text"
              name="street"
              placeholder="Street Address"
              value={address.street}
              onChange={handleChange}
              className="w-full border px-4 py-2 rounded"
              required
            />
            <input
              type="text"
              name="city"
              placeholder="City"
              value={address.city}
              onChange={handleChange}
              className="w-full border px-4 py-2 rounded"
              required
            />
            <input
              type="text"
              name="state"
              placeholder="State"
              value={address.state}
              onChange={handleChange}
              className="w-full border px-4 py-2 rounded"
              required
            />
            <input
              type="text"
              name="zip"
              placeholder="ZIP Code"
              value={address.zip}
              onChange={handleChange}
              className="w-full border px-4 py-2 rounded"
              required
            />
          </div>

          {/* Cart Summary */}
          <div className="border rounded-lg p-6 shadow-md">
            <h2 className="text-xl font-semibold mb-4 text-gray-700">Order Summary</h2>

            <ul className="space-y-2">
              {cartItems.map((item) => (
                <li key={item.id} className="flex justify-between">
                  <span>{item.name} × {item.quantity}</span>
                  <span>₹{item.price * item.quantity}</span>
                </li>
              ))}
            </ul>

            <hr className="my-4" />

            <p className="text-lg font-bold">Total: ₹{total}</p>

            <button
              onClick={placeOrder}
              className="mt-6 w-full bg-yellow-700 text-white py-2 rounded hover:bg-yellow-800"
            >
              Place Order
            </button>
          </div>
        </div>
      </div>

    </>
  );
}
