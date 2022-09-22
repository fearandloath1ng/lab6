package com.daniel.common.util;

/**
 * Класс для проверки полей на соответствие требованиям (ограничениям)
 */
public class RequirementsChecker {

    private final int xLIMIT = -662;
    private final int yLIMIT = 141;

    public boolean checkHeight(int height) {
        if (height <= 0) {
            System.out.println("Поле height должно быть больше 0!");
        }
        return height > 0;
    }

    public boolean checkWeight(float weight) {
        if (weight <= 0) {
            System.out.println("Поле weight должно быть больше 0!");
        }
        return weight > 0;
    }

    public boolean checkX(Float x) {
        if (x <= xLIMIT) {
            System.out.println("Поле x должно быть больше -662!");
        }
        return x > xLIMIT;
    }

    public boolean checkY(long y) {
        if (y > yLIMIT) {
            System.out.println("Максимальное значение поля Y - 141!");
        }
        return y <= yLIMIT;
    }

}
