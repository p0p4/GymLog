package com.example.project_420;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 *
 * @author Tino Behnen
 * @version 1.6
 */
public class Week
{
    private ArrayList<Movement> movements;
    private static Week instance;

    /**
     *
     * @return
     */
    public static Week getInstance()
    {
        if(instance == null)
        {
            instance = new Week();
        }
        return instance;
    }

    /**
     *
     * @param movement
     */
    public void addMovement(Movement movement)
    {
        this.movements.add(movement);
    }

    /**
     *
     */
    public void clear()
    {
        if (this.movements != null)
        this.movements.clear();
    }

    /**
     *
     * @return
     */
    public ArrayList<Movement> movements()
    {
        return this.movements;
    }

    /**
     *
     * @param day
     * @return
     */
    public ArrayList<Movement> getDay(Day day)
    {
        ArrayList<Movement> dayList = new ArrayList<>();

        for (Movement m : this.movements)
        {
            if (m.getDay() == day)
                dayList.add(m);
        }
        return dayList;
    }

    /**
     *
     * @param context
     */
    public void save(Context context)
    {
        SharedPreferences sharedPrefs = context.getSharedPreferences("Week Data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(this.movements);
        editor.putString("movements", json);
        editor.apply();
    }

    /**
     *
     * @param context
     */
    public void load(Context context)
    {
        SharedPreferences sharedPrefs = context.getSharedPreferences("Week Data", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPrefs.getString("movements", null);
        Type type = new TypeToken<ArrayList<Movement>>() {}.getType();
        this.movements = gson.fromJson(json, type);

        if (this.movements == null)
        {
            this.movements = new ArrayList<>();
        }
    }
}