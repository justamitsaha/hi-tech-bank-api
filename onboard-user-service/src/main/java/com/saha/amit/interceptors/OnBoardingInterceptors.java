package com.saha.amit.interceptors;

import brave.Span;
import brave.Tracer;
import brave.propagation.TraceContextOrSamplingFlags;
import brave.propagation.TraceIdContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Optional;

@Component
public class OnBoardingInterceptors implements HandlerInterceptor {

    @Autowired
    private Tracer tracer;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String tracerId = "";
        String spanId = "";
        String cookieHeader = "";
        try{
            cookieHeader = request.getHeader("cookie");
        } catch (Exception e){
            e.printStackTrace();
        }

        if(null != cookieHeader){
            Optional<String> found = Optional.empty();
            for (String s : cookieHeader.split(";")) {
                if (s.contains("tracer")) {
                    String s1 = s.split("=")[1];
                    found = Optional.of(s1);
                    break;
                }
            }
            tracerId = found.orElse("");
        }

//        Enumeration headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = (String) headerNames.nextElement();
//            System.out.println("header: " + headerName + ":" + request.getHeader(headerName));
//        }

        Span span = tracer.currentSpan();
        if (span != null) {
            if (tracerId.isEmpty()) {
                var lng = span.context().traceId();
                tracerId = String.valueOf(lng);
                spanId = span.context().spanIdString();
                response.addHeader("tracer", tracerId);
                Cookie cookie = new Cookie("tracer", tracerId);
                cookie.setMaxAge(10000);
                cookie.setHttpOnly(false);
                cookie.setSecure(false);
                response.addCookie(cookie);

                Cookie cookie2 = new Cookie("alo", "jhat");
                cookie2.setMaxAge(120);
                cookie2.setHttpOnly(false);
                cookie2.setSecure(false);
                response.addCookie(cookie2);
            } else {
                long longTraceId = Long.parseLong(tracerId);
                span = this.tracer.nextSpan(TraceContextOrSamplingFlags.newBuilder()
                        .traceIdContext(TraceIdContext.newBuilder()
                                .traceId(longTraceId)
                                .build())
                        .build()).name("foo").start();
                tracerId = span.context().traceIdString();
            }
        }


        return true;
    }
}
