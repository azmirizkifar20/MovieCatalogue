package org.marproject.moviescatalogue.data.source.remote.network

import org.marproject.moviescatalogue.data.source.remote.network.StatusResponse.SUCCESS

class ApiResponse<T>(val status: StatusResponse, val body: T, val message: String?) {
    companion object {
        fun <T> success(body: T): ApiResponse<T> = ApiResponse(SUCCESS, body, null)
    }
}