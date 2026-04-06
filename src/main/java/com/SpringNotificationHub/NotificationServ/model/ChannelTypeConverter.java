package com.SpringNotificationHub.NotificationServ.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class ChannelTypeConverter implements AttributeConverter<ChannelType, String>{
    
     @Override
    public String convertToDatabaseColumn(ChannelType channelType){
        return channelType != null ? channelType.getCategoria() : null;
    }

    @Override
    public ChannelType convertToEntityAttribute(String code){
        for (ChannelType channel : ChannelType.values()){
            if(channel.getCategoria().equalsIgnoreCase(code) || channel.name().equalsIgnoreCase(code)){
                return channel;
            } 
        }
        throw new IllegalArgumentException("Invalid channel type code: " + code);
    }
}
