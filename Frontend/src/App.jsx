
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import Register from './components/Register';
import Login from './components/Login';


function App() {
  return (
<Routes>
  {/* <Route path="/login" element={<Login></Login>}></Route> */}
  <Route path="/register" element={<Register></Register>}></Route>
  <Route path="/login" element={<Login></Login>}></Route>
</Routes>
  );
}

export default App;
