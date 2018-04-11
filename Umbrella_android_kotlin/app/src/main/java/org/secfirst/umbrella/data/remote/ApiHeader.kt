package org.secfirst.umbrella.data.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import dagger.Provides
import org.secfirst.umbrella.di.ApiKeyInfo
import javax.inject.Inject

class ApiHeader @Inject constructor(internal val publicApiHeader: PublicApiHeader /*, internal val protectedApiHeader: ProtectedApiHeader*/) {

    class PublicApiHeader @Inject constructor(@ApiKeyInfo
                                              @Expose
                                              @SerializedName
                                              ("api_key") val apiKey: String)
//
//    class ProtectedApiHeader @Inject constructor(@ApiKeyInfo
//                                                 @Expose
//                                                 @SerializedName("api_key") val apiKey: String,
//                                                 @Expose
//                                                 @SerializedName("user_id") val userId: Long?,
//                                                 @Expose
//                                                 @SerializedName("access_token") val accessToken: String?)

}