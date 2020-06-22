package org.sdelaysam.carprice.util.data

/**
 * Created on 6/23/20.
 * @author sdelaysam
 */

sealed class Optional<T> {
    class None<T>: Optional<T>()
    class Some<T>(val value: T): Optional<T>()
}