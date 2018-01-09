package ru.artsok.dao.interfaces;


import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Extractor <T>{
    public T extract(ResultSet resultSet) throws SQLException, PropertyVetoException, IOException;
}

