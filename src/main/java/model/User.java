package model;

import java.util.Objects;

public class User {

    private String username,password,role;


    public User(String name, String password,String role) {
        this.username = name;
        this.password=password;
        this.role = role;
    }

    public User(String name, String role) {
        this.username = name;
        this.role = role;
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

    public User() {
    }

    public boolean validate(){
        if(password.equals("admin")){
            return true;
        }
        else{
            return false;
        }
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAdmin(){
        return Objects.equals(this.role, "admin");
    }

    public boolean isWorker(){
        return Objects.equals(this.role, "worker");
    }

}