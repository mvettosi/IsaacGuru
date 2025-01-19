package com.isaacguru.domain.gamemod.model

import com.isaacguru.domain.inventory.model.Character
import com.isaacguru.domain.inventory.model.Curse
import com.isaacguru.domain.inventory.model.Item
import com.isaacguru.domain.inventory.model.Machine
import com.isaacguru.domain.inventory.model.Pickup
import com.isaacguru.domain.inventory.model.Transformation
import com.isaacguru.domain.inventory.model.Trinket

data class GameAspects(
    val items: List<Item>,
    val trinkets: List<Trinket>,
    val pickups: List<Pickup>,
    val machines: List<Machine>,
    val characters: List<Character>,
    val transformations: List<Transformation>,
    val curses: List<Curse>,
)
