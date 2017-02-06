package io.gavan.trains.factory.impl;

import io.gavan.trains.factory.ITownFactory;
import io.gavan.trains.model.Town;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gavan on 17-2-6.
 */
public class TownFactory implements ITownFactory {
    private Map<Character, Town> towns;

    public TownFactory() {
        this.towns = new HashMap<Character, Town>();
    }

    @Override
    public Town get(char id) {// works only on a single thread
        Town t = towns.get(id);
        if (t == null) {
            t = new Town(id);
            towns.put(id, t);
        }
        return t;
    }
}
