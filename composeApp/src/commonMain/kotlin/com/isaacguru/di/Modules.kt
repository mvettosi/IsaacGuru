package com.isaacguru.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.isaacguru.data.db.IsaacGuruDatabase
import com.isaacguru.data.db.converters.DatabaseFactory
import com.isaacguru.data.gamemod.default.DefaultGameDataSource
import com.isaacguru.data.item.ItemRepositoryImpl
import com.isaacguru.domain.collectable.item.repository.ItemRepository
import com.isaacguru.domain.collectable.item.usecase.GetItemsUseCase
import com.isaacguru.presentation.AppViewModel
import com.isaacguru.presentation.features.inventory.items.ItemsViewModel
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
  singleOf(::ItemRepositoryImpl).bind<ItemRepository>()

  // Use Cases
  singleOf(::GetItemsUseCase)

  // View Models
  viewModelOf(::AppViewModel)
  viewModelOf(::ItemsViewModel)
}
