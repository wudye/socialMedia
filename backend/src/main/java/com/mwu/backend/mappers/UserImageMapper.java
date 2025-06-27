package com.mwu.backend.mappers;


import com.mwu.backend.models.UserImage;
import com.mwu.backend.response.UserImageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserImageMapper {

    @Mapping(source = "user.id",target = "userId")
    UserImageResponse userImageToResponse(UserImage userImage);

}