package ru.artsok.dao.interfaces;


import ru.artsok.entitys.Migu;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

public interface MiguDao extends Dao<Migu> {
    public Migu getById(int id) throws PropertyVetoException, IOException, SQLException;
    public int getIdByNumb(String numb) throws PropertyVetoException, IOException, SQLException;
}
