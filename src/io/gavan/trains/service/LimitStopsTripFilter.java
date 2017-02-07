package io.gavan.trains.service;

import io.gavan.trains.model.Town;

/**
 * Created by gavan on 17-2-7.
 */
public class LimitStopsTripFilter implements ITripFilter {
    private int maxStops;
    private int minStops;

    public LimitStopsTripFilter(int maxStops, int minStops) {
        this.maxStops = maxStops;
        this.minStops = minStops;
    }

    public LimitStopsTripFilter(int exactStops) {
        this(exactStops, exactStops);
    }

    @Override
    public TripFilterResult accept(Trip trip, Town next) {
        int stops = trip.getStops() + 1;
        return new TripFilterResult(stops >= this.minStops && stops <= this.maxStops, stops >= this.maxStops);
    }
}
