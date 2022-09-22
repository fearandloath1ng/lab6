package com.daniel.server.commands;

import com.daniel.common.network.Response;
import com.daniel.common.person.Person;
import com.daniel.server.connection.ResponseFactory;
import com.daniel.server.util.CollectionHandler;


/**
 * Команда выводит коллекцию по убыванию
 */
public class PrintDescending {

    public Response execute(CollectionHandler collectionHandler) {
        if (collectionHandler.isEmpty()) {
            return ResponseFactory.createErrorResponse("Коллекция пуста!");
        }
        StringBuilder sb = new StringBuilder();
        for (Person p: collectionHandler.sortDescending()) {
            sb.append(p).append("\n");
        }
        return ResponseFactory.createOkResponse(sb.toString());
    }

}
