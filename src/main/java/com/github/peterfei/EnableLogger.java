package com.github.peterfei;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(value = {LoggerAOPAspect.class})
@Inherited
public @interface EnableLogger {
}
