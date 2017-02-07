package io.gavan.trains.service.travel;

import io.gavan.trains.model.Track;
import io.gavan.trains.service.IRailroadService;
import io.gavan.trains.trip.Trip;

/**
 * Created by gavan on 17-2-7.
 */
public class ShortestDistanceTravelCallback implements ITravelCallback {
    private int distance;

    public ShortestDistanceTravelCallback() {
        this.distance = IRailroadService.INVALID_DISTANCE_VALUE;
    }

    @Override
    public void onAccepted(Trip trip, Track track) {
        int d = trip.calculateDistance(track);
//        System.out.println("debug shortest:" + trip + next + " " + d);//debug
        if (this.distance < 0 || this.distance > d) {
            this.distance = d;
        }
    }

    @Override
    public int getResult() {
        return this.distance;
    }
}
