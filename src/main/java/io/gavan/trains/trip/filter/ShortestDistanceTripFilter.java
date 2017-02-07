package io.gavan.trains.trip.filter;

import io.gavan.trains.model.Town;
import io.gavan.trains.model.Track;
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
    public TripFilterResult accept(Trip trip, Track track) {//TODO: refactor: CompositeTripFilter with NoDuplicateTripFilter
        List<Town> towns = trip.getTowns();
        boolean duplicate = towns.size() > 1 && towns.subList(1, towns.size() - 1).contains(track.getTo());
        if (!duplicate) {
            int minDistance = this.travelCallback.getResult();
            if (minDistance > 0) {
                int distance = trip.calculateDistance(track);
                if (distance > minDistance) {//branch cutting of larger distance
                    duplicate = true;
                }
            }
        }
        return new TripFilterResult(!duplicate, duplicate);
    }
}
