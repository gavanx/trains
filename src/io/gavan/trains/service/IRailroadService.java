package io.gavan.trains.service;

import io.gavan.trains.model.Town;

/**
 * Created by gavan on 17-2-7.
 */
public interface IRailroadService {
    int getRouteDistance(Town from, Town to);
}
