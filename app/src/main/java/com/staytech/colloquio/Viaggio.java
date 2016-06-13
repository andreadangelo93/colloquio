package com.staytech.colloquio;

/**
 * Created by andrea on 13/06/16.
 */
public class Viaggio {

    String id;
    String name;
    String starting_date;
    String ending_date;

    public Viaggio(String id, String name, String starting_date, String ending_date) {
        this.id = id;
        this.name = name;
        this.starting_date = starting_date;
        this.ending_date = ending_date;

    }


    public String getId() {

        return id;
    }
    public String getName() {
        return name;
    }

    public String getStarting_date() {
        return starting_date;
    }

    public String getEnding_date() {
        return ending_date;
    }
}
