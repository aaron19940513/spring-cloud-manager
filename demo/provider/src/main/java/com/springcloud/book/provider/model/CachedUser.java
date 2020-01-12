package com.springcloud.book.provider.model;
/**
 * @author sam
 * @date 08/22/19 10:35
 */
public class CachedUser {

    private String username;
    private String password;
    private int age;

    public CachedUser() {
    }

    public CachedUser(String username, String password, int age) {
        this.username = username;
        this.password = password;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
