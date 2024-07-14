package com.mhsenpc.v2raybot.telegram.methods;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mhsenpc.v2raybot.bot.SpringContext;
import com.mhsenpc.v2raybot.bot.config.ConfigurationManager;
import com.mhsenpc.v2raybot.bot.enums.ConfigName;

public class DeleteMessageMethod extends BaseTelegramMethod {

    public DeleteMessageMethod() {
        super();
        this.setMethod("deleteMessage");

        setToken(SpringContext.getBean(ConfigurationManager.class).getConfig(ConfigName.BOT_TOKEN));
    }

    @JsonProperty("chat_id")
    private String chatId;

    @JsonProperty("message_id")
    private int messageId;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }
}
