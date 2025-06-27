package com.mwu.backend.mappers;

import com.mwu.backend.models.Follow;
import com.mwu.backend.models.User;
import com.mwu.backend.requests.UserAddRequest;
import com.mwu.backend.response.UserFollowerResponse;
import com.mwu.backend.user.UserFollowingResponse;
import com.mwu.backend.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "user.id",target = "userId")
    @Mapping(source = "user.name",target = "name")
    @Mapping(source = "user.lastName",target = "lastName")
    UserFollowerResponse followToFollowerResponse(Follow follow);
    @Mapping(source = "following.id",target = "userId")
    @Mapping(source = "following.lastName",target = "lastName")
    @Mapping(source = "following.name",target = "name")
    UserFollowingResponse followToFollowingResponse(Follow follow);
    @Mapping(source = "followers",target = "followers")
    @Mapping(source = "following",target = "following")
    UserResponse userToResponse(User user);

    User requestToUser(UserAddRequest userAddRequest);

    List<UserResponse> usersToResponses(List<User> users);

    List<UserFollowingResponse> followsToFollowingResponses(List<Follow> follows);
}
