package com.my.demo.designmode.chain;

/**
 * @author hyl
 * @create 2020-05-18
 * @version:
 */
public interface Filter {

    void doFilter(RequestInface request,ResponseInface response,FilterChain filterChain);

}
