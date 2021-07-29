package com.xola.assignment.utils;

import com.xola.assignment.model.Coordinate;
import com.xola.assignment.model.enums.Direction;
import org.junit.Assert;
import org.junit.Test;

public class CommonUtilsTest {

    @Test
    public void shouldTestGetCoordinateFromPositionString() {
        Coordinate coordinate = CommonUtils.getCoordinateFromPositionString("1 1 N");
        Assert.assertNotNull(coordinate);
        Assert.assertEquals(1, coordinate.getX(), 0);
        Assert.assertEquals(1, coordinate.getY(), 0);
        Assert.assertEquals(Direction.N, coordinate.getDirection());
    }

}