package io.gavan.trains.factory.impl;

import io.gavan.trains.factory.IRailroadFactory;
import io.gavan.trains.factory.ITownRegistry;
import io.gavan.trains.model.Railroad;
import io.gavan.trains.model.Track;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gavan on 17-2-6.
 */
public class RailroadFactory implements IRailroadFactory {
    @Override
    public Railroad create(ITownRegistry townRegistry, String data) {
        List<Track> tracks = new ArrayList<Track>();
        String[] rawTracks = data.split(", ");
        for (String rawTrack : rawTracks) {
            tracks.add(new Track(townRegistry.get(rawTrack.charAt(0)), townRegistry.get(rawTrack.charAt(1)), Integer.parseInt(rawTrack.substring(2))));
        }
        return new Railroad(tracks);
    }
}
