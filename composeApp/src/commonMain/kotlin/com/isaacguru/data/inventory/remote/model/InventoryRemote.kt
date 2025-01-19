package com.isaacguru.data.inventory.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class InventoryRemote(
    val item: List<InventoryItemRemote>,
    val trinket: List<InventoryItemRemote>,
    val pickup: List<InventoryItemRemote>,
    val consumable: List<InventoryItemRemote>,
    val machine: List<InventoryItemRemote>,
    val character: List<InventoryItemRemote>,
    val transformation: List<InventoryItemRemote>,
    val curse: List<InventoryItemRemote>,
)
