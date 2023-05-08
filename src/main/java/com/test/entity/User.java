package com.test.entity;

import com.test.annotation.Column;
import com.test.annotation.GeneratedValue;
import com.test.annotation.Id;
import com.test.annotation.Table;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author Trangle 2018/07/16 15:13
 */
@Table("users")
@Data
@FieldNameConstants
public class User extends AuditDomain{

    private static final long serialVersionUID = 1L;

    public Class clazz(){
        return super.getClass();
    }

    @Id
    @Column("user_id")
    @GeneratedValue
    private Long userId;

    @Column("user_name")
    private String userName;

    @Column("password")
    private String password;

    private Date creationDate;

    private Boolean bool;

    private BigDecimal money;

    public User getUser(String name, Long id) {
        User user = new User();
//        user.setUserName(name);
//        user.setUserId(id);
        return user;
    }

    public User() {

    }

    public User(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public User(String userName, BigDecimal money) {
        this.userName = userName;
        this.money = money;
    }

    public static List<User> getDefaultUserListInfo(){
        List<User> userList = new ArrayList<>();
        userList.add(new User("qqq",new BigDecimal(2)));
        userList.add(new User("qqq",new BigDecimal(3)));
        userList.add(new User("eee",new BigDecimal(2)));
        userList.add(new User("ffd",new BigDecimal(2)));
        userList.add(new User("ddd",new BigDecimal(2)));
        userList.add(new User("qqaaaq",new BigDecimal(2)));
        return userList;
    }
}
