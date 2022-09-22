package com.daniel.client.connection;

import com.daniel.common.network.Request;
import com.daniel.common.network.RequestType;
import com.daniel.common.person.Person;

public class RequestFactory {

    private RequestFactory() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");

    }

    public static Request createArgObjectRequest(String command, Integer arg, Person person) {
        return new Request(RequestType.ARG_OBJECT_REQUEST, command, arg, person);
    }

    public static Request createObjectRequest(String command, Person person) {
        return new Request(RequestType.OBJECT_REQUEST, command, null, person);
    }

    public static Request createArgRequest(String command, Integer arg) {
        return new Request(RequestType.ARG_REQUEST, command, arg, null);
    }

    public static Request createSimpleRequest(String command) {
        return new Request(RequestType.SIMPLE_REQUEST, command, null, null);
    }
}
