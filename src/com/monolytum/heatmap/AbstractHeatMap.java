package com.monolytum.heatmap;

abstract class AbstractHeatMap {

    public DataStorage map;

    public AbstractHeatMap(DataStorage map) {
        this.map = map;
    }

    abstract void enable();

    abstract void disable();

}
