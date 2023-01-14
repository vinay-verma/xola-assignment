package com.xola.assignment.model;

import com.xola.assignment.exception.AssignmentException;
import com.xola.assignment.model.enums.Direction;
import com.xola.assignment.model.enums.PersonType;

public class Person implements Infectable, Movable, Curable {

    private Coordinate initialPosition;
    private Coordinate currentPosition;
    private String movements;
    private boolean infected;
    private PersonType type = PersonType.citizen;

    private Person() {
    }

    public Person(Coordinate coordinate, String movements, PersonType type) {
        this(coordinate, movements, false, type);
    }

    public Person(Coordinate coordinate, String movements, boolean infected, PersonType type) {
        this.initialPosition = coordinate;
        this.currentPosition = coordinate;
        this.movements = movements;
        this.infected = infected;
        this.type = type;
    }

    public Coordinate getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(Coordinate initialPosition) {
        this.initialPosition = initialPosition;
    }

    @Override
    public Coordinate getCurrentCoordinates() {
        return currentPosition;
    }

    @Override
    public String getCurrentPosition() {
        return "" + currentPosition.getX() + " " + currentPosition.getY() + " " + currentPosition.getDirection();
    }

    public String getMovements() {
        return movements;
    }

    public void setMovements(String movements) {
        this.movements = movements;
    }

    public void setCurrentPosition(Coordinate currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void setInfected(boolean infected) {
        this.infected = infected;
    }

    public PersonType getType() {
        if (type == null) {
            type = PersonType.citizen;
        }
        return type;
    }

    public void setType(PersonType type) {
        this.type = type;
    }

    @Override
    public boolean isImmune() {
        return false;
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
    public void turnLeft() {
        if (this.currentPosition == null) {
            throw new AssignmentException("Current Position is not set for person " + this);
        }
        Direction direction = this.currentPosition.getDirection();
        switch (direction) {
            case N: this.currentPosition.setDirection(Direction.W); break;
            case W: this.currentPosition.setDirection(Direction.S); break;
            case S: this.currentPosition.setDirection(Direction.E); break;
            case E: this.currentPosition.setDirection(Direction.N); break;
        }
    }

    @Override
    public void turnRight() {
        if (this.currentPosition == null) {
            throw new AssignmentException("Current Position is not set for person " + this);
        }
        Direction direction = this.currentPosition.getDirection();
        switch (direction) {
            case N: this.currentPosition.setDirection(Direction.E); break;
            case W: this.currentPosition.setDirection(Direction.N); break;
            case S: this.currentPosition.setDirection(Direction.W); break;
            case E: this.currentPosition.setDirection(Direction.S); break;
        }
    }

    @Override
    public Coordinate moveForward() {
        if (this.currentPosition == null) {
            throw new AssignmentException("Current Position is not set for person " + this);
        }
        Direction direction = this.currentPosition.getDirection();
        switch (direction) {
            case N: this.currentPosition.addY(1); break;
            case W: this.currentPosition.addX(-1); break;
            case S: this.currentPosition.addY(-1); break;
            case E: this.currentPosition.addX(1); break;
        }
        return this.currentPosition;
    }

    @Override
    public String toString() {
        return "Person{" +
                "initialPosition=" + getCurrentPosition() +
                ", movements='" + movements + '\'' +
                ", infected='" + infected + '\'' +
                '}';
    }

    @Override
    public void cure() {
        this.setInfected(false);
    }
}
