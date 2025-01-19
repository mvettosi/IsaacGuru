package com.isaacguru.data.inventory.local.mapper

import com.isaacguru.data.inventory.local.model.InventoryItemEntity
import com.isaacguru.domain.collectable.item.model.Item
import com.isaacguru.domain.collectable.item.model.ItemType

fun Item.toEntity() =
    InventoryItemEntity(
        id = id,
        name = name,
        description = description,
        image = image,
        keywords = keywords,
        quote = quote,
        unlockMethod = unlockMethod,
        costume = costume,
        itemPools = itemPools,
        quality = quality,
        transformations = transformations,
        type = if (this is Item.Active) ItemType.ACTIVE else ItemType.PASSIVE,
        maxCharges = if (this is Item.Active) maxCharges else null,
        chargeType = if (this is Item.Active) chargeType else null,
        cooldown = if (this is Item.Active) cooldown else null,
    )

fun InventoryItemEntity.toDomain() =
    when (type) {
      ItemType.ACTIVE ->
          Item.Active(
              id = id,
              name = name,
              description = description,
              image = image,
              keywords = keywords,
              quote = quote,
              unlockMethod = unlockMethod ?: "",
              costume = costume,
              itemPools = itemPools,
              quality = quality,
              transformations = transformations,
              maxCharges = maxCharges ?: 0,
              chargeType = chargeType ?: Item.Active.ChargeType.DEFAULT,
              cooldown = cooldown ?: 0,
          )
      ItemType.PASSIVE ->
          Item.Passive(
              id = id,
              name = name,
              description = description,
              image = image,
              keywords = keywords,
              quote = quote,
              unlockMethod = unlockMethod ?: "",
              costume = costume,
              itemPools = itemPools,
              quality = quality,
              transformations = transformations)
    }
