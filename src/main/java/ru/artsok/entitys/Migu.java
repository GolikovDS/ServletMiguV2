package ru.artsok.entitys;


import ru.artsok.tcp.entity.interfaces.MiguHandle;

public class Migu {
    private int id;
    private String number;
    private String color;

    {

    }

    public Migu(int id, String number) {
        this.id = id;
        this.number = number;
    }

    public Migu(String number) {
        this.number = number;
    }

    public Migu(String number, String color) {
        this.number = number;
        this.color = color;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setColor(String color) {
        if (MiguHandle.miguMap.getMap().get(Integer.parseInt(number)) != null) {
            if (MiguHandle.miguMap.getMap().get(Integer.parseInt(number)).getStates().isMiguIsRespond())
                this.color = "rgba(0, 109, 54, 0.55)";
            else
                this.color = "rgba(229, 179, 0, 0.77)";
        } else {
            this.color = "rgba(145, 0, 9, 0.77)";
        }
    }

    public String getColor() {
        return color;
    }
}
