package com.isaacguru.presentation.features.inventory.items.model

import androidx.compose.ui.text.AnnotatedString
import com.isaacguru.domain.inventory.model.InventoryItem
import com.isaacguru.domain.inventory.model.Item
import com.isaacguru.presentation.shared.fromDiscordString
import com.isaacguru.presentation.shared.fromPools

data class ViewItemWrapper<T : InventoryItem>(
    val raw: T,
    val richPools: AnnotatedString?,
    val richDescription: AnnotatedString,
    val subtitle: String? = null
)

fun <T : InventoryItem> viewWrapperOf(item: T) =
    ViewItemWrapper(
        raw = item,
        richPools = (item as? Item)?.itemPools?.fromPools(),
        richDescription = item.description.fromDiscordString(),
        subtitle = if (item is Item.Active) "Active Item" else "Passive Item")
