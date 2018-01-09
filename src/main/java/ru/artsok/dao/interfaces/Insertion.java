package ru.artsok.dao.interfaces;


import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Insertion {
    public void action(PreparedStatement preparedStatement) throws SQLException, PropertyVetoException, IOException;
}
