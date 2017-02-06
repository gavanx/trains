package io.gavan.trains.factory.impl;

import io.gavan.trains.factory.IRailroadFactory;
import io.gavan.trains.factory.ITownFactory;
import io.gavan.trains.model.Railroad;
import io.gavan.trains.model.Track;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gavan on 17-2-6.
 */
public class RailroadFactory implements IRailroadFactory {
    @Override
    public Railroad create() {//TODO:
        ITownFactory townFactory = new TownFactory();
        List<Track> tracks = new ArrayList<Track>();
        String data = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        String[] rawTracks = data.split(", ");
        for (String rawTrack : rawTracks) {
            tracks.add(new Track(townFactory.get(rawTrack.charAt(0)), townFactory.get(rawTrack.charAt(1)), Integer.parseInt(rawTrack.substring(2))));
        }
        return new Railroad(tracks);
    }
}
