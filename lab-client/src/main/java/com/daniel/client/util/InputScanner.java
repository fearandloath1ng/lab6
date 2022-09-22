package com.daniel.client.util;

import com.daniel.common.person.Color;
import com.daniel.common.person.Coordinates;
import com.daniel.common.person.Location;
import com.daniel.common.person.Person;
import com.daniel.common.util.RequirementsChecker;
import com.daniel.common.util.StringParser;

import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Класс для ввода полей объекта с консоли
 */
public class InputScanner {
    private final Scanner scanner = new Scanner(System.in);
    private final StringParser parser = new StringParser();
    private final RequirementsChecker checker = new RequirementsChecker();

    private String scan(String field) {
        while (true) {
            System.out.println("Введите " + field + ":");
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!"".equals(line)) {
                    return line;
                }
            }
        }
    }

    public Person scanPerson() {
        String name = scanName();
        Coordinates coordinates = scanCoordinates();
        int height = scanHeight();
        LocalDateTime birthday = scanBirthday();
        float weight = scanWeight();
        Color hairColor = scanHairColor();
        Location location = scanLocation();
        return new Person(name, coordinates, height, birthday, weight, hairColor, location);
    }

    public String scanName() {
        return scan("name");
    }

    public Coordinates scanCoordinates() {
        Float x = null;
        boolean passed = false;
        while (x == null || !passed) {
            x = parser.toFloat(scan("X (>-662)"));
            if (x != null) {
                passed = checker.checkX(x);
            }
        }
        Long y = null;
        passed = false;
        while (y == null || !passed) {
            y = parser.toLong(scan("Y (<=141)"));
            if (y != null) {
                passed = checker.checkY(y);
            }
        }
        return new Coordinates(x, y);
    }

    public int scanHeight() {
        Integer h = null;
        boolean passed = false;
        while (h == null || !passed) {
            h = parser.toInt(scan("height (>0)"));
            if (h != null) {
                passed = checker.checkHeight(h);
            }
        }
        return h;
    }

    public LocalDateTime scanBirthday() {
        LocalDateTime b = null;
        while (b == null) {
            b = parser.toLocalDateTime(scan("birthday (в формате DD MM YYYY)"));
        }
        return b;
    }

    public float scanWeight() {
        Float w = null;
        boolean passed = false;
        while (w == null || !passed) {
            w = parser.toFloat(scan("weight (>0)"));
            if (w != null) {
                passed = checker.checkWeight(w);
            }
        }
        return w;
    }

    public Color scanHairColor() {
        Color hc = null;
        while (hc == null) {
            System.out.println("Введите поле hairColor (green, yellow, orange или ничего):");
            String line = scanner.nextLine();
            if ("".equals(line)) {
                return null;
            }
            hc = parser.toColor(line);
        }
        return hc;
    }

    public Location scanLocation() {
        String ans = "";
        while (!"YES".equalsIgnoreCase(ans) && !"NO".equalsIgnoreCase(ans)) {
            ans = scan("yes, если хотите указать location, или no, если не хотите");
        }
        if ("NO".equalsIgnoreCase(ans)) {
            return null;
        }
        Integer x = null;
        while (x == null) {
            x = parser.toInt(scan("location X"));
        }
        Integer y = null;
        while (y == null) {
            y = parser.toInt(scan("location Y"));
        }
        Long z = null;
        while (z == null) {
            z = parser.toLong(scan("location Z"));
        }
        return new Location(x, y, z, scan("location name"));
    }

}
