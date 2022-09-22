package com.daniel.server.commands;

import com.daniel.common.network.Response;
import com.daniel.common.person.Person;
import com.daniel.server.connection.ResponseFactory;
import com.daniel.server.util.CollectionHandler;

import java.util.Map;

/**
 * Команда обновляет поля элемента по его id
 */
public class Update {

    public Response execute(CollectionHandler collectionHandler, int arg, Person p) {
        if (collectionHandler.isEmpty()) {
            return ResponseFactory.createErrorResponse("Коллекция пуста!");
        }
        Person upd = null;
        for (Map.Entry<Integer, Person> entry: collectionHandler.getMap().entrySet()) {
            if (entry.getValue().getId() == arg) {
                upd = entry.getValue();
            }
        }
        if (upd == null) {
            return ResponseFactory.createErrorResponse("Элемента с заданным id не существует!");
        }
        p.setName(p.getName());
        p.setCoordinates(p.getCoordinates());
        p.setHeight(p.getHeight());
        p.setBirthday(p.getBirthday());
        p.setWeight(p.getWeight());
        p.setHairColor(p.getHairColor());
        p.setLocation(p.getLocation());
        return ResponseFactory.createOkResponse("Вы успешно обновили поля элемента!");
    }

}
