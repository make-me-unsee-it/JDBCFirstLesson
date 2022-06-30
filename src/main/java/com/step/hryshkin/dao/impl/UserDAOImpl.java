package com.step.hryshkin.dao.impl;

import com.step.hryshkin.config.Connector;
import com.step.hryshkin.config.ContextInitializer;
import com.step.hryshkin.dao.UserDAO;
import com.step.hryshkin.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    private static final Logger LOGGER = LogManager.getLogger(ContextInitializer.class);

    @Override
    public void createNewUser(User user) {
        try (Connection connection = Connector.createConnection()) {
            try (PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO USERS (username, password) values (?,?)")) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getPassword());
                statement.executeUpdate();
            }
        } catch (SQLException exception) {
            LOGGER.error("SQLException at UserDAOImpl at CreateNewUser" + exception);
        }
    }

    @Override
    public Optional<User> getUserByName(String userName) {
        return Optional.empty();
    }
}
