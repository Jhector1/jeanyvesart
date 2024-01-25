package com.art.jeanyvesart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailClient implements Serializable {
    private String firstName;
    private String lastName;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String phoneNumber;
    private String message;

    private String fileAttachment;
}
