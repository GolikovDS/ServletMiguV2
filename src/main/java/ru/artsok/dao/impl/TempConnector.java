package ru.artsok.dao.impl;


import ru.artsok.dao.config.JdbcPoolConnectC3PO;
import ru.artsok.dao.interfaces.Extractor;
import ru.artsok.dao.interfaces.Insertion;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TempConnector<T> {

    public void query0(String sqlQuery, Insertion insertion) throws PropertyVetoException, SQLException, IOException {
        try (Connection connection = JdbcPoolConnectC3PO.getInstance().getConnection()) {
            connection.setAutoCommit(false);
            insertion.action(connection.prepareStatement(sqlQuery));
            connection.commit();
        }
    }

    public T querySelect0(String key, String sqlQuery, Extractor extractor) throws PropertyVetoException, SQLException, IOException {

        T result = null;
        try (Connection connection = JdbcPoolConnectC3PO.getInstance().getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                if (key != null)
                    preparedStatement.setString(1, key);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        result = (T) extractor.extract(resultSet);
                    }
                }
            }
        }
        return result;
    }

    public List<T> querySelects0(String key, String sqlQuery, Extractor extractor) throws PropertyVetoException, SQLException, IOException {

        List<T> result = new ArrayList<>();
        try (Connection connection = JdbcPoolConnectC3PO.getInstance().getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                if (key != null)
                    preparedStatement.setString(1, key);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        result.add((T) extractor.extract(resultSet));
                    }
                }
            }
        }
        return result;
    }


}


