package com.algorithms.collections.arraylist;

import com.algorithms.controls.option.Option;

/**
 * List implementation backed by an Array
 */
public interface ArrayList<T> {
    boolean isEmpty();
    Option<T> get(int index);
    ArrayList<T> push(T element);
    ArrayList<T> pop(int index);
}
