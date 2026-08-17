import './App.css'
import {Provider} from "react-redux";
import { store } from './app/store';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import LandingPage from './components/HomePage/LandingPage';
import Login from './components/AuthticationPage/Login';
import ProtectedRoute from './components/ProtectedRoute';
function App() {
  return(
    <Provider store={store}>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Login />} />
            <Route path="/landing" element={
              <ProtectedRoute allowedRoles={["admin", "editor", "viewer"]}>
                <LandingPage />
              </ProtectedRoute>
            } />
          </Routes>
        </BrowserRouter>
    </Provider>
  )
}

export default App
