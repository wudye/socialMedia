package com.mwu.backend.mappers;

import com.mwu.backend.models.PostImage;
import com.mwu.backend.response.PostImageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostImageMapper {

    @Mapping(source = "post.id",target = "postId")
    PostImageResponse imageToResponse(PostImage postImage);

}