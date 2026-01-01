package org.example

class PeerSection() : Section() {
    var publicKey : String? = null
    var endpoint : IpAddress? = null
    var allowedIps : List<IpAddress>? = null
    var persistentKeepAlive: Int? = null

    constructor(
        publicKey: String,
        endpoint: IpAddress,
        allowedIps: List<IpAddress>,
        persistentKeepAlive: Int
    ) : this() {
        this.publicKey = publicKey
        this.endpoint = endpoint
        this.allowedIps = allowedIps
        this.persistentKeepAlive = persistentKeepAlive
    }

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

    override fun checkSection() {
        require(publicKey != null)
        require(endpoint != null)
        require(allowedIps != null)
    }

    override fun getProperties(): Map<String, String> {
        return buildMap {
            publicKey?.let { put("PublicKey", it) }
            endpoint?.let { put("Endpoint", it.toString()) }
            allowedIps?.let { put("AllowedIPs", it.joinToString(",")) }
            persistentKeepAlive?.let { put("PersistentKeepalive", it.toString()) }
        }
    }
}