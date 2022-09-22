package com.daniel.server.connection;

import com.daniel.common.network.Response;
import com.daniel.common.network.ResponseType;

public class ResponseFactory {
    private ResponseFactory() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static Response createOkResponse(String content) {
        return new Response(ResponseType.OK_RESPONSE, content);
    }

    public static Response createErrorResponse(String content) {
        return new Response(ResponseType.ERROR_RESPONSE, content);
    }
}
