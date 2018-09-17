package com.data.generator.repository;

import com.data.generator.domain.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MailRepository {

    private final JdbcTemplate jdbcTemplate;
    private final static String mailsSql =
            "insert into mails (sender, recipient, mail_topic, mail_body, sent_date, delivery_date, status) " +
                    "values (?, ?, ?, ?, ?, ?, ?)";

    @Autowired
    public MailRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void uploadMails(List<Mail> mails) {
        jdbcTemplate.batchUpdate(mailsSql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int index) throws SQLException {
                Mail mail = mails.get(index);
                preparedStatement.setLong(1, mail.getSenderId());
                preparedStatement.setLong(2, mail.getRecipientId());
                preparedStatement.setString(3, mail.getMailTopic());
                preparedStatement.setString(4, mail.getMailBody());
                preparedStatement.setDate(5, Date.valueOf(mail.getSentDate()));
                preparedStatement.setDate(6, Date.valueOf(mail.getDeliveryDate()));
                preparedStatement.setString(7, mail.getStatus());
            }

            @Override
            public int getBatchSize() {
                return mails.size();
            }
        });
    }
}
