package com.daniel.server.commands;

import com.daniel.common.network.Response;
import com.daniel.common.person.Person;
import com.daniel.server.connection.ResponseFactory;
import com.daniel.server.util.CollectionHandler;

import java.util.Map;

/**
 * Команда выводит все элементы коллекции на экран
 */
public class Show {

    public Response execute(CollectionHandler collectionHandler) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Person> entry: collectionHandler.getMap().entrySet()) {
            sb.append(entry).append("\n");
        }
        return ResponseFactory.createOkResponse(sb.toString());
    }

}
