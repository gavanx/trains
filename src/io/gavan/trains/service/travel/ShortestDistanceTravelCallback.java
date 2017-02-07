package io.gavan.trains.service.travel;

import io.gavan.trains.model.Railroad;
import io.gavan.trains.model.Town;
import io.gavan.trains.service.IRailroadService;
import io.gavan.trains.service.impl.RailroadService;
import io.gavan.trains.trip.Trip;

/**
 * Created by gavan on 17-2-7.
 */
public class ShortestDistanceTravelCallback implements ITravelCallback {
    private IRailroadService railroadService;
    private Railroad railroad;
    private int distance;

    public ShortestDistanceTravelCallback(RailroadService railroadService, Railroad railroad) {
        this.railroadService = railroadService;
        this.railroad = railroad;
        this.distance = IRailroadService.INVALID_DISTANCE_VALUE;
    }

    @Override
    public void onAccepted(Trip trip, Town next) {
        int d = this.getDistance(trip, next);
        System.out.println("debug shortest:" + trip + next + " " + d);//debug
        if (this.distance < 0 || this.distance > d) {
            this.distance = d;
        }
    }

    public int getDistance(Trip trip, Town next) {
        Town[] towns = trip.getAllTowns(next);
        int d = this.railroadService.getRouteDistance(this.railroad, towns);
        return d;
    }

    @Override
    public int getResult() {
        return this.distance;
    }
}
