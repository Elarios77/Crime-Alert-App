package com.example.criminalalertapp.util.navigation

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlin.reflect.KClass

@Polymorphic
@Serializable
open class NavigationRoute {

    @Transient
    var popUpToKlass: KClass<out NavigationRoute>? = null

    @Transient
    var inclusive: Boolean = false

    @Transient
    var launchTop: Boolean = true

    @Transient
    var restoreState: Boolean = false

    @Transient
    var saveState: Boolean = false

    constructor(
        popUpToKlass: KClass<out NavigationRoute>? = null,
        inclusive: Boolean = false,
        launchTop: Boolean = true,
        restoreState: Boolean = false,
        saveState: Boolean = false
    ) {
        this.popUpToKlass = popUpToKlass
        this.inclusive = inclusive
        this.launchTop = launchTop
        this.restoreState = restoreState
        this.saveState = saveState
    }
}

internal fun<T : NavigationRoute> T.popUpTo(
    klass : KClass<out NavigationRoute>,
    inclusive : Boolean = true
):T = apply {
    @Suppress("UNCHECKED_CAST")
    popUpToKlass = klass as KClass<out NavigationRoute>
    this.inclusive = inclusive
}