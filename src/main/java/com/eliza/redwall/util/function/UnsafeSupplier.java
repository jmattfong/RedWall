/*
 * Copyright (c) 2016 Matthew Fong
 */

package com.eliza.redwall.util.function;

@FunctionalInterface
public interface UnsafeSupplier<T> {

    T get() throws Exception;
}
