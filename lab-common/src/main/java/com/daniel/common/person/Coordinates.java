package com.daniel.common.person;

import java.io.Serializable;

public class Coordinates implements Comparable<Coordinates>, Serializable {
    private static final long serialVersionUID = 8438362866765644205L;
    private Float x; //Значение поля должно быть больше -662, Поле не может быть null
    private long y; //Максимальное значение поля: 141

    public Coordinates(Float x, long y) {
        this.x = x;
        this.y = y;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" + "x=" + x + ", y=" + y + '}';
    }

    @Override
    public int compareTo(Coordinates o) {
        return Double.compare(x * y, o.getX() * o.getY());
    }
}
