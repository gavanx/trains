package io.gavan.trains.factory;

import io.gavan.trains.model.Railroad;

/**
 * Created by gavan on 17-2-6.
 */
public interface IRailroadFactory {
    Railroad create(ITownRegistry townRegistry, String data, String split);
}
