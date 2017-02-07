package io.gavan.trains.trip.filter;

import io.gavan.trains.model.Town;
import io.gavan.trains.trip.Trip;

import java.util.List;

/**
 * Created by gavan on 17-2-7.
 */
public class NoDuplicateTripFilter implements ITripFilter {
    @Override
    public TripFilterResult accept(Trip trip, Town next) {
        List<Town> towns = trip.getTowns();
        boolean duplicate = towns.size() > 1 && towns.subList(1, towns.size() -1).contains(next);
        return new TripFilterResult(!duplicate, duplicate);
    }
}
