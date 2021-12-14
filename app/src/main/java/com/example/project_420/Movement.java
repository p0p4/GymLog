package com.example.project_420;

/**
 * Enum for weekdays.
 * @author Tino Behnen
 * @version 1.0
 */
enum Day
{
    MON, TUE, WED, THU, FRI, SAT, SUN
}

/**
 * Object that encapsulates instructions for an exercise movement.
 * @author Tino Behnen
 * @version 1.2
 */
public class Movement
{
    private final Day day;
    private final String name;
    private final int sets, reps, weight, duration;

    /**
     * Constructor for all initial settings.
     * @param day      Day of the week.
     * @param name     Name of the exercise.
     * @param sets     Number of sets to perform.
     * @param reps     Number of reps to perform.
     * @param weight   Amount of weight to be used.
     * @param duration How long the movement should last.
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
     * Getter for weekday.
     * @return Weekday of the exercise.
     */
    public Day getDay()
    {
        return day;
    }

    /**
     * Getter for name.
     * @return Name of the exercise.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Getter for weight.
     * @return Amount of weight used in the exercise.
     */
    public int getWeight()
    {
        return weight;
    }

    /**
     * Getter for duration.
     * @return Movement duration.
     */
    public int getDuration()
    {
        return duration;
    }

    /**
     * All needed toString variants for our use case.
     * @return Instructions for the whole movement.
     */
    @Override
    public String toString()
    {
        if (getWeight() == 0 && getDuration() == 0)
            return this.name + "\nSets: " + this.sets + ", Reps: " + this.reps;
        else if (getDuration() == 0)
            return this.name + "\nSets: " + this.sets + ", Reps: " + this.reps + ", Weight: " + this.weight + "kg";
        else if (getWeight() == 0)
            return this.name + "\nSets: " + this.sets + ", Reps: " + this.reps + ", Duration: " + this.duration + "s";
        else
            return this.name + "\nSets: " + this.sets + ", Reps: " + this.reps + ", Weight: " + this.weight + "kg, Duration: " + this.duration + "s";
    }
}