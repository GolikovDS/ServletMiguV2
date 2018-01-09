package ru.artsok.dao.impl;

import ru.artsok.dao.interfaces.MiguDao;
import ru.artsok.entitys.Migu;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class MiguDaoImpl extends TempConnector<Migu> implements MiguDao {
    @Override
    public void add(Migu migu) throws PropertyVetoException, IOException, SQLException {
        query0("INSERT INTO migu(number) VALUES (?)", statement -> {
            statement.setString(1, migu.getNumber());
            statement.executeUpdate();
        });
    }

    @Override
    public void remove(String key) throws PropertyVetoException, SQLException, IOException {
        new UMDaoImp().removeMigu(String.valueOf(get(key).getId()));

        query0("DELETE FROM migu WHERE number=?", statement -> {
            statement.setString(1, key);
            statement.executeUpdate();
        });

    }

    @Override
    public Migu get(String kay) throws PropertyVetoException, IOException, SQLException {
        return querySelect0(kay, "SELECT * FROM migu WHERE number=?",
                resultSet-> new Migu(resultSet.getInt("id"), resultSet.getString("number")));
    }

    public Migu getById(String kay) throws PropertyVetoException, IOException, SQLException {
        return querySelect0(kay, "SELECT * FROM migu WHERE id=?",
                resultSet-> new Migu(resultSet.getInt("id"), resultSet.getString("number")));
    }

    @Override
    public Migu getById(int id) throws PropertyVetoException, IOException, SQLException {
        return querySelect0(null, "SELECT * FROM migu WHERE id=" + id,
                resultSet-> new Migu(resultSet.getInt("id"), resultSet.getString("number")));
    }

    @Override
    public int getIdByNumb(String numb) throws PropertyVetoException, IOException, SQLException {
        return querySelect0(numb, "SELECT * FROM migu WHERE number=?",
                resultSet-> new Migu(resultSet.getInt("id"), resultSet.getString("number"))).getId();
    }


    public List<Migu> getAll() throws PropertyVetoException, IOException, SQLException {
        List<Migu> migus =  querySelects0(null, "SELECT * FROM migu",
                resultSet -> new Migu(resultSet.getString("number")));
        for (Migu migu : migus) migu.setColor("");

        return migus;
    }
}
