package com.qs.p2p.web;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public final class ResponseUtils {
    public static void writeText(String text) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        writeText(response, text);
    }

    public static void writeText(HttpServletResponse response, String text) {
        write(response, "text/plain; charset=UTF-8", text);
    }

    public static void write(HttpServletResponse response, String contentType, String text) {
        response.setContentType(contentType);

        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    private ResponseUtils() {
    }
}
