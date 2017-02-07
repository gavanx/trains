package io.gavan.trains.trip.filter;

import io.gavan.trains.model.Town;
import io.gavan.trains.service.impl.RailroadService;
import io.gavan.trains.service.travel.ITravelCallback;
import io.gavan.trains.service.travel.ShortestDistanceTravelCallback;
import io.gavan.trains.trip.Trip;

import java.util.List;

/**
 * Created by gavan on 17-2-7.
 */
public class ShortestDistanceTripFilter implements ITripFilter {
    private ShortestDistanceTravelCallback travelCallback;

    public ShortestDistanceTripFilter(ShortestDistanceTravelCallback travelCallback) {
        this.travelCallback = travelCallback;
    }

    @Override
    public TripFilterResult accept(Trip trip, Town next) {//TODO: refactor: CompositeTripFilter with NoDuplicateTripFilter
        List<Town> towns = trip.getTowns();
        boolean duplicate = towns.size() > 1 && towns.subList(1, towns.size() - 1).contains(next);
        if (!duplicate) {
            int minDistance = this.travelCallback.getResult();
            if (minDistance > 0) {
                int distance = this.travelCallback.getDistance(trip, next);
                if (distance > minDistance) {//branch cutting of larger distance
                    duplicate = true;
                }
            }
        }
        return new TripFilterResult(!duplicate, duplicate);
    }
}
