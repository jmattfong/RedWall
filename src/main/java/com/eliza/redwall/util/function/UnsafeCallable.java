/*
 * Copyright (c) 2016 Matthew Fong
 */

package com.eliza.redwall.util.function;

@FunctionalInterface
public interface UnsafeCallable {

    void call() throws Exception;
}
