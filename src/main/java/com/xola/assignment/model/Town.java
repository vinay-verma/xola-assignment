package com.xola.assignment.model;

import java.util.Objects;

public class Town extends Grid implements Infectable, Curable {

    private Coordinate coordinate;
    private boolean infected;
    private boolean medicalCenter;

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

    public boolean isMedicalCenter() {
        return medicalCenter;
    }

    public void setMedicalCenter(boolean medicalCenter) {
        this.medicalCenter = medicalCenter;
    }

    @Override
    public boolean isImmune() {
        return isMedicalCenter();
    }

    @Override
    public boolean isInfected() {
        return this.infected;
    }

    @Override
    public void infect() {
        if (!this.medicalCenter) {
            this.setInfected(true);
        }
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

    @Override
    public void cure() {
        this.setInfected(false);
    }

    public boolean isNeibourOf(Coordinate a) {
        Integer x = this.getCoordinate().getX();
        Integer y = this.getCoordinate().getY();
        Integer ax = a.getX();
        Integer ay = a.getY();
        return Math.abs(x - ax) <= 1 || Math.abs(y - ay) <= 1;
    }
}
