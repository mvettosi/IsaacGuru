package com.isaacguru.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.isaacguru.data.db.DatabaseFactory
import com.isaacguru.data.db.IsaacGuruDatabase
import com.isaacguru.data.gamemod.GameAspectRepositoryImpl
import com.isaacguru.data.gamemod.GameModRepositoryImpl
import com.isaacguru.data.gamemod.default.DefaultGameDataSource
import com.isaacguru.data.item.ItemRepositoryImpl
import com.isaacguru.data.settings.DataStoreSettingsRepository
import com.isaacguru.domain.collectable.item.repository.ItemRepository
import com.isaacguru.domain.collectable.item.usecase.GetItemsUseCase
import com.isaacguru.domain.device.usecase.IsNetworkAvailableUseCase
import com.isaacguru.domain.gamemod.repository.GameAspectRepository
import com.isaacguru.domain.gamemod.repository.GameModRepository
import com.isaacguru.domain.gamemod.usecase.GetGameModsUseCase
import com.isaacguru.domain.gamemod.usecase.LoadDefaultModUseCase
import com.isaacguru.domain.gamemod.usecase.SetGameAspectsUseCase
import com.isaacguru.domain.gamemod.usecase.SetGameModUseCase
import com.isaacguru.domain.settings.repository.SettingsRepository
import com.isaacguru.domain.settings.usecase.GetCurrentSettingsUseCase
import com.isaacguru.domain.settings.usecase.LoadDataUseCase
import com.isaacguru.domain.settings.usecase.UpdateSettingsUseCase
import com.isaacguru.presentation.AppViewModel
import com.isaacguru.presentation.features.inventory.items.ItemsViewModel
import com.isaacguru.presentation.features.inventory.items.detail.ItemDetailsViewModel
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
  // Third parties
  single { get<DatabaseFactory>().create().setDriver(BundledSQLiteDriver()).build() }
  single { Json { ignoreUnknownKeys = true } }

  // Data Sources
  single { get<IsaacGuruDatabase>().itemDataSource }
  //  single { get<IsaacGuruDatabase>().itemPoolDataSource }
  singleOf(::DefaultGameDataSource)

  // Repositories
  singleOf(::GameAspectRepositoryImpl).bind<GameAspectRepository>()
  singleOf(::GameModRepositoryImpl).bind<GameModRepository>()
  singleOf(::DataStoreSettingsRepository).bind<SettingsRepository>()
  singleOf(::ItemRepositoryImpl).bind<ItemRepository>()

  // Use Cases
  singleOf(::SetGameAspectsUseCase)
  singleOf(::UpdateSettingsUseCase)
  singleOf(::GetCurrentSettingsUseCase)
  singleOf(::LoadDefaultModUseCase)
  singleOf(::SetGameModUseCase)
  singleOf(::GetGameModsUseCase)
  singleOf(::IsNetworkAvailableUseCase)
  singleOf(::GetCurrentSettingsUseCase)
  singleOf(::LoadDataUseCase)
  singleOf(::GetItemsUseCase)

  // View Models
  viewModelOf(::AppViewModel)
  viewModelOf(::ItemsViewModel)
  viewModelOf(::ItemDetailsViewModel)
}
