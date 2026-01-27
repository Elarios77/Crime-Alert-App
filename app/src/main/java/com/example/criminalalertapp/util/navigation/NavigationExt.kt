package com.example.criminalalertapp.util.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import kotlin.reflect.KType

inline fun <reified T : Any> NavGraphBuilder.screenViewComposable(
    typeMap: Map<KType, NavType<*>> = emptyMap(),
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline enter: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards EnterTransition?)? = null,
    noinline exit: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards ExitTransition?)? = null,
    noinline popEnter: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards EnterTransition?)? = enter,
    noinline popExit: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards ExitTransition?)? = exit,
    noinline sizeTransform: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards SizeTransform?)? = null,
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable<T>(
        typeMap = typeMap,
        deepLinks = deepLinks,
        enterTransition = enter,
        exitTransition = exit,
        popEnterTransition = popEnter,
        popExitTransition = popExit,
        sizeTransform = sizeTransform,
        content = { entry -> content(entry) }
    )
}

inline fun <reified T : Any> NavGraphBuilder.glanceFade(
    typeMap: Map<KType, NavType<*>> = emptyMap(),
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline sizeTransform: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> SizeTransform?)? = null,
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    screenViewComposable<T>(
        typeMap = typeMap,
        deepLinks = deepLinks,
        enter = {
            fadeIn(animationSpec = tween(durationMillis = 300)) +
                    slideInHorizontally(
                        animationSpec = tween(durationMillis = 350),
                        initialOffsetX = { it / 5 })
        },
        exit = {
            fadeOut(animationSpec = tween(durationMillis = 300)) +
                    slideOutHorizontally(
                        animationSpec = tween(durationMillis = 350),
                        targetOffsetX = { -it / 10 })
        },
        popEnter = {
            fadeIn(animationSpec = tween(durationMillis = 300, delayMillis = 50)) +
                    slideInHorizontally(
                        animationSpec = tween(durationMillis = 350, delayMillis = 50),
                        initialOffsetX = { -it / 10 })
        },
        popExit = {
            fadeOut(animationSpec = tween(durationMillis = 300)) +
                    slideOutHorizontally(
                        animationSpec = tween(durationMillis = 350),
                        targetOffsetX = { it / 10 })
        },
        sizeTransform = sizeTransform,
        content = content
    )
}