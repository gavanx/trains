package io.gavan.trains.model;

/**
 * Created by gavan on 17-2-6.
 */
public class Track {
    private Town from;
    private Town to;
    private int distance;

    public Track(Town from, Town to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public Town getFrom() {
        return from;
    }

    public Town getTo() {
        return to;
    }


    public int getDistance() {
        return distance;
    }
}
