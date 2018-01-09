package ru.artsok.entitys;


public class User {
    private int id;
    private String login;
    private String password;
    private String organization;
    private String session;

    public User(int id, String login, String organization, String password, String session) {
        this.id = id;
        this.login = login;
        this.organization = organization;
        this.password = password;
        this.session = session;
    }

    public User(String login, String password, String organization,  String session) {
        this.login = login;
        this.organization = organization;
        this.password = password;
        this.session = session;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
