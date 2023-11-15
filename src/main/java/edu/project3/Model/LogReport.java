package edu.project3.Model;

import java.util.Date;

public record LogReport(String remoteAddress, Date timeLog, RequestModel request, int statusCode, int bytesSend,
                        String userAgent) {
    public record RequestModel(HttpMethod method, String resource, String protocolVersion) {
        public enum HttpMethod {
            GET,
            POST
        }
    }
}
