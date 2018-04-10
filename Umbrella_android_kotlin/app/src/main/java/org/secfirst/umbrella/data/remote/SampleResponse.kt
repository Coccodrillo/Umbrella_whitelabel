package org.secfirst.umbrella.data.remote


/**
 * Created by nandawperdana.
 * A sample response model from API.
 * TODO: Replace this with your own response model.
 */

data class SampleResponse( val message: String?) : RootResponseModel() {

    constructor() : this(message = null)
}
