package com.example.grim.SEG2105_GROUP199.Clubs;

public class Club {
    public Club(String clubname, String description, ClubOwner owner){

        this.clubname = clubname;
        this.description = description;
        this.owner = owner;

    }

    public String getClubname(){
        return this.clubname;
    }

    public String getDescription(){
        return this.description;
    }

    public ClubOwner getOwner(){
        return this.owner;
    }

    public void setClubname(String clubname){
        this.clubname = clubname;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setOwner(ClubOwner owner){
        this.owner = owner;
    }
    
}
