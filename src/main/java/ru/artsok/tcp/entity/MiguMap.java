package ru.artsok.tcp.entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class MiguMap {
    private Map<Integer, Migu> map = new ConcurrentHashMap<Integer, Migu>();

    public void setState(Integer number, MiguState state) {
        map.get(number).setStates(state);
    }

    public void setStateByAddress(byte address, MiguState state) {
        for (Migu migu : getMap().values())
            if (migu.getAddress() == address)
                setState(migu.getNumber(), state);
    }


    public MiguState getState(Integer address) {
        return map.get(address).getStates();
    }

    public Map<Integer, Migu> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Migu> map) {
        this.map = map;
    }
}
