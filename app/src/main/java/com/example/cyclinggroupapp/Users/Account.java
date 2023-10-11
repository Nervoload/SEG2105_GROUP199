package com.example.cyclinggroupapp.Users;

public class Account {

    protected String username;
    protected String password;
    protected String email;
    
    public Account(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){    // Make sure this is not easily accesible!
        return password;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){    // Make sure this is not easily accesible!
        this.password = password;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void verifyEmail(String email1, String email2){

        if (email1.equals(email2)){
            this.email = email1;
        }
        else{
            System.out.println("Emails do not match!");
        }

    }

    


}
