package com.step.hryshkin.dao.impl;

import com.step.hryshkin.config.Connector;
import com.step.hryshkin.config.ContextInitializer;
import com.step.hryshkin.dao.GoodDAO;
import com.step.hryshkin.model.Good;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class GoodDAOImpl implements GoodDAO {
    private static final Logger LOGGER = LogManager.getLogger(ContextInitializer.class);

    @Override
    public Optional<Good> getByTitle(String title) {
        Optional<Good> good = Optional.empty();
        try (Connection connection = Connector.createConnection()) {
            try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM GOODS WHERE TITLE = '" + title + "'")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    good = Optional.of(new Good(rs.getLong("ID"),
                            rs.getNString("TITLE"),
                            rs.getBigDecimal("PRICE")));
                }
            }
        } catch (SQLException throwables) {
            LOGGER.error("SQLException at UserDAOImpl at CreateNewUser" + throwables);

        }
        return good;
    }

    @Override
    public Optional<Good> getId(long id) {
        return Optional.empty();
    }

    @Override
    public List<Good> getAll() {
        return null;
    }
}
