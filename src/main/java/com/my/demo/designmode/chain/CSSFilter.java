package com.my.demo.designmode.chain;

/**
 * @author hyl
 * @create 2020-05-18
 * @version:
 */
public class CSSFilter implements Filter {
    @Override
    public void doFilter(RequestInface request, ResponseInface response, FilterChain filterChain) {
        String msg = "CSSFilter处理过";
        StringBuilder sb = new StringBuilder(request.getRequestStr());
        sb.append(msg);
        System.out.println(msg);

        request.setRequestStr(sb.toString());
        filterChain.doFilter(request, response);
        response.setResponseStr(sb.toString());
    }
}
