package com.data.generator.repository;

import com.data.generator.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final static String usersSql = "insert into users (name, age, address) values (?, ?, ?)";
    private final static String userIdsSql = "select id from users";

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void uploadUsers (List<User> users) {
        jdbcTemplate.batchUpdate(usersSql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int index) throws SQLException {
                User user = users.get(index);
                preparedStatement.setString(1, user.getName());
                preparedStatement.setInt(2, user.getAge());
                preparedStatement.setString(3, user.getAddress());
            }

            @Override
            public int getBatchSize() {
                return users.size();
            }
        });
    }

    public List<Long> fetchUserIds() {
        return jdbcTemplate.queryForList(userIdsSql, Long.class);
    }
}
