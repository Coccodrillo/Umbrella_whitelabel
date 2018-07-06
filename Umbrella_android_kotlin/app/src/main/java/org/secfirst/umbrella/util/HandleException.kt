package org.secfirst.umbrella.util

import io.reactivex.Single
import io.reactivex.exceptions.CompositeException


fun <T> Single<T>.trackException(): Single<T> {
    val exception = CustomException()
    return this.onErrorResumeNext { error: Throwable ->
        throw CompositeException(error, exception)
    }
}

class CustomException : Exception()
