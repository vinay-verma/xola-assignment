package com.xola.assignment.model;

public interface Infectable {
    boolean isImmune();
    boolean isInfected();
    void infect();
}
