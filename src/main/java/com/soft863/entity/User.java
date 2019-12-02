package com.soft863.entity;

import java.io.Serializable;

/**
 * @description:
 * @author: wangchaojie
 * @create: 2019-11-12 11:22
 **/
public class User implements Serializable {
   private  int id;
   private String username;
   private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return  "用户名："+username+password;
    }
}
