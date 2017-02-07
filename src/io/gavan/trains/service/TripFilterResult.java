package io.gavan.trains.service;

/**
 * Created by gavan on 17-2-7.
 */
public class TripFilterResult {
    private boolean accepted;
    private boolean finished;

    public TripFilterResult(boolean accepted, boolean finished) {
        this.accepted = accepted;
        this.finished = finished;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public boolean isFinished() {
        return finished;
    }

    @Override
    public String toString() {
        return "accepted=" + this.accepted + " finished=" + this.finished;
    }
}