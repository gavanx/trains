package io.gavan.trains.service.impl;

import io.gavan.trains.model.Railroad;
import io.gavan.trains.model.Town;
import io.gavan.trains.model.Track;
import io.gavan.trains.service.IRailroadService;
import io.gavan.trains.service.ITripFilter;
import io.gavan.trains.service.Trip;
import io.gavan.trains.service.TripFilterResult;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Created by gavan on 17-2-7.
 */
public class RailroadService implements IRailroadService {
    @Override
    public int getRouteDistance(Railroad railroad, Town[] route) {
        Map<Town, List<Track>> map = railroad.getTracks();
        Town from = route[0];
        Town to = null;
        List<Track> tracks = null;
        Track track = null;
        int distance = 0;
        for (int i = 1; i < route.length; i++) {
            tracks = map.get(from);
            to = route[i];
            boolean hasTrack = false;
            if (tracks != null) {
                for (int j = 0; j < tracks.size(); j++) {
                    track = tracks.get(j);
                    if (to.equals(track.getTo())) {
                        hasTrack = true;
                        from = to;
                        distance += track.getDistance();
                        break;
                    }
                }
            }

            if (!hasTrack) {
                return INVALID_DISTANCE_VALUE;
            }
        }
        return distance;
    }

    @Override
    public int getTripCount(Railroad railroad, Town from, Town to, ITripFilter tripFilter) {
        int tripCount = 0;
        Map<Town, List<Track>> map = railroad.getTracks();
        List<Track> tracks = null;

        Queue<Trip> trips = new ArrayDeque<Trip>();
        trips.add(new Trip(from));
        Trip trip = null;
        Trip newTrip = null;
        TripFilterResult tripFilterResult = null;
        Town nextFrom = null;

        while (trips.size() > 0) {
            trip = trips.poll();
            nextFrom = trip.getTo();// current last town
            tracks = map.get(nextFrom);// tracks from current last town
            if (tracks != null) {
                for (Track track : tracks) {
                    tripFilterResult = tripFilter.accept(trip, track.getTo());
                    if (tripFilterResult.isAccepted() && to.equals(track.getTo())) {
                        tripCount++;
                        //TODO: cache the trip result if need to print the route
                        System.out.println("debug:" + trip + track.getTo());//debug
                    }

                    if (tripFilterResult.isFinished()) {
                        continue;
                    } else {
                        newTrip = trip.copy();
                        newTrip.add(track.getTo());
                        trips.add(newTrip);
                    }
                }
            }
        }
        return tripCount;
    }
}
