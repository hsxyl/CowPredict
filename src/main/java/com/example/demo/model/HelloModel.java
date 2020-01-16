package com.example.demo.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HelloModel {
    private long id;

    private String title;
    private String text;
}
