package com.example.newproject.methods;

import java.sql.Date;
import java.sql.Timestamp;

public class item implements Comparable<item> {
    private Timestamp timestamp;

    public item(Timestamp timestamp) {
        this.timestamp = timestamp;
    }



    @Override
    public int compareTo(item o) {

            item another =(item) o;
            if (this.timestamp == another.timestamp){
                return 0;
            }else if (this.timestamp.after( another.timestamp)){
                return -1;
            }else {
                return 1;
            }

    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
