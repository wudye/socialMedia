
import './App.css'
import { Routes } from 'react-router-dom'
import {AuthProvider} from './context/AuthContext.jsx'
import React from 'react'
import Register from './pages/Register.jsx'
import { Route } from 'react-router-dom'
import Login from './pages/Login.jsx'
import Home from './pages/Home.jsx'
import Profile from './pages/Profile';


function App() {

  return (
    <>


        <AuthProvider>
            <Routes>
              <Route path="/" element={<Register />} />
              <Route path='/login' element={<Login />} />

              <Route path='/home' element={<Home />} />
              <Route path='/profile/:userId' element={<Profile />} />
          
          </Routes>
        </AuthProvider>
    </>
  )
}

export default App
