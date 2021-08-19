package com.my.demo.designmode.proxy;

import com.my.demo.designmode.proxy.annotation.RestApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author hyl
 * @create 2020-05-14
 * @version:
 */
@RestApi(value = "http://127.0.0.1:8080/stu")
public interface IStudentApi {

    @GetMapping(value = "/listStudent")
    String listStudent();

//    @GetMapping(value = "/{id}")
//    Mono<Object> getStudent(
//            @PathVariable(value = "id") String studentId
//    );

}
