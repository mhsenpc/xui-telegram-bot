package com.mhsenpc.v2raybot.bot.services;

import com.mhsenpc.v2raybot.bot.dto.UserItemButtonCallback;
import com.mhsenpc.v2raybot.bot.entity.User;
import com.mhsenpc.v2raybot.bot.enums.UserCommandType;
import com.mhsenpc.v2raybot.bot.enums.UserRole;
import com.mhsenpc.v2raybot.bot.enums.UserStatus;
import com.mhsenpc.v2raybot.telegram.types.keyaboard.InlineKeyboardButton;
import com.mhsenpc.v2raybot.telegram.types.keyaboard.InlineKeyboardMarkup;
import org.springframework.stereotype.Service;

@Service
public class UserKeyboardActions {

    public InlineKeyboardMarkup getKeyboardForUser(User user) throws Exception {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        if(user.getRole() == UserRole.NORMAL.getValue()){
            inlineKeyboardMarkup.addRow(getUpgradeUserToAdminButton(user));
        }

        if(user.getRole() == UserRole.ADMIN.getValue()){
            inlineKeyboardMarkup.addRow(getDowngradeAdminToUserButton(user));
        }

        if(user.getStatus() == UserStatus.ACTIVE.getValue()){
            inlineKeyboardMarkup.addRow(getBlockUserButton(user));
        }

        if(user.getStatus() == UserStatus.INACTIVE.getValue()){
            inlineKeyboardMarkup.addRow(getUnblockUserButton(user));
        }

        inlineKeyboardMarkup.addRow(getInspectToUserButton(user));

        return inlineKeyboardMarkup;
    }

    private InlineKeyboardButton getBlockUserButton(User user) throws Exception {

        String blockCallbackData = UserItemButtonCallbackSerializer.serialize(new UserItemButtonCallback(user.getUserId(), UserCommandType.BLOCK));
        return new InlineKeyboardButton("بلاک", blockCallbackData);
    }

    private InlineKeyboardButton getUnblockUserButton(User user) throws Exception {

        String blockCallbackData = UserItemButtonCallbackSerializer.serialize(new UserItemButtonCallback(user.getUserId(), UserCommandType.UNBLOCK));
        return new InlineKeyboardButton("آنبلاگ", blockCallbackData);
    }

    private InlineKeyboardButton getUpgradeUserToAdminButton(User user) throws Exception {

        String blockCallbackData = UserItemButtonCallbackSerializer.serialize(new UserItemButtonCallback(user.getUserId(), UserCommandType.UPGRADE_TO_ADMIN));
        return new InlineKeyboardButton("ارتقا به ادمین", blockCallbackData);
    }

    private InlineKeyboardButton getDowngradeAdminToUserButton(User user) throws Exception {

        String blockCallbackData = UserItemButtonCallbackSerializer.serialize(new UserItemButtonCallback(user.getUserId(), UserCommandType.DOWNGRADE_TO_USER));
        return new InlineKeyboardButton("تنزل درجه", blockCallbackData);
    }

    private InlineKeyboardButton getInspectToUserButton(User user) throws Exception {

        String blockCallbackData = UserItemButtonCallbackSerializer.serialize(new UserItemButtonCallback(user.getUserId(), UserCommandType.INSPECT));
        return new InlineKeyboardButton("جزئیات", blockCallbackData);
    }
}
