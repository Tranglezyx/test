package com.test.dto;

import com.test.annotation.Column;
import com.test.annotation.GeneratedValue;
import com.test.annotation.Id;
import com.test.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Trangle 2018/07/16 15:13
 */
@Table("users")
public class User extends AuditDomain {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
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

    public Boolean getBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }

    public User getUser(String name, Long id) {
        User user = new User();
        user.setUserName(name);
        user.setUserId(id);
        return user;
    }

    public String testReflect(Object o){
        return o.toString();
    }

    public String testReflect(String name, Long id) {
        return name + id;
    }

    public String testReflect(Long id, String name) {
        return id + name;
    }

    private String getPrivateName() {
        return userName;
    }

    String getProtectedName() {
        return userName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserName() {
        return userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {

    }

    public User(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
