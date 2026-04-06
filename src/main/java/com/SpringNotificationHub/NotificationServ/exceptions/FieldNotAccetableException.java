package com.SpringNotificationHub.NotificationServ.exceptions;

import lombok.Getter;

@Getter
public class FieldNotAccetableException extends RuntimeException {
    public FieldNotAccetableException(String message) {
        super(message);
    }
}
