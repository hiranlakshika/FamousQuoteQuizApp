package com.flatrocktech.famousquotequiz.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.flatrocktech.famousquotequiz.feature.auth.presentation.LoginScreen
import com.flatrocktech.famousquotequiz.feature.profile.presentation.ProfileScreen
import com.flatrocktech.famousquotequiz.feature.quiz.presentation.QuizScreen
import com.flatrocktech.famousquotequiz.feature.settings.presentation.SettingsScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        val tabItems = listOf(
            TabItem(
                label = "Quiz",
                icon = Icons.Default.Quiz,
                route = Route.Quiz,
            ),
            TabItem(
                label = "Profile",
                icon = Icons.Default.Person,
                route = Route.Profile
            ),
            TabItem(
                label = "Settings",
                icon = Icons.Default.Settings,
                route = Route.Settings
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
                            label = { Text(item.label) },
                            icon = { Icon(item.icon, contentDescription = item.label) }
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
    val label: String,
    val icon: ImageVector,
    val route: Route
)