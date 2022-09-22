package com.daniel.server.commands;

import com.daniel.common.network.Response;
import com.daniel.common.person.Person;
import com.daniel.server.connection.ResponseFactory;
import com.daniel.server.util.CollectionHandler;

import java.util.ArrayList;

/**
 * Команда, выводящая максимальное значение поля coordinates
 */
public class MaxByCoordinates {

    public Response execute(CollectionHandler collectionHandler) {
        if (collectionHandler.isEmpty()) {
            return ResponseFactory.createErrorResponse("Коллекция пуста!");
        }
        ArrayList<Person> sorted = collectionHandler.sortByCoordinates();
        Person p = sorted.get(sorted.size() - 1);
        return ResponseFactory.createErrorResponse("Элемент с максимальным coordinates:\n" + p);
    }

}
