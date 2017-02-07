package io.gavan.trains.service.travel;

import io.gavan.trains.model.Town;
import io.gavan.trains.model.Track;
import io.gavan.trains.trip.Trip;

/**
 * Created by gavan on 17-2-7.
 */
public interface ITravelCallback {
    void onAccepted(Trip trip, Track track);

    int getResult();
}
