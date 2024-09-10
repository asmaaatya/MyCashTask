package com.mycash.di

import android.content.Context
import android.util.Log
import com.mycash.data.local.LocalDataStore
import com.mycash.data.remote.apis.ApiService
import com.mycash.utils.Constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)


object NetworkModule {

    private const val READ_TIMEOUT = 30
    private const val WRITE_TIMEOUT = 30
    private const val CONNECTION_TIMEOUT = 10
    private const val CACHE_SIZE_BYTES = 10 * 1024 * 1024L // 10 MB

    @Provides
    @Singleton
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    internal fun provideCache(@ApplicationContext context: Context): Cache {
        val httpCacheDirectory = File(context.cacheDir.absolutePath, "HttpCache")
        return Cache(httpCacheDirectory, CACHE_SIZE_BYTES)
    }

    @Provides
    @Singleton
    internal fun provideOkhttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        cache: Cache,
        preferences: LocalDataStore,
    ): OkHttpClient {

        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.cache(cache)

        okHttpClientBuilder.addNetworkInterceptor { chain ->

            val token = runBlocking { preferences.requestToken().first() }
            val language = runBlocking { preferences.requestLanguage().first() }
            val requestBuilder = chain.request().newBuilder()

            if (token != null)
                requestBuilder.addHeader("Authorization", "Bearer $token")
            if (language != null)
                requestBuilder.addHeader("lang", language)

            requestBuilder.addHeader("Accept", "application/json")

            Log.d("droidOs", "-> Request Headers: ${requestBuilder.build().headers}")
            Log.d("droidOs ", "-> token: $token")

            chain.proceed(requestBuilder.build())
        }

        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)

        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}