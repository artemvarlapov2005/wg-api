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
            "publicKey" -> publicKey = value
            "endpoint" -> endpoint = value.let {
                it.split(":").let {
                    IpAddress(it[0], port = it[1])
                }
            }
            "allowedIps" -> allowedIps = value.let {
                it.split(",").map {
                        ipWithMask -> ipWithMask.split("/").let {
                    IpAddress(it[0], mask = it[1])
                }
                }
            }
            "persistentKeepAlive" -> persistentKeepAlive = value.toInt()
        }
    }
}