package com.example.project_420;

import java.util.ArrayList;
import java.util.List;

public class Week {
    private List<Movement> movements;
    private static final Week instance = new Week();

    public static Week getInstance() {
        return instance;
    }

    private Week() {
        this.movements = new ArrayList<>();
    }

    public void addMovement(Movement movement) {
        movements.add(movement);
    }

    public List<Movement> movements() {
        return this.movements;
    }

    public ArrayList<Movement> getDay(Day day) {
        ArrayList dayList = new ArrayList<Movement>();

        for (Movement m : this.movements) {
            if (m.getDay() == day)
                dayList.add(m);
        }
        return dayList;
    }
}