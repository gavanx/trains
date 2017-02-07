package io.gavan.trains.factory;

import io.gavan.trains.model.Town;

/**
 * Created by gavan on 17-2-6.
 */
public interface ITownRegistry {
    Town get(char id);
}
