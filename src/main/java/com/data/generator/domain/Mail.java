package com.data.generator.domain;

import java.time.LocalDate;
import java.util.Objects;

public class Mail {

    private Long senderId;
    private Long recipientId;
    private String mailTopic;
    private String mailBody;
    private LocalDate sentDate;
    private LocalDate deliveryDate;
    private String status;

    private Mail(Long senderId, Long recipientId, String mailTopic, String mailBody, LocalDate sentDate, LocalDate deliveryDate, String status) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.mailTopic = mailTopic;
        this.mailBody = mailBody;
        this.sentDate = sentDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    public static Mail from(Long senderId, Long recipientId, String mailTopic, String mailBody, LocalDate sentDate, LocalDate deliveryDate, String status) {
        return new Mail(senderId, recipientId, mailTopic, mailBody, sentDate, deliveryDate, status);
    }

    public Long getSenderId() {
        return senderId;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public String getMailTopic() {
        return mailTopic;
    }

    public String getMailBody() {
        return mailBody;
    }

    public LocalDate getSentDate() {
        return sentDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mail mail = (Mail) o;
        return Objects.equals(senderId, mail.senderId) &&
                Objects.equals(recipientId, mail.recipientId) &&
                Objects.equals(mailTopic, mail.mailTopic) &&
                Objects.equals(mailBody, mail.mailBody) &&
                Objects.equals(sentDate, mail.sentDate) &&
                Objects.equals(deliveryDate, mail.deliveryDate) &&
                Objects.equals(status, mail.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senderId, recipientId, mailTopic, mailBody, sentDate, deliveryDate, status);
    }

    @Override
    public String toString() {
        return "Mail{" +
                "senderId=" + senderId +
                ", recipientId=" + recipientId +
                ", mailTopic='" + mailTopic + '\'' +
                ", mailBody='" + mailBody + '\'' +
                ", sentDate=" + sentDate +
                ", deliveryDate=" + deliveryDate +
                ", status='" + status + '\'' +
                '}';
    }
}
