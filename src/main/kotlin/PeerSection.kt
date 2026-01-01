package org.matkini

class PeerSection(
   val publicKey: String,
   val endpoint: IpAddress,
   val allowedIps: List<IpAddress>,
   val persistentKeepAlive: Int
) : Section() {
    override fun getProperties(): Map<String, String> {
        return buildMap {
            put("PublicKey", publicKey)
            put("Endpoint", endpoint.toString())
            put("AllowedIPs", allowedIps.joinToString(","))
            put("PersistentKeepalive", persistentKeepAlive.toString())
        }
    }
}

class PeerSectionBuilder() : SectionBuilder() {
    var publicKey : String? = null
    var endpoint : IpAddress? = null
    var allowedIps : List<IpAddress>? = null
    var persistentKeepAlive: Int? = null

    override fun putProperty(property : String, value : String) {
        when (property) {
            "PublicKey" -> publicKey = value
            "Endpoint" -> endpoint = value.let {
                it.split(":").let {
                    IpAddress(it[0], port = it[1])
                }
            }
            "AllowedIPs" -> allowedIps = value.let {
                it.split(",").map {
                        ipWithMask -> ipWithMask.split("/").let {
                    IpAddress(it[0], mask = it[1])
                }
                }
            }
            "PersistentKeepalive" -> persistentKeepAlive = value.toInt()
        }
    }

    fun build() : PeerSection {
        require(publicKey != null)
        require(endpoint != null)
        require(allowedIps != null)
        return PeerSection(publicKey!!, endpoint!!, allowedIps!!, persistentKeepAlive!!)
    }
}