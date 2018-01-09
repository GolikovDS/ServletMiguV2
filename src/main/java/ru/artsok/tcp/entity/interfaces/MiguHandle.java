package ru.artsok.tcp.entity.interfaces;


import ru.artsok.tcp.entity.Migu;
import ru.artsok.tcp.entity.MiguMap;

public interface MiguHandle {
    public static MiguMap miguMap = new MiguMap();
    public void view();



    public Migu removeMiguByNumberTreeView(String number);
}
