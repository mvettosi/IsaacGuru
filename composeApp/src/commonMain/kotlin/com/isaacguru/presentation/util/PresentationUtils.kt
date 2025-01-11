package com.isaacguru.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.withContext

fun <T> MutableStateFlow<T>.stateWith(
    scope: CoroutineScope,
    intermediaries: Flow<T>.() -> Flow<T>
) = intermediaries().stateIn(scope, SharingStarted.WhileSubscribed(5000L), value)

@Composable
fun <T> ObserveEvents(flow: Flow<T>, onEvent: (T) -> Unit) {
  val lifeCycleOwner = LocalLifecycleOwner.current
  LaunchedEffect(flow, lifeCycleOwner.lifecycle) {
    lifeCycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
      withContext(Dispatchers.Main.immediate) { flow.collect(onEvent) }
    }
  }
}
