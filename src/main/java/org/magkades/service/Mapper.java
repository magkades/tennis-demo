package org.magkades.service;

/**
 * Maps an object of type F to an object of type T.
 */
public interface Mapper<F, T> {
    public T map(F from);
}
