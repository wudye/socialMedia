package com.mwu.backend.mappers;

import com.mwu.backend.models.Like;
import com.mwu.backend.requests.LikeRequest;
import com.mwu.backend.response.LikeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LikeMapper {

    @Mapping(source = "user.id",target = "userId")
    @Mapping(source = "user.name",target = "name")
    @Mapping(source = "user.lastName",target = "lastName")
    LikeResponse likeToPostLikeResponse(Like like);

    @Mapping(source = "postId",target = "post.id")
    @Mapping(source = "userId",target = "user.id")
    Like requestToLike(LikeRequest likeRequest);

    List<LikeResponse> likesToLikeResponses(List<Like> likes);

}