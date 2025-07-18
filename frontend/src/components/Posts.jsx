import { Center, VStack } from '@chakra-ui/react'
import React from 'react'
import PostCard from './PostCard.jsx'

function Posts({posts}) {

    const imageUrl = import.meta.env.VITE_REACT_APP_API + "postimages/download/"

    return (
        <>
            <Center>

                <VStack marginTop={'50px'} spacing={5}>
                    {
                        posts.map(post => (
                            <PostCard
                                key={post.id}
                                description={post.description}
                                userName={post.userName+" "+post.userLastName}
                                postImage={imageUrl + post.id}
                                postId={post.id}
                                userId={post.userId}
                            />
                        ))
                    }
                </VStack>
            </Center>
        </>
    )
}

export default Posts