import React, { useContext, useEffect } from 'react'
import Nav from '../components/Nav.jsx'
import ProfileCard from '../components/ProfileCard.jsx'
import AuthContext  from '../context/AuthContext.jsx'
import PostService from '../services/PostService.jsx'
import { Center, Heading, VStack, Image } from '@chakra-ui/react'
import Posts from '../components/Posts.jsx'
import svg from '../svgs/undraw_no_data_re_kwbl.svg'


const Home = () => {
    const {user} = useContext(AuthContext);
    const [posts, setPosts] = React.useState([]);


    const getData = React.useCallback(async () => {
        const postService = new PostService();
      
        try {
            if (user.id != undefined) {
                const response = await postService.getAllByUserFollowing(user.id, localStorage.getItem('token'));
                setPosts(response.data);
            }
        } catch (error) {
            console.error("Error fetching posts:", error);
        }
    }, [user.id]);


    useEffect(() => {
        getData();
    }, [getData]);


  return (
    <>
        <Nav />
        <ProfileCard userName={user.fullName} />
        {
                posts.length === 0 ?
                    <Center>
                        <VStack h={'100vh'} alignItems={'center'} justifyContent={'center'}>
                            <Heading>No posts to show</Heading>
                            <Image src={svg} h={'50vh'} />
                        </VStack>

                    </Center>
                    :
                    <Posts posts={posts} />
            }
    </>
  )
}

export default Home
