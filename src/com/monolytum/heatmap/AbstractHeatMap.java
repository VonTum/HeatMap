package com.monolytum.heatmap;

abstract class AbstractHeatMap {

    public BaseHeatMap map;

    public AbstractHeatMap(BaseHeatMap map) {
        this.map = map;
    }

    abstract void enable();

    abstract void disable();

}
