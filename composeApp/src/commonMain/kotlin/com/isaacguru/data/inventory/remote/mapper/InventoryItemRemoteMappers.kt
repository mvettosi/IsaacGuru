package com.isaacguru.data.inventory.remote.mapper

import com.isaacguru.data.inventory.remote.model.InventoryItemRemote
import com.isaacguru.data.inventory.remote.model.InventoryRemote
import com.isaacguru.domain.character.Character
import com.isaacguru.domain.collectable.item.model.Item
import com.isaacguru.domain.collectable.pickup.model.Pickup
import com.isaacguru.domain.collectable.trinket.model.Trinket
import com.isaacguru.domain.curse.model.Curse
import com.isaacguru.domain.gamemod.model.GameAspects
import com.isaacguru.domain.machine.model.Machine
import com.isaacguru.domain.transformation.model.Transformation
import isaacguru.composeapp.generated.resources.Res
import kotlinx.io.files.Path
import org.jetbrains.compose.resources.ExperimentalResourceApi

fun InventoryRemote.toDomain(): GameAspects =
    GameAspects(
        items = item.map { it.toItem() },
        trinkets = trinket.map { it.toTrinket() },
        pickups = pickup.map { it.toPickup() },
        machines = machine.map { it.toMachine() },
        characters = character.map { it.toCharacter() },
        transformations = transformation.map { it.toTransformation() },
        curses = curse.map { it.toCurse() },
    )

fun InventoryItemRemote.toItem(): Item =
    when (display_type) {
      "Active item" ->
          Item.Active(
              id = id_raw ?: "",
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
              id = id_raw ?: "",
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

fun InventoryItemRemote.toTrinket(): Trinket =
    Trinket(
        id = id_raw ?: "",
        name = name ?: "",
        description = description ?: "",
        image = gfxfull.toResourceUri(),
        keywords = keywords.splitToKeywords(),
        quote = description,
        unlockMethod = unlock_method,
    )

fun InventoryItemRemote.toPickup(): Pickup =
    Pickup(
        id = id_raw ?: "",
        name = name ?: "",
        description = description ?: "",
        image = gfxfull.toResourceUri(),
        keywords = keywords.splitToKeywords(),
        unlockMethod = unlock_method,
    )

fun InventoryItemRemote.toMachine(): Machine =
    Machine(
        id = id_raw ?: "",
        name = name ?: "",
        description = description ?: "",
        image = gfxfull.toResourceUri(),
        keywords = keywords.splitToKeywords(),
    )

fun InventoryItemRemote.toCharacter(): Character =
    Character(
        id = id_raw ?: "",
        name = name ?: "",
        description = description ?: "",
        image = gfxfull.toResourceUri(),
        keywords = keywords.splitToKeywords(),
        costume = costume ?: "",
        unlockMethod = unlock_method,
    )

fun InventoryItemRemote.toTransformation(): Transformation =
    Transformation(
        id = id_raw ?: "",
        name = name ?: "",
        description = description ?: "",
        image = gfxfull.toResourceUri(),
        keywords = keywords.splitToKeywords(),
        costume = costume ?: "",
    )

fun InventoryItemRemote.toCurse(): Curse =
    Curse(
        id = id_raw ?: "",
        name = name ?: "",
        description = description ?: "",
        image = gfxfull.toResourceUri(),
        keywords = keywords.splitToKeywords(),
    )
