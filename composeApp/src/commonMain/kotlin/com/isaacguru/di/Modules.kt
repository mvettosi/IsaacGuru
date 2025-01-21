package com.isaacguru.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.isaacguru.data.db.DatabaseFactory
import com.isaacguru.data.db.IsaacGuruDatabase
import com.isaacguru.data.gamemod.GameModRepositoryImpl
import com.isaacguru.data.gamemod.remote.GameModRemoteDataSource
import com.isaacguru.data.inventory.InventoryRepositoryImpl
import com.isaacguru.data.inventory.remote.InventoryRemoteDataSource
import com.isaacguru.data.itempool.ItemPoolRepositoryImpl
import com.isaacguru.data.itempool.remote.ItemPoolRemoteDataSource
import com.isaacguru.data.settings.DataStoreSettingsRepository
import com.isaacguru.domain.device.usecase.IsNetworkAvailableUseCase
import com.isaacguru.domain.gamemod.repository.GameModRepository
import com.isaacguru.domain.gamemod.usecase.GetGameModsUseCase
import com.isaacguru.domain.gamemod.usecase.LoadDefaultInventoryUseCase
import com.isaacguru.domain.gamemod.usecase.SetGameModUseCase
import com.isaacguru.domain.gamemod.usecase.SetInventoryUseCase
import com.isaacguru.domain.inventory.repository.InventoryRepository
import com.isaacguru.domain.inventory.usecase.GetInventoryUseCase
import com.isaacguru.domain.inventory.usecase.GetItemUseCase
import com.isaacguru.domain.itempool.repository.ItemPoolRepository
import com.isaacguru.domain.settings.repository.SettingsRepository
import com.isaacguru.domain.settings.usecase.GetCurrentSettingsUseCase
import com.isaacguru.domain.settings.usecase.LoadDataUseCase
import com.isaacguru.domain.settings.usecase.UpdateSettingsUseCase
import com.isaacguru.presentation.AppViewModel
import com.isaacguru.presentation.features.inventory.InventoryViewModel
import com.isaacguru.presentation.features.inventory.detail.InventoryDetailsViewModel
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
  single { get<IsaacGuruDatabase>().inventoryLocalDataSource }
  single { get<IsaacGuruDatabase>().itemPoolDataSource }
  singleOf(::GameModRemoteDataSource)
  singleOf(::ItemPoolRemoteDataSource)
  singleOf(::InventoryRemoteDataSource)

  // Repositories
  singleOf(::DataStoreSettingsRepository).bind<SettingsRepository>()
  singleOf(::InventoryRepositoryImpl).bind<InventoryRepository>()
  singleOf(::ItemPoolRepositoryImpl).bind<ItemPoolRepository>()
  singleOf(::GameModRepositoryImpl).bind<GameModRepository>()

  // Use Cases
  singleOf(::SetInventoryUseCase)
  singleOf(::UpdateSettingsUseCase)
  singleOf(::GetCurrentSettingsUseCase)
  singleOf(::LoadDefaultInventoryUseCase)
  singleOf(::SetGameModUseCase)
  singleOf(::GetGameModsUseCase)
  singleOf(::IsNetworkAvailableUseCase)
  singleOf(::GetCurrentSettingsUseCase)
  singleOf(::LoadDataUseCase)
  singleOf(::GetInventoryUseCase)
  singleOf(::GetItemUseCase)

  // View Models
  viewModelOf(::AppViewModel)
  viewModelOf(::InventoryViewModel)
  viewModelOf(::InventoryDetailsViewModel)
}
