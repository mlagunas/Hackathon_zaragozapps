package com.example.mlagunas.hackaton_app.Objects;

import com.example.mlagunas.hackaton_app.Objects.Animal;

import java.util.ArrayList;

/**
 * Created by mlagunas on 11/07/15.
 */
public class AnimalRequest {

    private Integer count;
    private ArrayList<Animal> rows = new ArrayList<>();

    public AnimalRequest(){
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ArrayList<Animal> getRows() {
        return rows;
    }

    public void setRows(ArrayList<Animal> rows) {
        this.rows = rows;
    }
}

