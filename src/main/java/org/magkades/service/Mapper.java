package org.magkades.service;

/**
 */
public interface Mapper<F, T> {
    public T map(F from);
}
