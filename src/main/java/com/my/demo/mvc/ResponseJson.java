package com.my.demo.mvc;

import java.lang.annotation.*;

/**
 * @version:
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseJson {
}
