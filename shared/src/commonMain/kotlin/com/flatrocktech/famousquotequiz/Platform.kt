package com.flatrocktech.famousquotequiz

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform