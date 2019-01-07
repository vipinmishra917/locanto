package com.example.roshan.tripperstrip;

public class City {

    private String image_url;
    private String desc;
    private String inform;

    private String bestTime;
    private String ticket;

    private String reach;

    private String map;

    private String user_id;

    private String username;

    public City(){


    }

    public City(String image_url, String desc, String inform, String bestTime, String ticket, String reach, String map, String user_id, String username) {
        this.image_url = image_url;
        this.desc = desc;
        this.inform = inform;
        this.bestTime = bestTime;
        this.ticket = ticket;
        this.reach = reach;
        this.map = map;
        this.user_id = user_id;
        this.username = username;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getInform() {
        return inform;
    }

    public void setInform(String inform) {
        this.inform = inform;
    }

    public String getBestTime() {
        return bestTime;
    }

    public void setBestTime(String bestTime) {
        this.bestTime = bestTime;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getReach() {
        return reach;
    }

    public void setReach(String reach) {
        this.reach = reach;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
