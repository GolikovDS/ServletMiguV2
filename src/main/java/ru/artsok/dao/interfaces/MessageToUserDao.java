package ru.artsok.dao.interfaces;


import ru.artsok.entitys.MessageToUser;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface MessageToUserDao {
    public void add(MessageToUser messageToUser) throws PropertyVetoException, IOException, SQLException;
    public void answer(int idMessage, String text);
    public void remove(int id);
    public List<MessageToUser> read(String nameUser) throws PropertyVetoException, IOException, SQLException;
    public String stringMessages(String nameUser);


}
