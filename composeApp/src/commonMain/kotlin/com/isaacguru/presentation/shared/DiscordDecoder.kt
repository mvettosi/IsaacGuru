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

fun inlineImageOf(id: String, resource: DrawableResource): Pair<String, InlineTextContent> =
    id to
        InlineTextContent(Placeholder(20.sp, 20.sp, PlaceholderVerticalAlign.TextCenter)) {
          ResImage(modifier = Modifier.fillMaxSize(), resource = resource, contentDescription = id)
        }

val inlineDiscordMap =
    mapOf(
        // Arrows
        inlineImageOf(
            id = ":stat_arrowup:1263934766697287770", resource = Res.drawable.stat_arrowup),
        inlineImageOf(
            id = ":stat_arrowdn:1263940042561421488", resource = Res.drawable.stat_arrowdn),

        // Stats
        inlineImageOf(id = ":stat_damage:1263940065411727444", resource = Res.drawable.stat_damage),
        inlineImageOf(id = ":stat_tears:1263940253836771458", resource = Res.drawable.stat_tears),
        inlineImageOf(
            id = ":stat_shotspeed:1263940211591876651", resource = Res.drawable.stat_shotspeed),
        inlineImageOf(id = ":stat_range:1263940180163821659", resource = Res.drawable.stat_range),
        inlineImageOf(id = ":stat_speed:1263940240675045417", resource = Res.drawable.stat_speed),
        inlineImageOf(id = ":stat_size:1263944884474347630", resource = Res.drawable.stat_size),
        inlineImageOf(
            id = ":stat_tearsize:1263944891675967581", resource = Res.drawable.stat_tearsize),
        inlineImageOf(id = ":stat_luck:1263940158839849010", resource = Res.drawable.stat_luck),

        // Others
        inlineImageOf(
            id = ":stat_exclamation:1263940092494483568", resource = Res.drawable.stat_exclamation),
        inlineImageOf(id = ":stat_red:1263940190515232838", resource = Res.drawable.stat_red),
        inlineImageOf(id = ":stat_bomb:1263944832515178559", resource = Res.drawable.stat_bomb),
        inlineImageOf(
            id = ":stat_halfred:1263940116741754992", resource = Res.drawable.stat_halfred),
        inlineImageOf(id = ":stat_soul:1263940226758479953", resource = Res.drawable.stat_soul),
        inlineImageOf(id = ":stat_hard:1263944869878173857", resource = Res.drawable.stat_hard),
        inlineImageOf(
            id = ":stat_planetarium:1263940169824866316", resource = Res.drawable.stat_planetarium),
        inlineImageOf(id = ":stat_key:1263944873510305925", resource = Res.drawable.stat_key),
        inlineImageOf(id = ":stat_coin:1263944839830179950", resource = Res.drawable.stat_coin),
        inlineImageOf(
            id = ":stat_angeldevil:1263940944848228474", resource = Res.drawable.stat_angeldevil),
        inlineImageOf(id = ":stat_black:1263940054078849086", resource = Res.drawable.stat_black),
        inlineImageOf(
            id = ":stat_emptyred:1263944849988784259", resource = Res.drawable.stat_emptyred),
        inlineImageOf(id = ":stat_angel:1263940933913804893", resource = Res.drawable.stat_angel),
        inlineImageOf(id = ":stat_greed:1263944856376443104", resource = Res.drawable.stat_greed),
        inlineImageOf(
            id = ":stat_greedier:1263944862378491966", resource = Res.drawable.stat_greedier),
        inlineImageOf(
            id = ":stat_halfsoul:1263940146504532049", resource = Res.drawable.stat_halfsoul),
        inlineImageOf(
            id = ":stat_halfblack:1263940106969026731", resource = Res.drawable.stat_halfblack),
        inlineImageOf(id = ":giga:1283119055972335780", resource = Res.drawable.giga),
        inlineImageOf(id = ":stat_devil:1263940081635557456", resource = Res.drawable.stat_devil),
    )

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
