import {createContext, useState, useEffect} from 'react'
import {jwtDecode} from 'jwt-decode';
import { useNavigate } from 'react-router-dom';
const AuthContext = createContext();



export const AuthProvider = ({children}) => {
    const navigate = useNavigate();

    const[isAuthenticated, setIsAuthenticated] = useState(localStorage.getItem('isAuthenticated') === 'true');

    const [user, setUser] = useState(JSON.parse(localStorage.getItem('user')) || {});

    const login =(token) => {
        const decodeToken = jwtDecode(token);
        setUser(decodeToken.user);
        setIsAuthenticated(true);
        localStorage.setItem('isAuthenticated', 'true');
        localStorage.setItem('user', JSON.stringify(decodeToken.user));
        localStorage.setItem('token', token);
        navigate('/home')



    }


    const logout = () => {
        setUser({});
        setIsAuthenticated(false);
        localStorage.removeItem('isAuthenticated');
        localStorage.removeItem('user');
        localStorage.removeItem('token');
        navigate('/');
    }

    useEffect(() => {

        const checkTokenExpiration = () => {
          const token = localStorage.getItem('token');
          if (token) {
        
            const decodedToken =jwtDecode(token);
            const currentTime = Date.now() / 1000; // Convert to seconds
            if (decodedToken.exp < currentTime) {
              logout(); // Token expired, log out the user
            }
        }
      }

      if (isAuthenticated) {
        checkTokenExpiration();
      }
    }, [isAuthenticated]);
        

    
    const value = {isAuthenticated, login, logout, user
        };
  return (
    

    <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
  )
}

export default AuthContext;
