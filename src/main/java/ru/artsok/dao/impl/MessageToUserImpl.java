package ru.artsok.dao.impl;


import ru.artsok.dao.interfaces.MessageToUserDao;
import ru.artsok.entitys.MessageToUser;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageToUserImpl extends TempConnector<MessageToUser> implements MessageToUserDao {


    @Override
    public void add(MessageToUser messageToUser) throws PropertyVetoException, IOException, SQLException {
        query0("INSERT INTO messages_to_user (name_user, date, text_message) VALUES (?, ?, ?)", statement -> {
            statement.setString(1, messageToUser.getNameUser());
            statement.setString(2, messageToUser.getDate());
            statement.setString(3, messageToUser.getTextMessage());

            statement.executeUpdate();
        });
    }

    @Override
    public void answer(int idMessage, String text) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<MessageToUser> read(String nameUser) throws PropertyVetoException, IOException, SQLException {
        return querySelects0(nameUser, "SELECT * FROM messages_to_user WHERE name_user=?",
                resultSet -> new MessageToUser(resultSet.getInt("id"), resultSet.getString("name_user"), resultSet.getString("date"),
                        resultSet.getString("text_message")));
    }

    @Override
    public String stringMessages(String nameUser) {
        StringBuilder result = new StringBuilder();
        List<MessageToUser> texts = new ArrayList<>();
        try {
            texts = read(nameUser);
        } catch (PropertyVetoException | IOException | SQLException e) {
            e.printStackTrace();
        }
        for (int i = texts.size() - 1; i >= 0; i--){
                result.append("<div class=\"message_div\">").append("<b class=\"date_message\">Дата: ").append(texts.get(i).getDate()).append("</b></br>")
                        .append(texts.get(i).getTextMessage()).append("</div>")
                        .append("</br>");

//            <form method="post" action="/admin_select.do">
//            <input type="submit" name="answerButton" value="Ответить">
//            </form>

        }
        return result.toString();
    }

}
