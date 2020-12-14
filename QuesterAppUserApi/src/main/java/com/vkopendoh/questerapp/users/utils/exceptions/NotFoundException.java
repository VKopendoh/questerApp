package com.vkopendoh.questerapp.users.utils.exceptions;

/**
 * @author Vladimir Kopendoh
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
