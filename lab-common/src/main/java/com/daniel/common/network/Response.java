package com.daniel.common.network;

import java.io.Serializable;

public class Response implements Serializable {

    private static final long serialVersionUID = 798150367917915018L;

    private final ResponseType type;
    private final String content;

    public Response(ResponseType type, String content) {
        this.type = type;
        this.content = content;
    }

    public ResponseType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;

        if (type != response.type) return false;
        return content.equals(response.content);
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + content.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Response{" +
                "type=" + type +
                ", content='" + content + '\'' +
                '}';
    }
}
