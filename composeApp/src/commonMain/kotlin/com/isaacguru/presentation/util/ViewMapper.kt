package com.isaacguru.presentation.util

interface ViewMapper<Domain, View> {
  fun mapToView(domain: Domain): View
}
