package io.gavan.trains.service;

import io.gavan.trains.model.Town;

/**
 * Created by gavan on 17-2-7.
 */
public interface ITripFilter {
    boolean accept(Town from, Town to, int stops);
}
