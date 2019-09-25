package com.jyz.springbootshiro.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Configuration;
@Data
public class TestEvent extends ApplicationEvent {

    private String name;

    private  String age;

    public TestEvent(Object source) {
        super(source);
    }


    public TestEvent(String name,Object source) {
        super(source);
        this.name = name;
    }
}
