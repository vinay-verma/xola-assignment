package com.xola.assignment.model;

import java.util.Objects;

public class Town extends Grid implements Infectable {

    private Coordinate coordinate;
    private boolean infected;

    public Town() {
        this(0, 0);
    }

    public Town(Integer x, Integer y) {
        super(1, 1);
        this.coordinate = new Coordinate(x, y);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void setInfected(boolean infected) {
        this.infected = infected;
    }

    @Override
    public boolean isInfected() {
        return this.infected;
    }

    @Override
    public void infect() {
        this.setInfected(true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Town town = (Town) o;
        return coordinate.equals(town.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate);
    }
}
