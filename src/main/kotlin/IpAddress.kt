package org.matkini

data class IpAddress(
    val address: String,
    val mask: String? = null,
    val port: String? = null
) {
    override fun toString(): String {
        return "$address${mask?.let { "/$it" } ?: ""}${port?.let { ":$it" } ?: ""}"
    }

    companion object {
        fun fromString(address: String) : IpAddress {
            if (address.contains("/") && address.contains(":")) {
                val (address, mask, port) = address.split("/", ":")
                return IpAddress(address, mask = mask, port = port)
            } else if (address.contains("/")) {
                val (address, mask) = address.split("/")
                return IpAddress(address, mask = mask)
            } else if (address.contains(":")) {
                val (address, port) = address.split(":")
                return IpAddress(address, port = port)
            } else {
                return IpAddress(address)
            }
        }

        fun intToIpStr(ip: Int): String =
            "${(ip ushr 24) and 0xFF}." +
                    "${(ip ushr 16) and 0xFF}." +
                    "${(ip ushr 8) and 0xFF}." +
                    "${ip and 0xFF}"

        fun maskToInt(cidr: Int): Int =
            if (cidr == 0) 0 else -1 shl (32 - cidr)
    }

    fun getBroadcast() : IpAddress {
        require(mask != null)
        return copy(
            address = intToIpStr(getBroadcastInt()),
        )
    }

    fun getNetwork() : IpAddress {
        require(mask != null)
        return copy(
            address = intToIpStr(getNetworkInt()),
        )
    }

    fun getBroadcastInt() : Int {
        require(mask != null)
        return ipToInt() or maskToInt(mask.toInt()).inv()
    }

    fun getNetworkInt() : Int {
        require(mask != null)
        return ipToInt() and maskToInt(mask.toInt())
    }

    fun ipToInt() : Int = address.split(".").let { p ->
        (p[0].toInt() shl 24) or
        (p[1].toInt() shl 16) or
        (p[2].toInt() shl 8) or
                p[3].toInt()
    }
}