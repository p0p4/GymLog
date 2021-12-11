package com.example.project_420;

/**
 *
 * @author Tino Behnen
 * version 1.0
 */
enum Day
{
    MON, TUE, WED, THU, FRI, SAT, SUN
}

/**
 *
 * @author Tino Behnen
 * @version 1.3
 */
public class Movement
{
    private Day day;
    private String name;
    private int sets, reps, weight, duration;

    /**
     *
     * @param day
     * @param name
     * @param sets
     * @param reps
     * @param weight
     * @param duration
     */
    public Movement(Day day, String name, int sets, int reps, int weight, int duration)
    {
        this.day = day;
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
        this.duration = duration;
    }

    /**
     *
     * @return
     */
    public Day getDay()
    {
        return day;
    }

    /**
     *
     * @return
     */
    public String getName()
    {
        return name;
    }

    public int getSets()
    {
        return sets;
    }

    /**
     *
     * @return
     */
    public int getReps()
    {
        return reps;
    }

    /**
     *
     * @return
     */
    public int getWeight()
    {
        return weight;
    }

    /**
     *
     * @return
     */
    public int getDuration()
    {
        return duration;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        if(this.weight == 0 && this.duration == 0)
        {
            return this.name + "\nSets: " + this.sets + ",  Reps: " + this.reps;
        }
        else if (this.weight == 0)
        {
            return this.name + "\nSets: " + this.sets + ",  Reps: " + this.reps + ",  Duration: " + this.duration + "s";
        }
        else if (this.duration == 0)
        {
            return this.name + "\nSets: " + this.sets + ",  Reps: " + this.reps + ",  Weight: " + this.weight + "kg";
        }
        else
        {
            return this.name + "\nSets: " + this.sets + ",  Reps: " + this.reps + ",  Weight: " + this.weight + "kg,  Duration: " + this.duration + "s";
        }
    }
}