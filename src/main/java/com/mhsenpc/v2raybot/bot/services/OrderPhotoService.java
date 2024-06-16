package com.mhsenpc.v2raybot.bot.services;

import com.mhsenpc.v2raybot.bot.entity.Order;
import com.mhsenpc.v2raybot.bot.entity.Photo;
import com.mhsenpc.v2raybot.bot.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderPhotoService {

    @Autowired
    private PhotoRepository photoRepository;
    public String getOrderPhoto(Order order){

        List<Photo> photos = photoRepository.findByOrderIdOrderByWidthDesc(order.getOrderId());

        if(photos.isEmpty()){
            return "https://wallpapers.com/images/high/placeholder-image-question-mark-2l23hmc4ndpavf9v-2l23hmc4ndpavf9v.png";
        }
        else{
            Photo photo = photos.get(0);
            return photo.getTelegramFileId();
        }
    }
}
