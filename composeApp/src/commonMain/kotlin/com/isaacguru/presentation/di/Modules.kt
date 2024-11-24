package com.isaacguru.presentation.di

import com.isaacguru.data.item.ItemRepositoryImpl
import com.isaacguru.domain.collectable.item.repository.ItemRepository
import com.isaacguru.domain.collectable.item.usecase.GetItemsUseCase
import com.isaacguru.presentation.features.inventory.items.ItemsViewModel
import com.isaacguru.presentation.features.inventory.items.mapper.ViewItemListMapper
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sharedModule = module {
  singleOf(::ItemRepositoryImpl).bind<ItemRepository>()
  singleOf(::ViewItemListMapper)
  singleOf(::GetItemsUseCase)
  viewModelOf(::ItemsViewModel)
}
