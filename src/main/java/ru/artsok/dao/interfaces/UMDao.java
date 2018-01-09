package ru.artsok.dao.interfaces;


import ru.artsok.entitys.Migu;
import ru.artsok.entitys.UM;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UMDao extends Dao<UM> {
    public void add(String loginUser, String numberMigu) throws PropertyVetoException, SQLException, IOException;
    public void remove(String loginUser, String numberMigu) throws PropertyVetoException, SQLException, IOException;
    public List<Migu> getMigus(String loginUser) throws PropertyVetoException, IOException, SQLException;

}
