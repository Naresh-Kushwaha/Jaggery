import { createContext } from "react";
import { useContext, useState } from "react";
export const CartContext=createContext()
export const CartProvider=({children})=>{
 const [cartItems,setCartItems]=useState([]);
const addToCart = (product, quantity) => {
  setCartItems((prev) => {
    const existingItem = prev.find((item) => item.id === product.id);

    if (existingItem) {
      const newQuantity = existingItem.quantity + quantity;

      // Check if new quantity exceeds stock
      console.log("Current stock:", product.stock);
      console.log("New quantity:", newQuantity);
      if (newQuantity <= product.stock) {
        return prev.map((item) =>
          item.id === product.id
            ? { ...item, quantity: newQuantity }
            : item
        );
      } else {
        // Optionally show an alert or return prev if exceeding stock
        alert("Cannot add more than stock available");
        return prev;
      }
    }

    // If item doesn't exist, add it only if quantity is valid
    if (quantity <= product.stock) {
      return [...prev, { ...product, quantity }];
    } else {
      alert("Cannot add more than stock available");
      return prev;
    }
  });
};

 const updateQuantity=(id,quantity)=>{
    setCartItems((prev)=>{
         return prev.map((item)=>
            item.id===id?{...item,quantity}:item);
    });
 };
 const removeFromCart=(id)=>{
    setCartItems((prev)=>prev.filter((item)=>
        item.id!==id
    ));
 };
 return (
    <CartContext.Provider value={{
        cartItems,
        addToCart,
        updateQuantity,
        removeFromCart
    }}>
        {children}</CartContext.Provider>
 )

}