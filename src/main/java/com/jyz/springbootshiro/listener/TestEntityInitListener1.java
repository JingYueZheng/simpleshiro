package com.jyz.springbootshiro.listener;

import com.jyz.springbootshiro.event.TestEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestEntityInitListener1 {
    @Async
    @EventListener         //注意必须带参数  参数为被监听事件的对象
    public void  listentMethod(TestEvent event){
         log.error("testEvent 发生变化了 from testEntityInitListener1");
    }
}
