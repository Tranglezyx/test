package com.test.entity;

import com.test.annotation.Column;
import com.test.annotation.GeneratedValue;
import com.test.annotation.Id;
import com.test.annotation.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Trangle 2018/07/16 15:13
 */
@Table("users")
@Data
public class User extends AuditDomain{

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

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
        user.setUserName(name);
        user.setUserId(id);
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
}
