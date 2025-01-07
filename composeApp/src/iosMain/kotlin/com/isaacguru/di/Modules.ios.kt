package com.isaacguru.di

import com.isaacguru.data.db.DatabaseFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
  get() = module { single { DatabaseFactory() } }
