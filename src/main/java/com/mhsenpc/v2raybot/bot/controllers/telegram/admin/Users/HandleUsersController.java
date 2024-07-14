package com.mhsenpc.v2raybot.bot.controllers.telegram.admin.Users;

import com.mhsenpc.v2raybot.bot.controllers.telegram.TelegramController;
import com.mhsenpc.v2raybot.bot.dto.UserItemButtonCallback;
import com.mhsenpc.v2raybot.bot.entity.User;
import com.mhsenpc.v2raybot.bot.enums.UserRole;
import com.mhsenpc.v2raybot.bot.enums.UserStatus;
import com.mhsenpc.v2raybot.bot.repository.UserRepository;
import com.mhsenpc.v2raybot.bot.services.UserInspectFormatter;
import com.mhsenpc.v2raybot.bot.services.UserItemButtonCallbackSerializer;
import com.mhsenpc.v2raybot.bot.services.UserStepService;
import com.mhsenpc.v2raybot.telegram.types.Update;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class HandleUsersController extends TelegramController {

    @Autowired
    private UserStepService userStepService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInspectFormatter userInspectFormatter;

    @Override
    public void invoke(Update update) {

        String callbackQueryData = "";
        if (update.getCallbackQuery() != null) {
            try {
                callbackQueryData = update.getCallbackQuery().getData();

                UserItemButtonCallback response = UserItemButtonCallbackSerializer.deserialize(callbackQueryData);
                handleCallback(response);

            } catch (Exception e) {
                this.sendMessage("متاسفانه مشکلی در دریافت فرمان شما پیش آمده است");
                log.error(e.getMessage());
            }
        }

    }

    private void handleCallback(UserItemButtonCallback response) {

        int userId = response.getUserId();
        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()){
            sendMessage("متاسفانه کاربر انتخاب شده پیدا نشد");
            log.warn("user id " + userId + " not found for doing any operations");
            return;
        }

        switch (response.getCommandType()){

            case UPGRADE_TO_ADMIN -> {
                upgradeUserToAdmin(user.get());
            }

            case DOWNGRADE_TO_USER -> {
                downgradeToUser(user.get());
            }

            case BLOCK -> {
                blockUser(user.get());
            }

            case UNBLOCK -> {
                unblockUser(user.get());
            }

            case INSPECT -> {
                inspectUser(user.get());
            }
        }

    }

    private void inspectUser(User user) {

        sendMessage(userInspectFormatter.getFormattedUser(user));
    }

    private void unblockUser(User user) {

        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);
        sendMessage("کاربر با موفقیت به حالت فعال تغییر کرد");
    }

    private void blockUser(User user) {

        user.setStatus(UserStatus.INACTIVE);
        userRepository.save(user);
        sendMessage("کاربر با موفقیت مسدود شد");
    }

    private void downgradeToUser(User user) {

        user.setRole(UserRole.NORMAL.getValue());
        userRepository.save(user);

        sendMessage("تنزل درجه ادمین با موفقیت انجام شد");
    }

    private void upgradeUserToAdmin(User user) {

        user.setRole(UserRole.ADMIN.getValue());
        userRepository.save(user);

        sendMessage("کاربر با موفقیت تبدیل به ادمین شد");
    }
}
