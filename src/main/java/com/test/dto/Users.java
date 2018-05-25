package com.test.dto;

import com.test.annotation.Column;
import com.test.annotation.GeneratedValue;
import com.test.annotation.Id;
import com.test.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhouyunxiang on 2017/6/30.
 */
@Table("users")
public class Users implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column("user_id")
    @GeneratedValue
    private Integer userId;

    @Column("user_name")
    private String userName;

    @Column("password")
    private String password;

    private Date creationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;

        Users users = (Users) o;

        if (!getUserId().equals(users.getUserId())) return false;
        if (!getUserName().equals(users.getUserName())) return false;
        if (!getPassword().equals(users.getPassword())) return false;
        return getCreationDate().equals(users.getCreationDate());
    }

    @Override
    public int hashCode() {
        int result = getUserId().hashCode();
        result = 31 * result + getUserName().hashCode();
        result = 31 * result + getPassword().hashCode();
        result = 31 * result + getCreationDate().hashCode();
        return result;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
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

    public Users(Integer userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public Users(Integer userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public Users(){

    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
