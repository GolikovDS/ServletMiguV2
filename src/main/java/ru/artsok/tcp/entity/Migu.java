package ru.artsok.tcp.entity;


public class Migu {

    private int address;
    private int number;
    private String type;
    private String node;
    private boolean isChangeData;
    private MiguState states = new MiguState();


    public Migu() {
    }

    public Migu(int address, int number, String type, String node) {
        this.address = address;
        this.node = node;
        this.number = number;
        this.type = type;
    }


    public String getNode() {
        return node;
    }


    public void setNode(String node) {
        this.node = node;
    }

    public int getNumber() {
        return number;
    }


    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }

    public int getAddress() {
        return address;
    }


    public void setAddress(int address) {
        this.address = address;
    }

    public MiguState getStates() {
        return states;
    }


    public void setStates(MiguState states) {
        this.states = states;
    }


    public String toString() {
        return "МИЖУ зав. № " + number + "\n" + "Масса: " + states.getMass() + "\n" + "Время" + states.getTime();
    }

    public String toStringHtml(){
        return "МИЖУ зав. № " + number + "    " + states.toString();

    }

}
