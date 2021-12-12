package com.example.project_420;

/**
 *
 * @author Tino Behnen
 * @version 1.2
 */

enum Day
{
    MON, TUE, WED, THU, FRI, SAT, SUN
}

public class Movement
{
    private final Day day;
    private final String name;
    private final int sets, reps, weight, duration;

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

    @Override
    public String toString()
    {
        if (getWeight() == 0 && getDuration() == 0)
        {
            return this.name + "\nSets: " + this.sets + ",  Reps: " + this.reps;
        }
        else if (getDuration() == 0)
        {
            return this.name + "\nSets: " + this.sets + ",  Reps: " + this.reps + ",  Weight: " + this.weight + "kg";
        }
        else if (getWeight() == 0)
        {
            return this.name + "\nSets: " + this.sets + ",  Reps: " + this.reps + ",  Duration: " + this.duration + "s";
        }
        else
        {
            return this.name + "\nSets: " + this.sets + ",  Reps: " + this.reps + ",  Weight: " + this.weight + "kg,  Duration: " + this.duration + "s";
        }
    }
}