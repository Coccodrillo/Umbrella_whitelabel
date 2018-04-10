package org.secfirst.umbrella.data.remote

import org.secfirst.umbrella.util.Enums


/**
 * Created by nandawperdana.
 */
interface APICallListener {

    fun onAPICallSucceed(route: Enums.APIRoute, responseModel: RootResponseModel)

    fun onAPICallFailed(route: Enums.APIRoute, throwable: Throwable)
}
