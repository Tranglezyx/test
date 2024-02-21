package com.dm.password;

import lombok.Data;

@Data
public class UserDTO {

    private String id;
    private String userId;
    private String username;
    private String password;
    private String salt;
    private String mobile;
}
