package io.gavan.trains.service.impl;

import io.gavan.trains.model.Railroad;
import io.gavan.trains.model.Town;
import io.gavan.trains.model.Track;
import io.gavan.trains.service.*;

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

    private static interface ITravelCallback {
        void onAccepted(Trip trip, Town next);

        int getResult();
    }

    private static class TripCountTravelCallback implements ITravelCallback {
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

    private static class ShortestDistanceTravelCallback implements ITravelCallback {
        private IRailroadService railroadService;
        private Railroad railroad;
        private int distance;

        public ShortestDistanceTravelCallback(RailroadService railroadService, Railroad railroad) {
            this.railroadService = railroadService;
            this.railroad = railroad;
            this.distance = INVALID_DISTANCE_VALUE;
        }

        @Override
        public void onAccepted(Trip trip, Town next) {
            List<Town> list = trip.getTowns();
            Town[] towns = new Town[list.size() + 1];
            towns = list.toArray(towns);
            towns[list.size()] = next;
            int d = this.railroadService.getRouteDistance(this.railroad, towns);
            System.out.println("debug shortest:" + trip + next + " " + d);//debug
            if (this.distance < 0 || this.distance > d) {
                this.distance = d;
            }
        }

        @Override
        public int getResult() {
            return this.distance;
        }
    }

    private void travel(Railroad railroad, Town from, Town to, ITripFilter tripFilter, ITravelCallback travelCallback) {
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
                        travelCallback.onAccepted(trip, to);
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
    }

    @Override
    public int getTripCount(Railroad railroad, Town from, Town to, ITripFilter tripFilter) {
        TripCountTravelCallback travelCallback = new TripCountTravelCallback();
        travel(railroad, from, to, tripFilter, travelCallback);
        return travelCallback.getResult();
    }

    @Override
    public int getShortestDistance(Railroad railroad, Town from, Town to) {
        ShortestDistanceTravelCallback travelCallback = new ShortestDistanceTravelCallback(this, railroad);
        travel(railroad, from, to, new NoDuplicateTripFilter(), travelCallback);
        return travelCallback.getResult();
    }
}
