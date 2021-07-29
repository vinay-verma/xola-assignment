package com.xola.assignment.model;

public interface Movable {
    void turnLeft();
    void turnRight();
    Coordinate moveForward();
    Coordinate getCurrentCoordinates();
    String getCurrentPosition();
}
