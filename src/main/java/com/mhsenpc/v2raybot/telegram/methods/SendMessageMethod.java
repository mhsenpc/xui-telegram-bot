package com.mhsenpc.v2raybot.telegram.methods;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mhsenpc.v2raybot.bot.config.Config;
import com.mhsenpc.v2raybot.telegram.types.keyaboard.IReplyMarkup;

public class SendMessageMethod extends BaseTelegramMethod {

    @JsonProperty("chat_id")
    private String chatId;

    @JsonProperty("text")
    private String text;

    @JsonProperty("disable_notification")
    private boolean disableNotification;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("reply_markup")
    private IReplyMarkup replyMarkup;

    public SendMessageMethod() {
        super();
        this.setMethod("sendMessage");
        this.setToken(Config.getInstance().getToken());
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDisableNotification() {
        return disableNotification;
    }

    public void setDisableNotification(boolean disableNotification) {
        this.disableNotification = disableNotification;
    }

    public IReplyMarkup getReplyMarkup() {
        return replyMarkup;
    }

    public void setReplyMarkup(IReplyMarkup replyMarkup) {
        this.replyMarkup = replyMarkup;
    }

    @Override
    public String toString() {
        return "SendMessageRequest{" +
                "chatId='" + chatId + '\'' +
                ", text='" + text + '\'' +
                ", disableNotification=" + disableNotification +
                ", replyMarkup=" + replyMarkup +
                ", queryParams=" + queryParams +
                '}';
    }
}
