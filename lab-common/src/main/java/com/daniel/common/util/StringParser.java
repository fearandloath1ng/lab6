package com.daniel.common.util;

import com.daniel.common.person.Color;
import com.daniel.common.person.Coordinates;
import com.daniel.common.person.Location;
import com.daniel.common.person.Person;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * Класс для преобразования строковых данных к нужному типу
 */
public class StringParser {

    private final int dateLength = 3;

    public Person toPerson(String name, String sX, String sY, String sHeight, String sBirthday, String sWeight,
                           String sColor) {
        if ("".equals(name) || "".equals(sX) || "".equals(sY) || "".equals(sHeight) || "".equals(sBirthday)
                || "".equals(sWeight)) {
            return null;
        }
        Float x = toFloat(sX);
        Long y = toLong(sY);
        Integer height = toInt(sHeight);
        LocalDateTime birthday = toLocalDateTime(sBirthday);
        Float weight = toFloat(sWeight);
        if (x == null || y == null || height == null || birthday == null || weight == null) {
            return null;
        }
        Color color;
        if ("".equals(sColor)) {
            color = null;
        } else {
            color = toColor(sColor);
            if (color == null) {
                return null;
            }
        }
        return new Person(name, new Coordinates(x, y), height, birthday, weight, color);
    }

    public Location toLocation(String sLocX, String sLocY, String sLocZ, String locName) {
        Integer locX = toInt(sLocX);
        Integer locY = toInt(sLocY);
        Long z = toLong(sLocZ);
        if (locX == null || locY == null || z == null || "".equals(locName)) {
            return null;
        }
        return new Location(locX, locY, z, locName);
    }

    public Integer toInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.out.println("Значение должно быть целым числом!");
        }
        return null;
    }

    public Long toLong(String s) {
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            System.out.println("Значение должно быть целым числом!");
        }
        return null;
    }

    public Float toFloat(String s) {
        try {
            return Float.parseFloat(s);
        } catch (NumberFormatException e) {
            System.out.println("Значение должно быть числом с плавающей запятой!");
        }
        return null;
    }

    public LocalDateTime toLocalDateTime(String s) {
        String[] ymd = s.trim().split(" ");
        if (ymd.length != dateLength || toInt(ymd[0]) == null || toInt(ymd[1]) == null || toInt(ymd[2]) == null) {
            System.out.println("Дата должна быть введена в формате DD MM YYYY!");
            return null;
        }
        try {
            return LocalDateTime.of(toInt(ymd[2]), toInt(ymd[1]), toInt(ymd[0]), 0, 0);
        } catch (DateTimeException e) {
            System.out.println("Неверные номера года, месяца или дня!");
        }
        return null;
    }

    public Color toColor(String s) {
        try {
            return Color.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Значение должно быть green, yellow или orange !");
        }
        return null;
    }

}
