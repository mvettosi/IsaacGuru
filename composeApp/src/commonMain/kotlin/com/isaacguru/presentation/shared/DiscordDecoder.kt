package com.isaacguru.presentation.shared

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.sp
import com.isaacguru.presentation.shared.components.ResImage
import isaacguru.composeapp.generated.resources.*
import isaacguru.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.DrawableResource

enum class DiscordSprite(val id: String, val resource: DrawableResource) {
  // Arrows
  StatArrowup(id = ":stat_arrowup:1263934766697287770", resource = Res.drawable.stat_arrowup),
  StatArrowdn(id = ":stat_arrowdn:1263940042561421488", resource = Res.drawable.stat_arrowdn),

  // Stats
  StatDamage(id = ":stat_damage:1263940065411727444", resource = Res.drawable.stat_damage),
  StatTears(id = ":stat_tears:1263940253836771458", resource = Res.drawable.stat_tears),
  StatShotspeed(id = ":stat_shotspeed:1263940211591876651", resource = Res.drawable.stat_shotspeed),
  StatRange(id = ":stat_range:1263940180163821659", resource = Res.drawable.stat_range),
  StatSpeed(id = ":stat_speed:1263940240675045417", resource = Res.drawable.stat_speed),
  StatSize(id = ":stat_size:1263944884474347630", resource = Res.drawable.stat_size),
  StatTearsize(id = ":stat_tearsize:1263944891675967581", resource = Res.drawable.stat_tearsize),
  StatLuck(id = ":stat_luck:1263940158839849010", resource = Res.drawable.stat_luck),

  // Others
  StatExclamation(
      id = ":stat_exclamation:1263940092494483568", resource = Res.drawable.stat_exclamation),
  StatRed(id = ":stat_red:1263940190515232838", resource = Res.drawable.stat_red),
  StatBomb(id = ":stat_bomb:1263944832515178559", resource = Res.drawable.stat_bomb),
  StatHalfred(id = ":stat_halfred:1263940116741754992", resource = Res.drawable.stat_halfred),
  StatSoul(id = ":stat_soul:1263940226758479953", resource = Res.drawable.stat_soul),
  StatHard(id = ":stat_hard:1263944869878173857", resource = Res.drawable.stat_hard),
  StatPlanetarium(
      id = ":stat_planetarium:1263940169824866316", resource = Res.drawable.stat_planetarium),
  StatKey(id = ":stat_key:1263944873510305925", resource = Res.drawable.stat_key),
  StatCoin(id = ":stat_coin:1263944839830179950", resource = Res.drawable.stat_coin),
  StatAngeldevil(
      id = ":stat_angeldevil:1263940944848228474", resource = Res.drawable.stat_angeldevil),
  StatBlack(id = ":stat_black:1263940054078849086", resource = Res.drawable.stat_black),
  StatEmptyred(id = ":stat_emptyred:1263944849988784259", resource = Res.drawable.stat_emptyred),
  StatAngel(id = ":stat_angel:1263940933913804893", resource = Res.drawable.stat_angel),
  StatGreed(id = ":stat_greed:1263944856376443104", resource = Res.drawable.stat_greed),
  StatGreedier(id = ":stat_greedier:1263944862378491966", resource = Res.drawable.stat_greedier),
  StatHalfsoul(id = ":stat_halfsoul:1263940146504532049", resource = Res.drawable.stat_halfsoul),
  StatHalfblack(id = ":stat_halfblack:1263940106969026731", resource = Res.drawable.stat_halfblack),
  Giga(id = ":giga:1283119055972335780", resource = Res.drawable.giga),
  StatDevil(id = ":stat_devil:1263940081635557456", resource = Res.drawable.stat_devil),

  // Pools
  PoolTreasure(id = ":pool_treasure:1263939981387370660", resource = Res.drawable.pool_treasure),
  PoolShop(id = ":pool_shop:1263939877800640592", resource = Res.drawable.pool_shop),
  PoolBoss(id = ":pool_boss:1263939576225861724", resource = Res.drawable.pool_boss),
  PoolDevil(id = ":pool_devil:1263939637152448675", resource = Res.drawable.pool_devil),
  PoolAngel(id = ":pool_angel:1263939483640795309", resource = Res.drawable.pool_angel),
  PoolSecret(id = ":pool_secret:1263939857604939916", resource = Res.drawable.pool_secret),
  PoolLibrary(id = ":pool_library:1263939667091521548", resource = Res.drawable.pool_library),
  PoolShellGame(id = ":pool_shellGame:1263939867633651814", resource = Res.drawable.pool_shellGame),
  PoolGoldenChest(
      id = ":pool_goldenChest:1263939653560569937", resource = Res.drawable.pool_goldenChest),
  PoolRedChest(id = ":pool_redChest:1263939834863423528", resource = Res.drawable.pool_redChest),
  poolBeggar(id = ":pool_beggar:1263939549051224188", resource = Res.drawable.pool_beggar),
  PoolDemonBeggar(
      id = ":pool_demonBeggar:1263939622791151678", resource = Res.drawable.pool_demonBeggar),
  PoolCurse(id = ":pool_curse:1263939609058869310", resource = Res.drawable.pool_curse),
  PoolKeyMaster(id = ":pool_keyMaster:1263939783009374278", resource = Res.drawable.pool_keyMaster),
  PoolBatteryBum(
      id = ":pool_batteryBum:1263939537277812897", resource = Res.drawable.pool_batteryBum),
  PoolMomsChest(id = ":pool_momsChest:1263939792996007937", resource = Res.drawable.pool_momsChest),
  PoolGreedTreasure(
      id = ":pool_greedTreasure:1277368539715735707", resource = Res.drawable.pool_greedTreasure),
  PoolGreedBoss(id = ":pool_greedBoss:1277368566240378983", resource = Res.drawable.pool_greedBoss),
  PoolGreedShop(id = ":pool_greedShop:1263939761450520678", resource = Res.drawable.pool_greedShop),
  PoolGreedCurse(
      id = ":pool_greedCurse:1263939722061811792", resource = Res.drawable.pool_greedCurse),
  PoolGreedDevil(
      id = ":pool_greedDevil:1263939736192553021", resource = Res.drawable.pool_greedDevil),
  PoolGreedAngel(
      id = ":pool_greedAngel:1263939689841430558", resource = Res.drawable.pool_greedAngel),
  PoolGreedSecret(
      id = ":pool_greedSecret:1263939749786288341", resource = Res.drawable.pool_greedSecret),
  PoolCraneGame(id = ":pool_craneGame:1263939597939904574", resource = Res.drawable.pool_craneGame),
  PoolUltraSecret(
      id = ":pool_ultraSecret:1263939996617015316", resource = Res.drawable.pool_ultraSecret),
  PoolBombBum(id = ":pool_bombBum:1263939561537540177", resource = Res.drawable.pool_bombBum),
  PoolPlanetarium(
      id = ":pool_planetarium:1263939815276286044", resource = Res.drawable.pool_planetarium),
  PoolOldChest(id = ":pool_oldChest:1263939802227540099", resource = Res.drawable.pool_oldChest),
  PoolBabyShop(id = ":pool_babyShop:1263939500019679373", resource = Res.drawable.pool_babyShop),
  PoolWoodenChest(
      id = ":pool_woodenChest:1263940006880477226", resource = Res.drawable.pool_woodenChest),
  PoolRottenBeggar(
      id = ":pool_rottenBeggar:1263939847672959037", resource = Res.drawable.pool_rottenBeggar);

  companion object {
    fun fromId(id: String): DiscordSprite? = entries.find { it.id.contains(id) }
  }
}

fun inlineImageOf(id: String, resource: DrawableResource): Pair<String, InlineTextContent> =
    id to
        InlineTextContent(Placeholder(20.sp, 20.sp, PlaceholderVerticalAlign.TextCenter)) {
          ResImage(modifier = Modifier.fillMaxSize(), resource = resource, contentDescription = id)
        }

val inlineDiscordMap =
    buildList { DiscordSprite.entries.forEach { add(inlineImageOf(it.id, it.resource)) } }.toMap()

fun String.fromDiscordString(): AnnotatedString = buildAnnotatedString {
  val blocks =
      this@fromDiscordString.replace("\r\n", "")
          .replace("^n", "\n")
          .split("\u003c", "\u003e")
          .filterNot { it.isEmpty() }

  blocks.forEach {
    if (it.startsWith(":")) {
      appendInlineContent(id = it)
    } else {
      append(it)
    }
  }
}

fun List<String>.fromPools(): AnnotatedString = buildAnnotatedString {
  this@fromPools.mapNotNull { poolName -> DiscordSprite.fromId("pool_$poolName") }.forEach {
      discordSprite ->
    appendInlineContent(id = discordSprite.id)
  }
}
