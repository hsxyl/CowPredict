package com.example.demo.Model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.nio.channels.Channel;

@Data
@Accessors(chain = true)
public class HelloModel {
    private long id;

    private String title;
    private String text;
}
