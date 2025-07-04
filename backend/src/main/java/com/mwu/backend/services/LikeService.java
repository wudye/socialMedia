package com.mwu.backend.services;

import com.mwu.backend.mappers.LikeMapper;
import com.mwu.backend.models.Like;
import com.mwu.backend.repositories.LikeRepository;
import com.mwu.backend.requests.LikeRequest;
import com.mwu.backend.response.LikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final LikeMapper likeMapper;

    public LikeService(LikeRepository likeRepository, LikeMapper likeMapper) {
        this.likeRepository = likeRepository;
        this.likeMapper = likeMapper;
    }

    public List<LikeResponse> getAllByPost(int postId){
        List<Like> likes = likeRepository.findAllByPost_Id(postId);
        return likeMapper.likesToLikeResponses(likes);
    }

    public List<LikeResponse> getAllByUser(int userId){
        List<Like> likes = likeRepository.findAllByUser_Id(userId);
        return likeMapper.likesToLikeResponses(likes);
    }

    public boolean isLiked(int userId,int postId){
        Optional<Like> like = likeRepository.findByUser_IdAndPost_Id(userId,postId);
        return like.isPresent();
    }

    public void add(LikeRequest likeRequest){
        if (isLiked(likeRequest.getUserId(), likeRequest.getPostId())){
            return;
        }
        Like like = likeMapper.requestToLike(likeRequest);
        likeRepository.save(like);
    }

    public void delete(LikeRequest likeRequest){
        Optional<Like> like = likeRepository.findByUser_IdAndPost_Id(likeRequest.getUserId(),likeRequest.getPostId());
        likeRepository.delete(like.get());
    }

}