package com.isaacguru.domain.itempool.repository

import com.isaacguru.domain.itempool.model.ItemPool

interface ItemPoolRepository {
  suspend fun getItemPools(): List<ItemPool>
}
