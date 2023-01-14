package com.xola.assignment.dto;

import com.xola.assignment.model.enums.PersonType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InputDto implements Serializable {
    private Grid grid;
    private List<Coordinate> infectedCells;
    private List<Coordinate> medicalCentres;
    private List<Person> persons;

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public List<Coordinate> getInfectedCells() {
        return infectedCells;
    }

    public void setInfectedCells(List<Coordinate> infectedCells) {
        this.infectedCells = infectedCells;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Coordinate> getMedicalCentres() {
        if (medicalCentres == null) {
            medicalCentres = new ArrayList<>();
        }
        return medicalCentres;
    }

    public void setMedicalCentres(List<Coordinate> medicalCentres) {
        this.medicalCentres = medicalCentres;
    }

    public static class Grid {
        private Integer length;
        private Integer breadth;

        public Integer getLength() {
            return length;
        }

        public void setLength(Integer length) {
            this.length = length;
        }

        public Integer getBreadth() {
            return breadth;
        }

        public void setBreadth(Integer breadth) {
            this.breadth = breadth;
        }
    }

    public static class Coordinate {
        private Integer x;
        private Integer y;

        public Integer getX() {
            return x;
        }

        public void setX(Integer x) {
            this.x = x;
        }

        public Integer getY() {
            return y;
        }

        public void setY(Integer y) {
            this.y = y;
        }
    }

    public static class Person {
        private String initialPosition;
        private String movement;
        private PersonType type;

        public String getInitialPosition() {
            return initialPosition;
        }

        public void setInitialPosition(String initialPosition) {
            this.initialPosition = initialPosition;
        }

        public String getMovement() {
            return movement;
        }

        public void setMovement(String movement) {
            this.movement = movement;
        }

        public PersonType getType() {
            return type;
        }

        public void setType(PersonType type) {
            this.type = type;
        }
    }
}
