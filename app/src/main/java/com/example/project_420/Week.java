package com.example.project_420;

import java.util.ArrayList;

public class Week
{
    private String name, description;
    private ArrayList week;

    public Week(String name, String description)
    {
        this.name = name;
        this.description = description;
        week = new ArrayList<Movement>();
    }

    public void addMovement(Movement movement)
    {
        week.add(movement);
    }

    /*public ArrayList getDay(Day day)
    {
        ArrayList dayList = new ArrayList<Movement>();

        for (Movement m : this.week){
            if (m.getDay() == day)
                dayList.add(m);
        }
        return dayList;
    }*/
}