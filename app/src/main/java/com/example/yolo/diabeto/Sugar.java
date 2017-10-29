package com.example.yolo.diabeto;

import java.sql.Time;
import java.util.Date;

/**
 * Created by abhinav on 29/10/17.
 */

public class Sugar {
    public String sugar;
    public String date;

    public Sugar() {
            // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Sugar(String sugar,String date) {
        this.sugar=sugar;
        this.date=date;
    }

}
