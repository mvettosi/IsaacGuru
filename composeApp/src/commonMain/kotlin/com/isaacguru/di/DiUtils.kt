package com.isaacguru.di

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import org.koin.compose.currentKoinScope
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.qualifier
import org.koin.core.scope.Scope
import org.koin.viewmodel.defaultExtras
import pro.respawn.flowmvi.android.StoreViewModel
import pro.respawn.flowmvi.api.Container
import pro.respawn.flowmvi.api.FlowMVIDSL
import pro.respawn.flowmvi.api.MVIAction
import pro.respawn.flowmvi.api.MVIIntent
import pro.respawn.flowmvi.api.MVIState
import pro.respawn.flowmvi.api.Store

@FlowMVIDSL
inline fun <reified T : Container<*, *, *>> Module.storeViewModel() =
    viewModel(qualifier<T>()) { params -> StoreViewModel(get<T> { params }) }

@FlowMVIDSL
@Composable
inline fun <
    reified T : Container<S, I, A>, S : MVIState, I : MVIIntent, A : MVIAction> storeViewModel(
    key: String? = null,
    scope: Scope = currentKoinScope(),
    viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current),
    extras: CreationExtras = defaultExtras(viewModelStoreOwner),
    noinline params: ParametersDefinition? = null,
): Store<S, I, A> =
    koinViewModel<StoreViewModel<S, I, A>>(
        qualifier = qualifier<T>(),
        parameters = params,
        key = key,
        scope = scope,
        viewModelStoreOwner = viewModelStoreOwner,
        extras = extras)
