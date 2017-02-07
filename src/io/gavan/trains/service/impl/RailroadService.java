package io.gavan.trains.service.impl;

import io.gavan.trains.model.Railroad;
import io.gavan.trains.model.Town;
import io.gavan.trains.model.Track;
import io.gavan.trains.service.IRailroadService;
import io.gavan.trains.service.ITripFilter;

import java.util.List;
import java.util.Map;

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
    public int getTripCount(Town from, Town to, ITripFilter tripFilter) {
        return 0;
    }
}
