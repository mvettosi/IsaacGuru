package com.isaacguru.presentation.features.inventory.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import com.isaacguru.presentation.shared.FilterOptionColor

data class FilterOption(
    val id: String,
    val label: AnnotatedString,
    val color: Color = FilterOptionColor.Default,
    val selected: Boolean = false
) {
  val selectedTextColor =
      when (color) {
        FilterOptionColor.White, FilterOptionColor.Yellow, FilterOptionColor.Cyan -> Color.Black
        else -> FilterOptionColor.White
      }
}

enum class FilterSection {
  ITEM_TYPE,
  ITEM_QUALITY,
  ITEM_POOLS,
  ITEM_TAGS,
  TRANSFORMATIONS,
  STAT_CHANGES,
  ADDED_IN_DLC,
  UNLOCKABLE,
  GRANTS_COSTUME,
  HAS_YOUTUBE_VIDEO,
  ANIMATED_ICON,
}

private val yesNoOptions =
    listOf(
        FilterOption(id = "yes", label = AnnotatedString("Yes"), color = FilterOptionColor.Green),
        FilterOption(id = "no", label = AnnotatedString("No"), color = FilterOptionColor.Red))

typealias FilterSections = Map<FilterSection, List<FilterOption>>

val defaultFilterSections: FilterSections =
    mapOf(
        FilterSection.ITEM_TYPE to
            listOf(
                FilterOption(
                    id = "active",
                    label = AnnotatedString("Active"),
                    color = FilterOptionColor.Green),
                FilterOption(
                    id = "passive",
                    label = AnnotatedString("Passive"),
                    color = FilterOptionColor.Default),
                FilterOption(
                    id = "familiar",
                    label = AnnotatedString("Familiar"),
                    color = FilterOptionColor.Yellow)),
        FilterSection.ITEM_QUALITY to
            listOf(
                FilterOption(
                    id = "0", label = AnnotatedString("0"), color = FilterOptionColor.Default),
                FilterOption(
                    id = "1", label = AnnotatedString("1"), color = FilterOptionColor.Cyan),
                FilterOption(
                    id = "2", label = AnnotatedString("2"), color = FilterOptionColor.Blue),
                FilterOption(
                    id = "3", label = AnnotatedString("3"), color = FilterOptionColor.Yellow),
                FilterOption(
                    id = "4", label = AnnotatedString("4"), color = FilterOptionColor.Red)),
        FilterSection.ITEM_POOLS to emptyList(),
        FilterSection.ITEM_TAGS to emptyList(),
        FilterSection.TRANSFORMATIONS to emptyList(),
        FilterSection.STAT_CHANGES to emptyList(),
        FilterSection.ADDED_IN_DLC to emptyList(),
        FilterSection.UNLOCKABLE to yesNoOptions,
        FilterSection.GRANTS_COSTUME to yesNoOptions,
        FilterSection.HAS_YOUTUBE_VIDEO to yesNoOptions,
        FilterSection.ANIMATED_ICON to yesNoOptions)

data class FilteringOptions(
    val query: String = "",
    val filterSections: FilterSections = defaultFilterSections
)
