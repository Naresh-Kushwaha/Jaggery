import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './components/HeroSection';
import Register from './components/Register';
import Login from './components/Login';
import Home from './pages/Home';
import ProductListingPage from './pages/ProductListingPage';
import Footer from './components/Footer';
import About from './pages/About';
import Contact from './pages/Contact';
import ProductDetailsPage from './pages/ProductDetailsPage';
import CartPage from './pages/CartPage';
import CheckoutPage from './pages/CheckOutPage';
import OrderConfirmationPage from './pages/OrderConfirmationPage';
import TrackOrderPage from './pages/TrackOrderPage';
import UserProfile from './pages/UserProfile';
import PrivateRoute from './routes/PrivateRoute';

function App() {
  return (
    <div >
      <Header></Header>
      <Routes>
        <Route path="/register" element={<Register></Register>}></Route>
        <Route path="/login" element={<Login></Login>}></Route>
        <Route path="/" element={<Home></Home>}> </Route>
        <Route path="/products/:id" element={<ProductListingPage></ProductListingPage>}></Route>
        <Route path="/about" element={<About></About>}></Route>
        <Route path="/contact" element={<div><Contact></Contact></div>}></Route>
        <Route path="/product/:id" element={<ProductDetailsPage></ProductDetailsPage>}></Route>
        <Route path="/cart" element={<PrivateRoute><CartPage></CartPage></PrivateRoute>}></Route>
        <Route path="/checkout" element={<PrivateRoute><CheckoutPage></CheckoutPage></PrivateRoute>}></Route>
        <Route path="/order-confirmation" element={<OrderConfirmationPage/>} />
        <Route path="/track-order" element={<TrackOrderPage/>} />
        <Route path="/profile" element={
          <PrivateRoute>
            <UserProfile />
          </PrivateRoute>
        } />
      </Routes>
      <Footer />
    </div>
  );
}

export default App;
