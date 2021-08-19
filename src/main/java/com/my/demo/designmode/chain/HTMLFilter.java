package com.my.demo.designmode.chain;

/**
 * @author hyl
 * @create 2020-05-18
 * @version:
 */
public class HTMLFilter implements Filter {
    @Override
    public void doFilter(RequestInface request, ResponseInface response, FilterChain filterChain) {
        String msg = "--HTMLFilter处理过--";
        StringBuilder sb = new StringBuilder(request.getRequestStr());
        sb.append(msg);
        System.out.println(msg);
        request.setRequestStr(sb.toString());
        response.setResponseStr(sb.toString());
        filterChain.doFilter(request, response);
    }
}
