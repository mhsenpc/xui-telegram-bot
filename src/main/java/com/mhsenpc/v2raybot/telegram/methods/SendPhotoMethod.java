package com.mhsenpc.v2raybot.telegram.methods;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mhsenpc.v2raybot.bot.SpringContext;
import com.mhsenpc.v2raybot.bot.config.ConfigurationManager;
import com.mhsenpc.v2raybot.bot.enums.ConfigName;
import com.mhsenpc.v2raybot.telegram.types.keyaboard.IReplyMarkup;

public class SendPhotoMethod extends BaseTelegramMethod {

    @JsonProperty("chat_id")
    private String chatId;

    @JsonProperty("photo")
    private String photo;

    @JsonProperty("caption")
    private String caption;

    @JsonProperty("disable_notification")
    private boolean disableNotification;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("reply_markup")
    private IReplyMarkup replyMarkup;

    public SendPhotoMethod() {
        super();
        this.setMethod("sendPhoto");

        setToken(SpringContext.getBean(ConfigurationManager.class).getConfig(ConfigName.BOT_TOKEN));
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
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
        return "SendPhotoMethod{" +
                "chatId='" + chatId + '\'' +
                ", photo='" + photo + '\'' +
                ", caption='" + caption + '\'' +
                ", disableNotification=" + disableNotification +
                ", replyMarkup=" + replyMarkup +
                '}';
    }
}
