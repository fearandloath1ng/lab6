package com.daniel.common.person;

import java.io.Serializable;

public class Location implements Serializable {
    private static final long serialVersionUID = -6403294230871511967L;
    private Integer x; //Поле не может быть null
    private int y;
    private Long z; //Поле не может быть null
    private String name; //Строка не может быть пустой, Поле не может быть null

    public Location(Integer x, int y, Long z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Long getZ() {
        return z;
    }

    public void setZ(Long z) {
        this.z = z;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Location{" + "x=" + x + ", y=" + y + ", z=" + z + ", name='" + name + '\'' + '}';
    }
}
