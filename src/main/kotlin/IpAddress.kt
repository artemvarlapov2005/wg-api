package org.matkini

data class IpAddress(
    val address: String,
    val mask: String? = null,
    val port: String? = null
) {
    override fun toString(): String {
        return "$address${mask?.let { "/$it" } ?: ""}${port?.let { ":$it" } ?: ""}"
    }
}