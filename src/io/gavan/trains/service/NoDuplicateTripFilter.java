package io.gavan.trains.service;

import io.gavan.trains.model.Town;

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
