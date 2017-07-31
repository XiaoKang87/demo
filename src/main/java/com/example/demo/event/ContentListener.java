package com.example.demo.event;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by Wu.Kang on 2017/6/5.
 */
@Component
public class ContentListener implements ApplicationListener<ContentEvent> {

    @Async
    @Override
    public void onApplicationEvent(ContentEvent contentEvent) {
        System.out.println(contentEvent.getSource());
        System.out.println(Thread.currentThread().getName());
    }
}
