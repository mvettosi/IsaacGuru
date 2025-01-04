package com.isaacguru.domain.gamemod.model

import com.isaacguru.domain.character.Character
import com.isaacguru.domain.collectable.item.model.Item
import com.isaacguru.domain.collectable.pickup.model.Pickup
import com.isaacguru.domain.collectable.trinket.model.Trinket
import com.isaacguru.domain.curse.model.Curse
import com.isaacguru.domain.machine.model.Machine
import com.isaacguru.domain.transformation.model.Transformation

data class GameAspects(
    val items: List<Item>,
    val trinkets: List<Trinket>,
    val pickups: List<Pickup>,
    val machines: List<Machine>,
    val characters: List<Character>,
    val transformations: List<Transformation>,
    val curses: List<Curse>,
)
