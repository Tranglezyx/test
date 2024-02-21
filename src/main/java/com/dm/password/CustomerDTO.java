package com.dm.password;

import lombok.Data;

@Data
public class CustomerDTO {

    private String id;
    private String platform_id;
    private String developer_id;
    private String dev_name;
    private String dev_password;
    private String dev_phone;
}
