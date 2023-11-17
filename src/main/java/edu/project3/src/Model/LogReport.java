package edu.project3.src.Model;

import java.time.OffsetDateTime;

public record LogReport(String remoteAddress, OffsetDateTime timeLog, RequestModel request, int statusCode, int bytesSend,
                        String userAgent) {
    public record RequestModel(HttpMethod method, String resource, String protocolVersion) {
        public enum HttpMethod {
            GET,
            POST
        }
    }
}
