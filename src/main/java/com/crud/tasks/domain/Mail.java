package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Mail {
    private String mailTo;
    private String toCc;
    private String subject;
    private String message;

    public Mail(final String mailTo, final String subject, final String message) {
        this(mailTo, null, subject, message);
    }
}
