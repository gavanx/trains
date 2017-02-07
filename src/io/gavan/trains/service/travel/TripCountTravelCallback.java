package io.gavan.trains.service.travel;

import io.gavan.trains.model.Town;
import io.gavan.trains.trip.Trip;

/**
 * Created by gavan on 17-2-7.
 */
public class TripCountTravelCallback implements ITravelCallback {
    private int tripCount = 0;

    @Override
    public void onAccepted(Trip trip, Town next) {
        this.tripCount++;
        //TODO: cache the trip result if need to print the route
        System.out.println("debug tripCount:" + trip + next);//debug
    }

    @Override
    public int getResult() {
        return this.tripCount;
    }
}
