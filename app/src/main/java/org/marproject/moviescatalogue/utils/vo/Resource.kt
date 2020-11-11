package org.marproject.moviescatalogue.utils.vo

import org.marproject.moviescatalogue.utils.vo.Status.SUCCESS
import org.marproject.moviescatalogue.utils.vo.Status.ERROR
import org.marproject.moviescatalogue.utils.vo.Status.LOADING

data class Resource<T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> = Resource(SUCCESS, data, null)

        fun <T> error(msg: String?, data: T?): Resource<T> = Resource(ERROR, data, msg)

        fun <T> loading(data: T?): Resource<T> = Resource(LOADING, data, null)
    }
}