package com.SpringNotificationHub.NotificationServ.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetails {
    private String message;
    private java.sql.Date timestamp;
}
