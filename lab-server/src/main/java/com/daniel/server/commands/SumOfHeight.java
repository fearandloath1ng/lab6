package com.daniel.server.commands;

import com.daniel.common.network.Response;
import com.daniel.common.person.Person;
import com.daniel.server.connection.ResponseFactory;
import com.daniel.server.util.CollectionHandler;

import java.util.Map;

/**
 * Команда выводит сумму полей height всех элементов коллекции
 */
public class SumOfHeight {

    public Response execute(CollectionHandler collectionHandler) {
        if (collectionHandler.isEmpty()) {
            return ResponseFactory.createErrorResponse("Коллекция пуста!");
        }
        int sum = 0;
        for (Map.Entry<Integer, Person> entry: collectionHandler.getMap().entrySet()) {
            sum += entry.getValue().getHeight();
        }
        return ResponseFactory.createOkResponse("Сумма значений поля height - " + sum);
    }

}
