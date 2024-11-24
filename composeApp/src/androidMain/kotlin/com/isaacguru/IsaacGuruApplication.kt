package com.isaacguru

import android.app.Application
import com.isaacguru.presentation.di.initKoin
import org.koin.android.ext.koin.androidContext

class IsaacGuruApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    initKoin { androidContext(this@IsaacGuruApplication) }
  }
}
