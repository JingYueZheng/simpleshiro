package com.jyz.springbootshiro.comment;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface Logging {
    String value() default  "";
}
