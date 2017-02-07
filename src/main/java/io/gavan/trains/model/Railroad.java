package io.gavan.trains.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gavan on 17-2-6.
 */
public class Railroad {
    private Map<Town, List<Track>> tracks;//town -> tracks from the town

    public Railroad(List<Track> tracks) {
        Map<Town, List<Track>> map = new HashMap<Town, List<Track>>();
        for (Track t : tracks) {
            if (map.containsKey(t.getFrom())) {
                map.get(t.getFrom()).add(t);
            } else {
                List<Track> list = new ArrayList<Track>();
                list.add(t);
                map.put(t.getFrom(), list);
            }
        }
        this.tracks = map;
    }

    public Railroad(Map<Town, List<Track>> tracks) {
        this.tracks = tracks;
    }

    public Map<Town, List<Track>> getTracks() {
        return tracks;
    }
}
