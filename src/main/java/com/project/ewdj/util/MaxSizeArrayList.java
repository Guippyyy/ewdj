package com.project.ewdj.util;

import java.util.ArrayList;

public class MaxSizeArrayList<E> extends ArrayList<E> {

    private int maxSize;

    public MaxSizeArrayList(int maxSize) {
        super();
        this.maxSize = maxSize;
    }

    @Override
    public boolean add(E e) {
        if (size() < maxSize) {
            return super.add(e);
        } else {
            throw new IllegalStateException("Max size reached: " + maxSize);
        }
    }
}
