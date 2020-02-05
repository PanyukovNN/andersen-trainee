package com.company.secondtask;

/**
 * Необходимо выполнить рефакторинг существующего кода - исправить все места, которые вам покажутся некрасивыми или не
 * правильными. Можно изменять этот класс и/или добавлять новые. Результатом работы должен быть код, который будет прост
 * и понятен, без логических ошибок. Так же необходимо предусмотреть возможность дальнейшего расширения функционала -
 * могут быть добавлены как новые команды для трактора (новые способы движения, вращения), так и новые "фигуры", которые
 * могут двигатся совершенно в другой системе координат.
 */
public class Main {

    public static void main(String[] args) {
        Field field = new Field();
        field.initRectangleField(5);
        Position startTractorPosition = new Position(0, 0);
        Orientation startTractorOrientation = Orientation.NORTH;
        Figure tractor = new Tractor(field, startTractorPosition, startTractorOrientation);

        for (int i = 0; i < 3; i++) {
            tractor.move("F");
        }
        System.out.println(tractor.position);
    }
}
