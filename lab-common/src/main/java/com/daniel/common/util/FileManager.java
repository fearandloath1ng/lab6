package com.daniel.common.util;

import com.daniel.common.person.Location;
import com.daniel.common.person.Person;
import com.daniel.common.util.RequirementsChecker;
import com.daniel.common.util.StringParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileManager {

    private final int allFIELDS = 14;
    private final int noLocFIELDS = 10;
    private final int noColNoLocFIELDS = 9;

    private final int nameINDEX = 2;
    private final int xINDEX = 3;
    private final int yINDEX = 4;
    private final int heightINDEX = 6;
    private final int birthINDEX = 7;
    private final int weightINDEX = 8;
    private final int colorINDEX = 9;
    private final int xLocINDEX = 10;
    private final int yLocINDEX = 11;
    private final int zLocINDEX = 12;
    private final int nameLocINDEX = 13;

    private File file;
    private final StringParser parser = new StringParser();
    private final RequirementsChecker checker = new RequirementsChecker();

    public FileManager(String filePath) {
        file = new File(filePath);
    }

    public FileManager() {}

    /**
     * Проверка файла на существования и права
     * @param f файл для проверки
     * @throws FileNotFoundException в случае отсутствия файла
     */
    public void checkFile(File f) throws FileNotFoundException {
        if (!f.exists()) {
            throw new FileNotFoundException("Файл не найден или не существует!");
        }
        if (!f.canRead() || !f.canWrite()) {
            throw new SecurityException("Отсутствуют права не чтение/запись файла!");
        }
    }

    /**
     * Чтение и парсинг коллекции из csv-файла
     * @return коллекция из файла
     * @throws IOException в случае ошибок в файле или потоках ввода/вывода
     */
    public LinkedHashMap<Integer, Person> fromFile() throws IOException {
        checkFile(file);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
        LinkedHashMap<Integer, Person> map = new LinkedHashMap<>();
        StringBuilder sb = new StringBuilder();
        char[] chars = new char[(int) file.length()];
        reader.read(chars);
        reader.close();
        sb.append(chars);
        String[] elements = sb.toString().trim().split("\n");
        int count = 0;
        for (String s: elements) {
            String[] fields = s.trim().split("\\|");
            Integer key = parser.toInt(fields[0]);
            if (key == null) {
                System.out.println("Неверный ключ! Элемент добавлен не будет!");
                continue;
            }
            Person p = getElement(fields);
            if (p != null) {
                count++;
                p.setId(count);
                map.put(key, p);
            }
        }
        return map;
    }

    private Person getElement(String[] fields) {
        Person p;
        Location l = null;
        if (fields.length == allFIELDS) {
            p = parser.toPerson(fields[nameINDEX], fields[xINDEX], fields[yINDEX], fields[heightINDEX],
                    fields[birthINDEX], fields[weightINDEX], fields[colorINDEX]);
            l = parser.toLocation(fields[xLocINDEX], fields[yLocINDEX], fields[zLocINDEX], fields[nameLocINDEX]);
        } else if (fields.length == noLocFIELDS) {
            p = parser.toPerson(fields[nameINDEX], fields[xINDEX], fields[yINDEX], fields[heightINDEX],
                    fields[birthINDEX], fields[weightINDEX], fields[colorINDEX]);
        } else if (fields.length == noColNoLocFIELDS) {
            p = parser.toPerson(fields[nameINDEX], fields[xINDEX], fields[yINDEX], fields[heightINDEX],
                    fields[birthINDEX], fields[weightINDEX], "");
        } else {
            System.out.println("Неверное количество полей элемента! Элемент добавлен не будет!");
            return null;
        }
        if (p == null) {
            System.out.println("Неверные данные! Элемент добавлен не будет!");
            return null;
        }
        if (!checker.checkX(p.getCoordinates().getX()) || !checker.checkY(p.getCoordinates().getY())
                || !checker.checkHeight(p.getHeight()) || !checker.checkWeight(p.getWeight())) {
            System.out.println("Поля элемента не соответствуют требованиям! Элемент добавлен не будет!");
            return null;
        }
        if (l != null) {
            p.setLocation(l);
        }
        return p;
    }

    /**
     * Представление элементов коллекции в csv-формате и запись в файл
     * @param map коллекция для записи
     * @throws IOException в случае ошибок в файле или потоках ввода/вывода
     */
    public void toFile(LinkedHashMap<Integer, Person> map) throws SecurityException, FileNotFoundException {
        checkFile(file);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Person> entry: map.entrySet()) {
            Person p = entry.getValue();
            sb.append(entry.getKey()).append("|").append(p.getId()).append("|").append(p.getName()).append("|")
                    .append(p.getCoordinates().getX()).append("|").append(p.getCoordinates().getY()).append("|")
                    .append(p.getCreationDate()).append("|").append(p.getHeight()).append("|")
                    .append(p.getBirthday().getDayOfMonth()).append(" ").append(p.getBirthday().getMonthValue())
                    .append(" ").append(p.getBirthday().getYear()).append("|").append(p.getWeight());
            if (p.getHairColor() == null) {
                if (p.getLocation() != null) {
                    sb.append("||").append(p.getLocation().getX()).append("|").append(p.getLocation().getY())
                            .append("|").append(p.getLocation().getZ()).append("|").append(p.getLocation().getName());
                }
            } else {
                sb.append("|").append(p.getHairColor());
                if (p.getLocation() != null) {
                    sb.append("|").append(p.getLocation().getX()).append("|").append(p.getLocation().getY())
                            .append("|").append(p.getLocation().getZ()).append("|").append(p.getLocation().getName());
                }
            }
            sb.append("\n");
            try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
                out.write(sb.toString().getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
