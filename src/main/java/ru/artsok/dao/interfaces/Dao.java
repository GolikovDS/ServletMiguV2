package ru.artsok.dao.interfaces;


import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

public interface Dao <T>{
    public void add(T t) throws PropertyVetoException, SQLException, IOException;
    public void remove(String kay) throws PropertyVetoException, SQLException, IOException;
    public T get(String kay) throws PropertyVetoException, IOException, SQLException;
}
