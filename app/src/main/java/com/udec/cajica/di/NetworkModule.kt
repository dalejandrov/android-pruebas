package com.udec.cajica.di

import com.udec.cajica.data.api.ApiService
import com.udec.cajica.data.api.RetrofitClient
import com.udec.cajica.data.repository.UsuarioRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "http://10.0.2.2:8080/"

    /** ApiService singleton */
    @Provides
    @Singleton
    fun provideBaseApi(): ApiService =
        RetrofitClient.createApi(BASE_URL)

    /** UsuarioRepository singleton */
    @Provides
    @Singleton
    fun provideUsuarioRepository(api: ApiService): UsuarioRepository =
        UsuarioRepository(api)
}
