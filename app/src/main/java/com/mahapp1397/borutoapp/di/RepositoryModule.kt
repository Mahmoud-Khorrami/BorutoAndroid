package com.mahapp1397.borutoapp.di

import android.content.Context
import com.mahapp1397.borutoapp.data.repository.DataStoreOperationImpl
import com.mahapp1397.borutoapp.data.repository.Repository
import com.mahapp1397.borutoapp.domain.repository.DataStoreOperations
import com.mahapp1397.borutoapp.domain.use_cases.UseCases
import com.mahapp1397.borutoapp.domain.use_cases.get_all_heroes.GetAllHeroesUseCase
import com.mahapp1397.borutoapp.domain.use_cases.get_selected_hero.GetSelectedHeroUseCase
import com.mahapp1397.borutoapp.domain.use_cases.read_onboarding.ReadOnBoardingUseCase
import com.mahapp1397.borutoapp.domain.use_cases.save_onboarding.SaveOnBoardingUseCase
import com.mahapp1397.borutoapp.domain.use_cases.search_heroes.SearchHeroesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperation(@ApplicationContext context: Context): DataStoreOperations
    {
        return DataStoreOperationImpl(context)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: Repository): UseCases
    {
        return UseCases(
            SaveOnBoardingUseCase(repository),
            ReadOnBoardingUseCase(repository),
            GetAllHeroesUseCase(repository),
            SearchHeroesUseCase(repository),
            GetSelectedHeroUseCase(repository)
                       )
    }
}