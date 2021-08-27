package com.my.demo.mvc;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @version:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MvcConfiguration.class)
public @interface EnableMvc {
}
