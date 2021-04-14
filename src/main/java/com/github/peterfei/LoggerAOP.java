package com.github.peterfei;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface LoggerAOP {
    String value() default "";
}
