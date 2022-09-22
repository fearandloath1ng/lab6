package com.daniel.server.commands;

import com.daniel.common.network.Response;
import com.daniel.common.person.Person;
import com.daniel.server.connection.ResponseFactory;
import com.daniel.server.util.CollectionHandler;


/**
 * Команда обновляет элемент по ключу, если он меньше старого элемента
 */
public class ReplaceIfLower {

    public Response execute(CollectionHandler collectionHandler, Integer key, Person p) {
        if (collectionHandler.isEmpty()) {
            return ResponseFactory.createErrorResponse("Коллекция пуста!");
        }
        if (!collectionHandler.containsKey(key)) {
            return ResponseFactory.createErrorResponse("Элемента с данным ключом не существует!");
        }
        Person upd = collectionHandler.get(key);
        if (upd.compareTo(p) > 0) {
            upd.setName(p.getName());
            upd.setCoordinates(p.getCoordinates());
            upd.setHeight(p.getHeight());
            upd.setBirthday(p.getBirthday());
            upd.setWeight(p.getWeight());
            upd.setHairColor(p.getHairColor());
            upd.setLocation(p.getLocation());
            return ResponseFactory.createOkResponse("Вы успешно обновили поля элемента!");
        } else {
            return ResponseFactory.createOkResponse("Новое значение не меньше старого! Элемент не будет изменен.");
        }
    }

}
