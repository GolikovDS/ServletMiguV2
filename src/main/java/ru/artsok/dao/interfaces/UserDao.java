package ru.artsok.dao.interfaces;


import ru.artsok.entitys.User;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

public interface UserDao extends Dao<User> {
    public void putSessionForUser(String userName, String session) throws PropertyVetoException, IOException, SQLException;
    public void removeSessionForUser(String userName) throws PropertyVetoException, IOException, SQLException;

}
