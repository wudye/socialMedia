import axios from 'axios'

export default class UserService{

    getById(id,token){
        return axios.get(import.meta.env.VITE_REACT_APP_API+"users/getbyid/"+id,{
            headers:{
                'Authorization':"Bearer "+token
            }
        });
    }

    isFollowing(userId,followingId,token){
        return axios.get(import.meta.env.VITE_REACT_APP_API+`users/isfollowing?userId=${userId}&followingId=${followingId}`,{
            headers:{
                'Authorization':"Bearer "+token
            }
        });
    }
}