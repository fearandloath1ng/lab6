package com.daniel.common.person;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Person implements Comparable<Person>, Serializable {

    private static final long serialVersionUID = 8986456915233799263L;
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int height; //Значение поля должно быть больше 0
    private LocalDateTime birthday; //Поле может быть null
    private float weight; //Значение поля должно быть больше 0
    private Color hairColor; //Поле может быть null
    private Location location; //Поле может быть null

    public Person(String name, Coordinates coordinates, int height, LocalDateTime birthday, float weight,
                  Color hairColor, Location location) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = new Date();
        this.height = height;
        this.birthday = birthday;
        this.weight = weight;
        this.hairColor = hairColor;
        this.location = location;
    }

    public Person(String name, Coordinates coordinates, int height, LocalDateTime birthday,
                  float weight, Color hairColor) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = new Date();
        this.height = height;
        this.birthday = birthday;
        this.weight = weight;
        this.hairColor = hairColor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }
        Person person = (Person) o;
        return getId() == person.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name='" + name + '\'' + ", coordinates=" + coordinates
                + ", creationDate=" + creationDate + ", height=" + height + ", birthday=" + birthday
                + ", weight=" + weight + ", hairColor=" + hairColor + ", location=" + location + '}';
    }

    @Override
    public int compareTo(Person p) {
        if (getHeight() == p.getHeight()) {
            if (getLocation() == null && p.getLocation() != null) {
                return -1;
            }
            if (getLocation() != null && p.getLocation() != null) {
                return Integer.compare(getLocation().getX(), p.getLocation().getX());
            }
            return 1;
        }
        return Integer.compare(getHeight(), p.getHeight());
    }
}
