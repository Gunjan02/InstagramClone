package com.example.admin.instagramclone;

public class User {
    String user_id;
    String username;
    long phone;
    String email;

    public User(){

    }

    public User(String user_id,String username,long phone,String email){
        this.user_id=user_id;
        this.username=username;
        this.phone=phone;
        this.email=email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String toString(){
        return "User{"+
                "user_id='" + user_id + '\'' +
                ", username='" + username +'\'' +
                ",phone='" + phone +'\'' +
                "email='" + email +'\'' +'}';

    }
}
