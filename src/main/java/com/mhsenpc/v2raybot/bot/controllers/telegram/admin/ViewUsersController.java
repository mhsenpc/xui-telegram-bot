package com.mhsenpc.v2raybot.bot.controllers.telegram.admin;

import com.mhsenpc.v2raybot.bot.controllers.telegram.TelegramController;
import com.mhsenpc.v2raybot.bot.dto.UserStepWithPayload;
import com.mhsenpc.v2raybot.bot.entity.User;
import com.mhsenpc.v2raybot.bot.enums.UserRole;
import com.mhsenpc.v2raybot.bot.enums.UserStep;
import com.mhsenpc.v2raybot.bot.repository.UserRepository;
import com.mhsenpc.v2raybot.bot.services.UserFormatter;
import com.mhsenpc.v2raybot.bot.services.UserKeyboardActions;
import com.mhsenpc.v2raybot.bot.services.UserStepService;
import com.mhsenpc.v2raybot.telegram.methods.SendMessageMethod;
import com.mhsenpc.v2raybot.telegram.types.Message;
import com.mhsenpc.v2raybot.telegram.types.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ViewUsersController extends TelegramController {

    @Autowired
    private UserStepService userStepService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserKeyboardActions userKeyboardActions;

    @Autowired
    private UserFormatter userFormatter;

    @Override
    public void invoke(Update update) throws Exception {

        User currentUser = this.userRepository.findByChatId(chatId);

        if (currentUser.getRole() != UserRole.ADMIN.getValue()) {
            sendMessage("شما اجازه دسترسی به این بخش را ندارید. لطفا با مدیر سیستم تماس بگیرید");
            return;
        }

        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            this.sendMessage("هیچ کاربری برای نمایش وجود ندارد");
        }
        for (User user : users) {

            SendMessageMethod userItemMessage = new SendMessageMethod();
            userItemMessage.setChatId(chatId);
            userItemMessage.setText(userFormatter.getFormattedUser(user));
            userItemMessage.setReplyMarkup(userKeyboardActions.getKeyboardForUser(user));
            requestHandler.send(userItemMessage, Message.class);
        }

        userStepService.set(chatId, new UserStepWithPayload(UserStep.ADMIN_VIEW_USERS));
    }
}
