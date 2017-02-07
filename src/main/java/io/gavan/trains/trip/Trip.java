package io.gavan.trains.trip;

import io.gavan.trains.model.Town;
import io.gavan.trains.model.Track;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gavan on 17-2-7.
 */
public class Trip {
    private List<Town> towns;
    private int distance = 0;

    public Trip(Town from) {
        this.towns = new ArrayList<Town>();
        this.towns.add(from);
    }

    private Trip(List<Town> towns) {
        this.towns = towns;
    }

    public void add(Track track) {
        this.towns.add(track.getTo());
        this.distance += track.getDistance();
    }

    public Town getFrom() {
        return this.towns.get(0);
    }

    public Town getTo() {
        return this.towns.get(this.towns.size() - 1);
    }

    public List<Town> getTowns() {
        return this.towns;
    }

    public int getStops() {
        return this.towns.size() - 1;
    }

    public Town[] getAllTowns(Town end) {//no use
        List<Town> list = this.towns;
        Town[] towns = new Town[list.size() + 1];
        towns = list.toArray(towns);
        towns[list.size()] = end;
        return towns;
    }

    public int calculateDistance(Track track) {
//        Town[] towns = this.getAllTowns(end);
//        return railroadService.getRouteDistance(railroad, towns);
        return this.distance + track.getDistance();
    }

    public Trip copy() {
        Trip t = new Trip(new ArrayList<Town>(this.towns));
        t.distance = this.distance;
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
