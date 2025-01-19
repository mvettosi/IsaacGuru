package com.isaacguru.data

import isaacguru.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
suspend fun getJsonString(uri: String) = Res.readBytes(uri).decodeToString()
