import { ShoppingCart, User } from "lucide-react";
import { useContext, useState } from "react";
import { Link } from "react-router-dom";
import { CartContext} from "../context/CartContext";

export default function Header() {
 
 const {cartItems} =useContext(CartContext);
 const totalQty=cartItems.reduce((sum,item)=>sum+item.quantity,0);
  const [isMobileMenuOpen, setMobileMenuOpen] = useState(false);
  return (
    <header className="flex items-center justify-between px-6 py-4 shadow-md bg-white sticky top-0 z-50">
      <div className="text-2xl font-bold text-yellow-800">JaggeryFarm</div>

      <div className="flex gap-4 items-center">
        {/* <input
          type="text"
          placeholder="Search..."
          className="px-3 py-1 border rounded-md hidden md:block"
        /> */}
        <Link to="/cart" className="relative">
  🛒
  {totalQty > 0 && (
    <span className="absolute -top-2 -right-2 bg-red-600 text-white text-xs px-2 py-0.5 rounded-full">
      {totalQty}
    </span>
  )}
</Link>

        <Link to="/login"><User className="w-6 h-6 cursor-pointer"  /></Link>
         {/* Desktop Nav */}
      <nav className="hidden md:flex gap-6 text-gray-700">
        <a href="/">Home</a>
        <a href="/products/0">Products</a>
        <a href="/about">About</a>
        <a href="/contact">Contact</a>
      </nav>

      {/* Mobile Hamburger Icon */}
      <button
        className="md:hidden text-gray-700 focus:outline-none"
        onClick={() => setMobileMenuOpen(!isMobileMenuOpen)}
      >
        {/* Simple Hamburger Icon */}
        <svg
          className="w-6 h-6"
          fill="none"
          stroke="currentColor"
          viewBox="0 0 24 24"
        >
          <path
            strokeLinecap="round"
            strokeLinejoin="round"
            strokeWidth={2}
            d={isMobileMenuOpen ? "M6 18L18 6M6 6l12 12" : "M4 6h16M4 12h16M4 18h16"}
          />
        </svg>
      </button>

      {/* Mobile Nav Menu */}
      {isMobileMenuOpen && (
        <nav className="md:hidden absolute top-16 right-2 max-w-md  bg-white shadow-lg flex flex-col items-start gap-4 px-4 py-6 text-gray-700 z-50 ">
          <a href="/" onClick={() => setMobileMenuOpen(false)}>Home</a>
          <a href="/products/0" onClick={() => setMobileMenuOpen(false)}>Products</a>
          <a href="/about" onClick={() => setMobileMenuOpen(false)}>About</a>
          <a href="/contact" onClick={() => setMobileMenuOpen(false)}>Contact</a>
        </nav>
      )}
      </div>
    </header>
  );
}
