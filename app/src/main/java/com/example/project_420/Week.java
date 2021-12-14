package com.example.project_420;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Singleton class that handles the data for the week program.
 * @author Tino Behnen
 * @version 1.5
 */
public class Week
{
    private ArrayList<Movement> movements;
    private static Week instance;
    private boolean loaded = false;

    /**
     * Initializes the singleton object.
     * @return Singleton object.
     */
    public static Week getInstance()
    {
        if (instance == null)
            instance = new Week();

        return instance;
    }

    /**
     * Adds an object to the object array.
     * @param movement {@link Movement#Movement(Day, String, int, int, int, int)}
     */
    public void addMovement(Movement movement)
    {
        this.movements.add(movement);
    }

    /**
     * Clears the object array.
     */
    public void clear()
    {
        if (this.movements != null)
            this.movements.clear();
    }

    /**
     * Builds an object array from objects with specific weekday.
     * @param day {@link Day}.
     * @return Object array for specific weekday.
     */
    public ArrayList<Movement> getDay(Day day)
    {
        ArrayList<Movement> dayList = new ArrayList<>();

        for (Movement m : this.movements)
            if (m.getDay() == day)
                dayList.add(m);

        return dayList;
    }

    /**
     * Saves the object array into sharedPreferences in json text format.
     * @param context Context provided by the activity in use.
     */
    public void save(Context context)
    {
        SharedPreferences sharedPrefs = context.getSharedPreferences("Week Data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(this.movements);
        editor.putString("movements", json);
        editor.apply();

        loaded = false;
    }

    /**
     * Initialized the object array from a json format file in sharedPreferences.
     * @param context Context provided by the activity in use.
     */
    public void load(Context context)
    {
        if (!loaded) {
            SharedPreferences sharedPrefs = context.getSharedPreferences("Week Data", Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPrefs.getString("movements", null);
            Type type = new TypeToken<ArrayList<Movement>>() {
            }.getType();
            this.movements = gson.fromJson(json, type);

            if (this.movements == null)
                this.movements = new ArrayList<>();

            loaded = true;
        }
    }
}