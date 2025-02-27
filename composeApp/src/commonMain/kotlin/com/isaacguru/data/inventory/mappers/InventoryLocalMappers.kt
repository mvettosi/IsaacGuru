package com.isaacguru.data.inventory.mappers

import com.isaacguru.data.inventory.local.model.InventoryItemEntity
import com.isaacguru.domain.inventory.model.Character
import com.isaacguru.domain.inventory.model.Consumable
import com.isaacguru.domain.inventory.model.Curse
import com.isaacguru.domain.inventory.model.InventoryItem
import com.isaacguru.domain.inventory.model.Item
import com.isaacguru.domain.inventory.model.Machine
import com.isaacguru.domain.inventory.model.Pickup
import com.isaacguru.domain.inventory.model.Transformation
import com.isaacguru.domain.inventory.model.Trinket
import isaacguru.composeapp.generated.resources.Res
import kotlinx.io.files.Path
import org.jetbrains.compose.resources.ExperimentalResourceApi

fun InventoryItemEntity.toDomain(): InventoryItem? =
    when (main_type) {
      "item" -> toItem()
      "trinket" -> toTrinket()
      "pickup" -> toPickup()
      "consumable" -> toConsumable()
      "machine" -> toMachine()
      "character" -> toCharacter()
      "transformation" -> toTransformation()
      "curse" -> toCurse()
      else -> null
    }

fun InventoryItemEntity.toItem(): Item =
    when (display_type) {
      "Active item" ->
          Item.Active(
              id = id,
              rawId = id_raw ?: "",
              orderId = order_id?.toInt() ?: Int.MAX_VALUE,
              name = name ?: "",
              description = bot_description ?: "",
              image = gfxfull.toResourceUri(),
              keywords = keywords.splitToKeywords(),
              quote = description,
              unlockMethod = unlock_method ?: "",
              costume = costume ?: "",
              itemPools = pools.splitItemPools(),
              quality = quality?.toInt() ?: 0,
              transformations = transformations.splitToKeywords(),
              maxCharges = maxcharges?.toInt() ?: 0,
              chargeType = chargetype.toChargeType(),
              cooldown = cooldown?.toInt() ?: 0,
          )
      else ->
          Item.Passive(
              id = id,
              rawId = id_raw ?: "",
              orderId = order_id?.toInt() ?: Int.MAX_VALUE,
              name = name ?: "",
              description = bot_description ?: "",
              image = gfxfull.toResourceUri(),
              keywords = keywords.splitToKeywords(),
              quote = description,
              unlockMethod = unlock_method ?: "",
              costume = costume ?: "",
              itemPools = pools.splitItemPools(),
              quality = quality?.toInt() ?: 0,
              transformations = transformations.splitToKeywords(),
          )
    }

@OptIn(ExperimentalResourceApi::class)
fun String?.toResourceUri(): String = // this?.let { "files/${Path(it).name}" } ?: ""
runCatching { Res.getUri("files/${Path(this!!).name}") }.getOrDefault("")

fun String?.splitToKeywords(): List<String> = this?.split(" ") ?: emptyList()

fun String?.splitItemPools(): List<String> =
    this?.removePrefix("@")?.split("@")?.map { it.substringBefore("#") } ?: emptyList()

fun String?.toChargeType(): Item.Active.ChargeType =
    when (this) {
      "timed" -> Item.Active.ChargeType.TIMED
      "special" -> Item.Active.ChargeType.SPECIAL
      else -> Item.Active.ChargeType.DEFAULT
    }

fun InventoryItemEntity.toTrinket(): Trinket =
    Trinket(
        id = id,
        rawId = id_raw ?: "",
        orderId = order_id?.toInt() ?: Int.MAX_VALUE,
        name = name ?: "",
        description = bot_description ?: "",
        image = gfxfull.toResourceUri(),
        keywords = keywords.splitToKeywords(),
        quote = description,
        unlockMethod = unlock_method,
    )

fun InventoryItemEntity.toPickup(): Pickup =
    Pickup(
        id = id,
        rawId = id_raw ?: "",
        orderId = order_id?.toInt() ?: Int.MAX_VALUE,
        name = name ?: "",
        description = bot_description ?: "",
        image = gfxfull.toResourceUri(),
        keywords = keywords.splitToKeywords(),
        unlockMethod = unlock_method,
    )

fun InventoryItemEntity.toConsumable(): Consumable =
    Consumable(
        id = id,
        rawId = id_raw ?: "",
        orderId = order_id?.toInt() ?: Int.MAX_VALUE,
        name = name ?: "",
        description = bot_description ?: "",
        image = gfxfull.toResourceUri(),
        keywords = keywords.splitToKeywords(),
        quote = description,
        unlockMethod = unlock_method,
        typeDescription = display_type ?: "",
    )

fun InventoryItemEntity.toMachine(): Machine =
    Machine(
        id = id,
        rawId = id_raw ?: "",
        orderId = order_id?.toInt() ?: Int.MAX_VALUE,
        name = name ?: "",
        description = bot_description ?: "",
        image = gfxfull.toResourceUri(),
        keywords = keywords.splitToKeywords(),
    )

fun InventoryItemEntity.toCharacter(): Character =
    Character(
        id = id,
        rawId = id_raw ?: "",
        orderId = order_id?.toInt() ?: Int.MAX_VALUE,
        name = name ?: "",
        description = bot_description ?: "",
        image = gfxfull.toResourceUri(),
        keywords = keywords.splitToKeywords(),
        costume = costume ?: "",
        unlockMethod = unlock_method,
    )

fun InventoryItemEntity.toTransformation(): Transformation =
    Transformation(
        id = id,
        rawId = id_raw ?: "",
        orderId = order_id?.toInt() ?: Int.MAX_VALUE,
        name = name ?: "",
        description = bot_description ?: "",
        image = gfxfull.toResourceUri(),
        keywords = keywords.splitToKeywords(),
        costume = costume ?: "",
    )

fun InventoryItemEntity.toCurse(): Curse =
    Curse(
        id = id,
        rawId = id_raw ?: "",
        orderId = order_id?.toInt() ?: Int.MAX_VALUE,
        name = name ?: "",
        description = bot_description ?: "",
        image = gfxfull.toResourceUri(),
        keywords = keywords.splitToKeywords(),
    )
