package com.skyteam.mts.entities;

/**
 * Created by rick- on 2017/6/29.
 */
public class Ticket {

    private String TicketID;
    private String TicketName;
    private String Location;
    private String TicketTime;
    private String Director;
    private String Actor;
    private Double Price;
    private int RestNum;
    private int TicketStatus;

    public String getTicketID() {
        return TicketID;
    }

    public void setTicketID(String ticketID) {
        TicketID = ticketID;
    }

    public String getTicketName() {
        return TicketName;
    }

    public void setTicketName(String ticketName) {
        TicketName = ticketName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getTicketTime() {
        return TicketTime;
    }

    public void setTicketTime(String ticketTime) {
        TicketTime = ticketTime;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getActor() {
        return Actor;
    }

    public void setActor(String actor) {
        Actor = actor;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public int getRestNum() {
        return RestNum;
    }

    public void setRestNum(int restNum) {
        RestNum = restNum;
    }

    public int getTicketStatus() {
        return TicketStatus;
    }

    public void setTicketStatus(int ticketStatus) {
        TicketStatus = ticketStatus;
    }
}
