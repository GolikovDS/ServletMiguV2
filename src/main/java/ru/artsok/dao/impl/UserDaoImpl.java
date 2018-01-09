package ru.artsok.dao.impl;


import ru.artsok.dao.interfaces.UserDao;
import ru.artsok.entitys.User;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl extends TempConnector<User> implements UserDao {
    @Override
    public void add(User user) throws PropertyVetoException, SQLException, IOException {
        query0("INSERT INTO user_migu(login, password, jsession, organization) VALUES (?, ?, ?, ?)", statement -> {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getSession());
            statement.setString(4, user.getOrganization());
            statement.executeUpdate();
        });
    }

    @Override
    public void remove(String login) throws PropertyVetoException, SQLException, IOException {
        new UMDaoImp().removeUser(String.valueOf(get(login).getId()));

        query0("DELETE FROM user_migu WHERE login=?", statement -> {
            statement.setString(1, login);
            statement.executeUpdate();
        });

    }

    @Override
    public User get(String kay) throws PropertyVetoException, IOException, SQLException {
        return querySelect0(kay, "SELECT * FROM user_migu WHERE login=?", resultSet -> new User(resultSet.getInt("id"), resultSet.getString("login"),
                resultSet.getString("organization"), resultSet.getString("password"),
                resultSet.getString("jsession")));
    }

    public User getById(String kay) throws PropertyVetoException, IOException, SQLException {
        return querySelect0(kay, "SELECT * FROM user_migu WHERE id=?", resultSet -> new User(resultSet.getInt("id"), resultSet.getString("login"),
                resultSet.getString("organization"), resultSet.getString("password"),
                resultSet.getString("jsession")));
    }

    public List<User> getAll() throws PropertyVetoException, IOException, SQLException {
        return querySelects0(null, "SELECT * FROM user_migu", resultSet -> new User(resultSet.getInt("id"), resultSet.getString("login"),
                resultSet.getString("organization"), resultSet.getString("password"),
                resultSet.getString("jsession")));
    }

    @Override
    public void putSessionForUser(String userName, String session) throws PropertyVetoException, IOException, SQLException {
        query0("UPDATE user_migu SET jsession=? WHERE login=?", statement -> {
            statement.setString(1, session);
            statement.setString(2, userName);
            statement.executeUpdate();
        });
    }

    @Override
    public void removeSessionForUser(String userName) throws PropertyVetoException, IOException, SQLException {
        query0("UPDATE user_migu SET jsession=? WHERE login=?", statement -> {
            statement.setString(1, "");
            statement.setString(2, userName);
            statement.executeUpdate();
        });
    }
}
