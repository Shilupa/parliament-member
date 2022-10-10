package fi.metropolia.retrofitparliamentmember.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import fi.metropolia.retrofitparliamentmember.model.PmExtrasModel
import fi.metropolia.retrofitparliamentmember.model.PmModel
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://users.metropolia.fi/~peterh/"

/**
 * Shilpa Singh Yadav
 * 2112616
 * Date:10.10.2022
 */

/**
 * Creating retrofit service
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PmApiService {
    //For pm db
    @GET("seating.json")
    suspend fun getPmList(): List<PmModel>

    // for PmExtras db
    @GET("extras.json")
    suspend fun getPmExtras(): List<PmExtrasModel>
}

object PmApi {
    val retrofitService: PmApiService by lazy {
        retrofit.create(PmApiService::class.java)
    }
}