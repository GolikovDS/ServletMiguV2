package ru.artsok.entitys;


public class UM {
    int id;
    int id_user;
    int id_migu;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UM(int id, int id_migu, int id_user) {
        this.id = id;
        this.id_migu = id_migu;
        this.id_user = id_user;
    }

    public UM(int id_migu, int id_user) {
        this.id_migu = id_migu;
        this.id_user = id_user;
    }
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_migu() {
        return id_migu;
    }

    public void setId_migu(int id_migu) {
        this.id_migu = id_migu;
    }
}
