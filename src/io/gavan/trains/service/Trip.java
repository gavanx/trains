package io.gavan.trains.service;

import io.gavan.trains.model.Town;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by gavan on 17-2-7.
 */
public class Trip {
    private List<Town> towns;

    public Trip(Town from) {
        this.towns = new ArrayList<Town>();
        this.towns.add(from);
    }

    private Trip(List<Town> towns) {
        this.towns = towns;
    }

    public void add(Town next) {
        this.towns.add(next);
    }

    public Town getFrom() {
        return this.towns.get(0);
    }

    public Town getTo() {
        return this.towns.get(this.towns.size() - 1);
    }

    public int getStops() {
        return this.towns.size() - 1;
    }

    public Trip copy() {
        Trip t = new Trip(new ArrayList<Town>(this.towns));
        return t;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (Town town : this.towns) {
            sb.append(town.getId()).append('-');
        }
        return sb.toString();
    }
}
