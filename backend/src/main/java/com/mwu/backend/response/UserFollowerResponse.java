package com.mwu.backend.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFollowerResponse {
    private int userId;
    private String name;
    private String lastName;
}