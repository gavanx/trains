package io.gavan.trains.trip.filter;

import io.gavan.trains.model.Town;
import io.gavan.trains.trip.Trip;

/**
 * Created by gavan on 17-2-7.
 */
public interface ITripFilter {
    TripFilterResult accept(Trip trip, Town next);
}
