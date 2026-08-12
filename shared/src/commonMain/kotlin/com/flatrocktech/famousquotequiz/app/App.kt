package com.flatrocktech.famousquotequiz.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.flatrocktech.famousquotequiz.core.theme.FamousQuoteQuizTheme
import com.flatrocktech.famousquotequiz.feature.auth.presentation.LoginScreen
import com.flatrocktech.famousquotequiz.feature.profile.presentation.ProfileScreen
import com.flatrocktech.famousquotequiz.feature.quiz.presentation.QuizScreen
import com.flatrocktech.famousquotequiz.feature.settings.presentation.SettingsScreen
import famousquotequiz.shared.generated.resources.Res
import famousquotequiz.shared.generated.resources.tab_profile
import famousquotequiz.shared.generated.resources.tab_quiz
import famousquotequiz.shared.generated.resources.tab_settings
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun App() {
    FamousQuoteQuizTheme {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        val tabItems = listOf(
            TabItem(
                label = Res.string.tab_quiz,
                icon = Icons.Default.Quiz,
                route = Route.Quiz,
            ),
            TabItem(
                label = Res.string.tab_settings,
                icon = Icons.Default.Settings,
                route = Route.Settings
            ),
            TabItem(
                label = Res.string.tab_profile,
                icon = Icons.Default.Person,
                route = Route.Profile
            )
        )

        val isLoginScreen = currentDestination?.hasRoute(Route.Login::class) == true

        NavigationSuiteScaffold(
            navigationSuiteItems = {
                if (!isLoginScreen) {
                    tabItems.forEach { item ->
                        val isSelected =
                            currentDestination?.hierarchy?.any { it.hasRoute(item.route::class) } == true
                        item(
                            selected = isSelected,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().route!!) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            label = { Text(stringResource(item.label)) },
                            icon = { Icon(item.icon, contentDescription = stringResource(item.label)) }
                        )
                    }
                }
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = Route.Login,
                modifier = Modifier.fillMaxSize()
            ) {
                composable<Route.Login> {
                    LoginScreen(
                        onLoginSuccess = {
                            navController.navigate(Route.Quiz) {
                                popUpTo(Route.Login) { inclusive = true }
                            }
                        }
                    )
                }
                composable<Route.Quiz> {
                    QuizScreen()
                }
                composable<Route.Profile> {
                    ProfileScreen(
                        onLogout = {
                            navController.navigate(Route.Login) {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    )
                }
                composable<Route.Settings> {
                    SettingsScreen()
                }
            }
        }
    }
}

private data class TabItem(
    val label: StringResource,
    val icon: ImageVector,
    val route: Route
)