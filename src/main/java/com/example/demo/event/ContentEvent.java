package com.example.demo.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by Wu.Kang on 2017/6/5.
 */
public class ContentEvent extends ApplicationEvent {
    public ContentEvent(String content) {
        super(content);
    }
}
