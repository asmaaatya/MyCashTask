package com.mycash.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.mycash.data.local.LocalDataStore
import com.mycash.data.local.LocalDataStoreImp
import com.mycash.utils.Constant.PREFERENCES_STORE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.prefs.Preferences
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Singleton
    @Provides
    fun provideLocalDataStore(@ApplicationContext app: Context): DataStore<androidx.datastore.preferences.core.Preferences> {
        return PreferenceDataStoreFactory.create(
            produceFile = { app.preferencesDataStoreFile(PREFERENCES_STORE_NAME) }
        )
    }

    @Provides
    fun provideApplicationContext(
        @ApplicationContext appContext: Context,
    ): Context {
        return appContext
    }

    @Singleton
    @Provides
    fun provideLocalDataSource(dataStore: DataStore<androidx.datastore.preferences.core.Preferences>): LocalDataStore {
        return LocalDataStoreImp(dataStore)
    }
}