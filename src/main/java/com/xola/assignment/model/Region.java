package com.xola.assignment.model;

import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

public class Region extends Grid {
    private final Set<Town> towns;

    public Region(Integer length, Integer width) {
        super(length, width);
        this.towns = generateTowns(length, width, null);
    }

    public Region(Integer length, Integer width, Set<Coordinate> infectedTowns) {
        super(length, width);
        this.towns = generateTowns(length, width, infectedTowns);
    }

    private Set<Town> generateTowns(Integer length, Integer width, Set<Coordinate> infectedTowns) {
        Set<Town> towns = new HashSet<>();
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < width; y++) {
                Town town = new Town(x, y);
                if (!CollectionUtils.isEmpty(infectedTowns)) {
                    Coordinate townCoordinate = town.getCoordinate();
                    if (infectedTowns.contains(townCoordinate)) {
                        town.infect();
                    }
                }
                towns.add(town);
            }
        }
        return towns;
    }

    public Set<Town> getTowns() {
        return towns;
    }

    public Town getTown(Coordinate coordinate) {
        for (Town town : this.getTowns()) {
            if (town.getCoordinate().equals(coordinate)) {
                return town;
            }
        }
        return null;
    };

    public String printInfectionState() {
        boolean[][] grid = new boolean[getLength()][getWidth()];
        for (Town town : this.getTowns()) {
            Coordinate coordinate = town.getCoordinate();
            grid[coordinate.getX()][coordinate.getY()] = town.isInfected();
        }
        StringBuilder sb = new StringBuilder();
        for (int y = getWidth() - 1; y >= 0 ; y--) {
            for (int x = 0; x < getLength(); x++) {
                if (grid[x][y]) {
                    sb.append("X");
                } else {
                    sb.append("O");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
