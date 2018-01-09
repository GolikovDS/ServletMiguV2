package ru.artsok.dao.impl;


import org.apache.log4j.Logger;
import ru.artsok.dao.interfaces.UMDao;
import ru.artsok.entitys.LoginToNumbMigu;
import ru.artsok.entitys.Migu;
import ru.artsok.entitys.UM;
import ru.artsok.entitys.User;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.artsok.util.ClassNameUtil.getCurrentClassName;

public class UMDaoImp extends TempConnector<UM> implements UMDao {

    public static final Logger LOGGER = Logger.getLogger(getCurrentClassName());

    @Override
    public void add(UM um) throws PropertyVetoException, SQLException, IOException {
        query0("INSERT INTO u_m(id_user, id_migu) VALUES (?, ?)", statement -> {
            statement.setInt(1, um.getId_user());
            statement.setInt(2, um.getId_migu());
            statement.executeUpdate();
        });
    }

    @Override
    public void remove(String kay) throws PropertyVetoException, SQLException, IOException {

    }

    @Override
    public void add(String loginUser, String numberMigu) throws PropertyVetoException, SQLException, IOException {
        query0("INSERT INTO u_m(id_user, id_migu) VALUES (?, ?)", statement -> {
            statement.setInt(1, new UserDaoImpl().get(loginUser).getId());
            statement.setInt(2, new MiguDaoImpl().get(numberMigu).getId());
            statement.executeUpdate();
        });
    }

    @Override
    public void remove(String loginUser, String numberMigu) throws PropertyVetoException, SQLException, IOException {
        query0("DELETE FROM u_m WHERE id_user=? AND id_migu=?", statement -> {
            statement.setInt(1, new UserDaoImpl().get(loginUser).getId());
            statement.setInt(2, new MiguDaoImpl().get(numberMigu).getId());
            statement.executeUpdate();
        });
    }

    @Override
    public List<Migu> getMigus(String loginUser) throws PropertyVetoException, IOException, SQLException {

        List<Migu> migus = new MiguDaoImpl().querySelects0(null, "SELECT * FROM u_m WHERE id_user=" + new UserDaoImpl().get(loginUser).getId(),
                resultSet -> new Migu(resultSet.getInt("id"), new MiguDaoImpl().getById(resultSet.getInt("id_migu")).getNumber()));
        for (Migu migu : migus) migu.setColor("");
        return migus;
    }

    public void removeUser(String key) throws PropertyVetoException, SQLException, IOException {
        remove0("DELETE FROM u_m WHERE id_user=?", key);
    }

    public void removeMigu(String key) throws PropertyVetoException, IOException, SQLException {

        remove0("DELETE FROM u_m WHERE id_migu=?", key);

    }

    private void remove0(String query, String key) throws PropertyVetoException, SQLException, IOException {
        query0(query, statement -> {
            statement.setInt(1, Integer.parseInt(key));
            statement.executeUpdate();
        });
    }

    @Override
    public UM get(String kay) throws PropertyVetoException, IOException, SQLException {
        return querySelect0(kay, "SELECT * FROM u_m WHERE id_user=?", resultSet -> new UM(resultSet.getInt("id"), resultSet.getInt("id_user"),
                resultSet.getInt("id_migu")));
    }

    public List<UM> getAll() throws PropertyVetoException, IOException, SQLException {
        return querySelects0(null, "SELECT * FROM u_m", resultSet -> new UM(resultSet.getInt("id"), resultSet.getInt("id_migu"),
                resultSet.getInt("id_user")));
    }

    public List<LoginToNumbMigu> getLoginAndNumb() throws PropertyVetoException, IOException, SQLException {
        List<UM> ums = getAll();
        List<LoginToNumbMigu> loginToNumbMigus = new ArrayList<>();
        for (UM um : ums) {
            User user = new UserDaoImpl().getById(String.valueOf(um.getId_user()));
            loginToNumbMigus.add(new LoginToNumbMigu((new UserDaoImpl().getById(String.valueOf(um.getId_user())).getLogin()),
                    (new MiguDaoImpl().getById(String.valueOf(um.getId_migu()))).getNumber()));
        }
        return loginToNumbMigus;
    }
}
