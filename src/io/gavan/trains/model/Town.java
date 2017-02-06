package io.gavan.trains.model;

/**
 * Created by gavan on 17-2-6.
 */
public class Town {
    private char id;

    public Town(char id){
        this.id = id;
    }

    public char getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }
}
