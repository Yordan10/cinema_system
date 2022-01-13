package com.example.cinemasystem.model.request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class MessageRequest {
    protected String name;
    protected String accountName;
}
