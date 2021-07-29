package com.xola.assignment.model;

public abstract class Grid {
    private Integer length;
    private Integer width;

    // no default constructor
    private Grid() {
    }

    public Grid(Integer length, Integer width) {
        this.length = length;
        this.width = width;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
}
