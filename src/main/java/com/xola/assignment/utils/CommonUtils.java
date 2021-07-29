package com.xola.assignment.utils;

import com.xola.assignment.model.Coordinate;
import com.xola.assignment.model.enums.Direction;

public class CommonUtils {

    public static Coordinate getCoordinateFromPositionString(String position) {
        String[] split = position.split(" ");
        return new Coordinate(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Direction.valueOf(split[2]));
    }
}
