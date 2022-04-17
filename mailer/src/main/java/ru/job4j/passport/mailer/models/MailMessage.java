package ru.job4j.passport.mailer.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MailMessage {

    private String sender;
    private Set<String> receivers;
    private String subject;
    private String text;

    public MailMessage() {
        receivers = new HashSet<>();
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<String> getReceivers() {
        return new HashSet<>(receivers);
    }

    public MailMessage addReceiver(String value) {
        receivers.add(value);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MailMessage that = (MailMessage) o;
        return
                Objects.equals(sender, that.sender)
                && Objects.equals(receivers, that.receivers)
                && Objects.equals(subject, that.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, receivers, subject);
    }

    @Override
    public String toString() {
        return
                "MailMessage{"
                + "sender='" + sender + '\''
                + ", receivers=" + receivers
                + ", subject='" + subject + '\''
                + ", text='" + text + '\''
                + '}';
    }
}