package org.example

data class IpAddress(
    val address: String,
    val mask: String? = null,
    val port: String? = null
)