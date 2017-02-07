package io.gavan.trains.trip.filter;

import io.gavan.trains.model.Track;
import io.gavan.trains.trip.Trip;

/**
 * Created by gavan on 17-2-7.
 */
public class LimitDistanceTripFilter implements ITripFilter {
    private int lessThanDistance;

    public LimitDistanceTripFilter(int lessThanDistance) {
        this.lessThanDistance = lessThanDistance;
    }

    @Override
    public TripFilterResult accept(Trip trip, Track track) {
        int distance = trip.calculateDistance(track);
        boolean accept = distance < this.lessThanDistance;
        return new TripFilterResult(accept, !accept);
    }
}
