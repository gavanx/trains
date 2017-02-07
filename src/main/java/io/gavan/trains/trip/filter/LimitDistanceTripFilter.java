package io.gavan.trains.trip.filter;

import io.gavan.trains.model.Railroad;
import io.gavan.trains.model.Town;
import io.gavan.trains.service.IRailroadService;
import io.gavan.trains.trip.Trip;

/**
 * Created by gavan on 17-2-7.
 */
public class LimitDistanceTripFilter implements ITripFilter {
    private int lessThanDistance;
    private IRailroadService railroadService;
    private Railroad railroad;

    public LimitDistanceTripFilter(int lessThanDistance, IRailroadService railroadService, Railroad railroad) {
        this.lessThanDistance = lessThanDistance;
        this.railroadService = railroadService;
        this.railroad = railroad;
    }

    @Override
    public TripFilterResult accept(Trip trip, Town next) {
        int distance = trip.calculateDistance(next, this.railroadService, this.railroad);//TODO: cache last distance in trip to avoid duplate calculate
        boolean accept = distance < this.lessThanDistance;
        return new TripFilterResult(accept, !accept);
    }
}
