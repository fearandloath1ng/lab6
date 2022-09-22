package com.daniel.server.util;

import com.daniel.common.person.Person;

import java.util.LinkedHashMap;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;

public class CollectionHandler {

    private final LinkedHashMap<Integer, Person> map;
    private final Date initDate;

    public CollectionHandler(LinkedHashMap<Integer, Person> map) {
        this.map = map;
        initDate = new Date();
    }

    public LinkedHashMap<Integer, Person> getMap() {
        return map;
    }

    public Person get(Integer key) {
        return map.get(key);
    }

    public int getNextId() {
        if (map.isEmpty()) {
            return 1;
        }
        ArrayList<Person> sorted = new ArrayList<>(map.values());
        sorted.sort(Comparator.comparing(Person::getId));
        return sorted.get(sorted.size() - 1).getId() + 1;
    }

    public Date getInitDate() {
        return initDate;
    }

    public int getSize() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean containsKey(Integer key) {
        return map.containsKey(key);
    }

    public void clear() {
        map.clear();
    }

    public ArrayList<Person> sortByCoordinates() {
        ArrayList<Person> sorted = new ArrayList<>(map.values());
        sorted.sort(Comparator.comparing(Person::getCoordinates));
        return sorted;
    }

    public ArrayList<Person> sortDescending() {
        ArrayList<Person> sorted = new ArrayList<>(map.values());
        sorted.sort(Collections.reverseOrder());
        return sorted;
    }

    public int removeGreater(Person p) {
        LinkedList<Integer> keys = new LinkedList<>();
        for (Map.Entry<Integer, Person> entry : map.entrySet()) {
            if (entry.getValue().compareTo(p) > 0) {
                keys.add(entry.getKey());
            }
        }
        for (Integer i : keys) {
            map.remove(i);
        }
        return keys.size();
    }

    public int removeGreater(Integer key) {
        LinkedList<Integer> keys = new LinkedList<>();
        for (Map.Entry<Integer, Person> entry : map.entrySet()) {
            if (entry.getKey() > key) {
                keys.add(entry.getKey());
            }
        }
        for (Integer i : keys) {
            map.remove(i);
        }
        return keys.size();
    }

    public void insert(Integer key, Person p) {
        map.put(key, p);
    }

    public void remove(Integer key) {
        map.remove(key);
    }

}
