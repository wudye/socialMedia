import axios from 'axios';

export default class  PostService  {

    add(values,token){
        return axios.post(import.meta.env.VITE_REACT_APP_API+"posts/add",values,{
            headers:{
                'Authorization':"Bearer "+token
            }
        })
    }
 getAllByUserId(userId,token){
        return axios.get(import.meta.env.VITE_REACT_APP_API+"posts/getallbyuser/"+userId,{
            headers:{
                'Authorization':"Bearer "+token
            }
        })
    }

    getAllByUserFollowing(userId,token){
        return axios.get(import.meta.env.VITE_REACT_APP_API+"posts/getbyuserfollowing/"+userId,{
            headers:{
                'Authorization':"Bearer "+token
            }
        })
    }

    getById(id,token){
        return axios.get(import.meta.env.VITE_REACT_APP_API+"posts/getbyid/"+id,{
            headers:{
                'Authorization':"Bearer "+token
            }
        })
    }
    

}


