package com.santiagomarin.configurations;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class LoggingFilter extends OncePerRequestFilter {
	
	private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class); 

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        long startTime = System.currentTimeMillis();

        chain.doFilter(request, response);  // ejecuta el request

        long duration = System.currentTimeMillis() - startTime;

        log.info("{} {} - status: {} - {}ms",
                request.getMethod(),
                request.getRequestURI(),
                response.getStatus(),
                duration);
    }
}
