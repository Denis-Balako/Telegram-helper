package com.balako.telegramhelper.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Data
@Table(name = "telegram_message")
@SQLDelete(sql = "UPDATE telegram_message SET is_deleted = true WHERE message_id = ?")
@Where(clause = "is_deleted = false")
public class TelegramMessage {
    @Id
    private Long messageId;
    @Column(nullable = false)
    private LocalDateTime date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", referencedColumnName = "chatId", nullable = false)
    private TelegramChat chat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "telegram_user_id", referencedColumnName = "userId", nullable = false)
    private TelegramUser telegramUser;
    private String text;
    private MessageType type;
    private boolean isDeleted = false;

    public enum MessageType {
        SENT, RECEIVED
    }
}
