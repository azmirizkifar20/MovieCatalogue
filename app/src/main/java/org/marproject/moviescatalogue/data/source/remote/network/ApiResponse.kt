package org.marproject.moviescatalogue.data.source.remote.network

import org.marproject.moviescatalogue.data.source.remote.network.StatusResponse.SUCCESS
import org.marproject.moviescatalogue.data.source.remote.network.StatusResponse.EMPTY
import org.marproject.moviescatalogue.data.source.remote.network.StatusResponse.ERROR

class ApiResponse<T>(val status: StatusResponse, val body: T, val message: String?) {
    companion object {
        fun <T> success(body: T): ApiResponse<T> = ApiResponse(SUCCESS, body, null)

        fun <T> empty(msg: String, body: T): ApiResponse<T> = ApiResponse(EMPTY, body, msg)

        fun <T> error(msg: String, body: T): ApiResponse<T> = ApiResponse(ERROR, body, msg)
    }
}