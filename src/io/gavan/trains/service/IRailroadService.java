package io.gavan.trains.service;

import io.gavan.trains.model.Railroad;
import io.gavan.trains.model.Town;

/**
 * Created by gavan on 17-2-7.
 */
public interface IRailroadService {
    static int INVALID_DISTANCE_VALUE = -1;
    static String INVALID_DISTANCE_OUTPUT = "NO SUCH ROUTE";

    int getRouteDistance(Railroad railroad, Town[] route);

    int getTripCount(Town from, Town to, ITripFilter tripFilter);
}
