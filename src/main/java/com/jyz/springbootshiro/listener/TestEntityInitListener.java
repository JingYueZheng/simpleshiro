package com.jyz.springbootshiro.listener;

import com.jyz.springbootshiro.event.TestEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestEntityInitListener implements ApplicationListener<TestEvent> {

    @Async
    @Override
    public void onApplicationEvent(TestEvent testEvent) {
         log.error("testEvent 发生变化了");
    }
}
