package ru.artsok.entitys;


public class MessageToUser {
    private int id;
    private String nameUser;
    private String date;
    private String textMessage;

    public MessageToUser(int id, String nameUser, String date, String textMessage) {
        this.date = date;
        this.nameUser = nameUser;
        this.textMessage = textMessage;
        this.id = id;
    }

    public MessageToUser(String nameUser, String date, String textMessage) {
        this.date = date;
        this.nameUser = nameUser;
        this.textMessage = textMessage;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
