package ru.artsok.entitys;


public class LoginToNumbMigu {
    private String login;
    private String numb;

    public LoginToNumbMigu(String login, String numb) {
        this.login = login;
        this.numb = numb;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNumb() {
        return numb;
    }

    public void setNumb(String numb) {
        this.numb = numb;
    }

    @Override
    public String toString() {
        return "LoginToNumbMigu{" +
                "login='" + login + '\'' +
                ", numb='" + numb + '\'' +
                '}';
    }
}
