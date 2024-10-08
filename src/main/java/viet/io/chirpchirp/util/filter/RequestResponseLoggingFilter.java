package viet.io.chirpchirp.util.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * A filter that logs incoming HTTP requests and their corresponding responses.
 * This filter logs the HTTP method, URI, protocol, and response status.
 */
@Slf4j
@Component
public class RequestResponseLoggingFilter implements Filter {

    /**
     * Filters incoming requests and logs the request method, URI, protocol, and response status.
     *
     * @param request  the ServletRequest object, which contains the client's request
     * @param response the ServletResponse object, which contains the filter's response
     * @param chain    the FilterChain object, which allows the filter to pass on the request and response to the next entity in the chain
     * @throws IOException      if an I/O error occurs during the processing of the request
     * @throws ServletException if the request could not be handled
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Cast the request and response to HTTP versions
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Create a custom wrapper to capture the response status
        CustomHttpServletResponseWrapper responseWrapper = new CustomHttpServletResponseWrapper(httpResponse);

        // Proceed with the rest of the filter chain
        chain.doFilter(request, responseWrapper);

        // Log request method, URI, protocol, and the response status
        log.info("\"{} {} {}\" {}",
                httpRequest.getMethod(),                        // Method (e.g., POST)
                httpRequest.getRequestURI(),                    // URI (e.g., /pos/login)
                httpRequest.getProtocol(),                      // Protocol (e.g., HTTP/1.1)
                responseWrapper.getStatus());                   // Response status (e.g., 200)
    }
}