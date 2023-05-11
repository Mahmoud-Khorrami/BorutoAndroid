package com.mahapp1397.borutoapp.di

import android.content.Context
import androidx.room.Room
import com.mahapp1397.borutoapp.data.BorutoDatabase
import com.mahapp1397.borutoapp.data.repository.LocalDataSourceImpl
import com.mahapp1397.borutoapp.domain.repository.LocalDataSource
import com.mahapp1397.borutoapp.utils.Constants.BORUTO_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context): BorutoDatabase {
        return Room.databaseBuilder(context,
                                    BorutoDatabase::class.java,
                                    BORUTO_DATABASE).build()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(borutoDatabase: BorutoDatabase): LocalDataSource
    {
        return LocalDataSourceImpl(borutoDatabase = borutoDatabase)
    }
}