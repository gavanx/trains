package io.gavan.trains.model;

/**
 * Created by gavan on 17-2-6.
 */
public class Town {
    private char id;

    public Town(char id) {
        this.id = id;
    }

    public char getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Town) {
            Town t = (Town) obj;
            if (t != null) {
                return this.id == t.id;
            }
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "" + this.id;
    }
}
