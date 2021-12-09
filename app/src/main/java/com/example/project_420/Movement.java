package com.example.project_420;

/**
 *
 * @author Tino Behnen
 * @version 1.1
 */

enum Day
{
    MON, TUE, WED, THU, FRI, SAT, SUN, END
}

public class Movement
{
    private Day day;
    private String name;
    private int sets, reps, weight, duration;

    public Movement(Day day, String name, int sets, int reps, int weight, int duration)
    {
        this.day = day;
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.duration = duration;
    }

    public Day getDay()
    {
        return day;
    }

    public String getName()
    {
        return name;
    }

    public int getSets()
    {
        return sets;
    }

    public int getReps()
    {
        return reps;
    }

    public int getWeight()
    {
        return weight;
    }

    public int getDuration()
    {
        return duration;
    }
}