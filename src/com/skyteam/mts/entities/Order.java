package com.skyteam.mts.entities;

/**
 * Created by rick- on 2017/6/29.
 */
public class Order {
    private String UserID;
    private String TicketID;
    private String Sites;

    public String getSites() {
        return Sites;
    }

    public void setSites(String sites) {
        Sites = sites;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getTicketID() {
        return TicketID;
    }

    public void setTicketID(String ticketID) {
        TicketID = ticketID;
    }
}
