package com.example.project_420;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tino Behnen
 * versio 1.4
 */

public class Week
{
    private List<Movement> movements;
    private static final Week instance = new Week();
    private String name;

    public static Week getInstance()
    {
        return instance;
    }

    private Week()
    {
        this.movements = new ArrayList<>();
    }

    public void addMovement(Movement movement)
    {
        this.movements.add(movement);
    }

    public void clearWeek()
    {
        this.movements.clear();
    }

    public void setWeekName(String name)
    {
        this.name = name;
    }

    public List<Movement> movements()
    {
        return this.movements;
    }

    public ArrayList<Movement> getDay(Day day)
    {
        ArrayList dayList = new ArrayList<Movement>();

        for (Movement m : this.movements)
        {
            if (m.getDay() == day)
                dayList.add(m);
        }
        return dayList;
    }

    public String getWeekName()
    {
        return this.name;
    }
}