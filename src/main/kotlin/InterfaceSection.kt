package org.example

class InterfaceSection() : Section() {
    var address : IpAddress? = null;
    var privateKey : String? = null;
    var dns : List<IpAddress>? = null
    var jc : Int? = null;
    var jmin : Int? = null;
    var jmax : Int? = null;
    var s1 : Int? = null;
    var s2 : Int? = null;
    var h1 : Int? = null;
    var h2 : Int? = null;
    var h3 : Int? = null;
    var h4 : Int? = null;

    constructor(
        address: IpAddress,
        privateKey: String,
        dns: List<IpAddress>? = null,
        jc: Int,
        jmin: Int,
        jmax: Int,
        s1: Int,
        s2: Int,
        h1: Int,
        h2: Int,
        h3: Int,
        h4: Int
    ) : this() {
        this.address = address
        this.privateKey = privateKey
        this.dns = dns
        this.jc = jc
        this.jmin = jmin
        this.jmax = jmax
        this.s1 = s1
        this.s2 = s2
        this.h1 = h1
        this.h2 = h2
        this.h3 = h3
        this.h4 = h4
    }

    override fun putProperty(property : String, value : String) {
        when (property) {
            "Address" -> address = value.let {
                it.split("/").let {
                    IpAddress(it[0], mask = it[1])
                }
            }
            "PrivateKey" -> privateKey = value
            "DNS" -> dns = value.let {
                it.split(",").map { ip -> IpAddress(ip) }
            }
            "Jc" -> jc = value.toInt()
            "Jmin" -> jmin = value.toInt()
            "Jmax" -> jmax = value.toInt()
            "S1" -> s1 = value.toInt()
            "S2" -> s2 = value.toInt()
            "H1" -> h1 = value.toInt()
            "H2" -> h2 = value.toInt()
            "H3" -> h3 = value.toInt()
            "H4" -> h4 = value.toInt()
        }
    }

    override fun checkSection() {
        require(address != null)
        require(privateKey != null)
        require(jc != null)
        require(jmin != null)
        require(jmax != null)
        require(s1 != null)
        require(s2 != null)
        require(h1 != null)
        require(h2 != null)
        require(h3 != null)
        require(h4 != null)
    }

    override fun getProperties(): Map<String, String> {
        return buildMap {
            return buildMap {
                address?.let { put("Address", it.toString()) }
                privateKey?.let { put("PrivateKey", it) }
                dns?.let { put("DNS", it.joinToString(",")) }
                jc?.let { put("Jc", it.toString()) }
                jmin?.let { put("Jmin", it.toString()) }
                jmax?.let { put("Jmax", it.toString()) }
                s1?.let { put("S1", it.toString()) }
                s2?.let { put("S2", it.toString()) }
                h1?.let { put("H1", it.toString()) }
                h2?.let { put("H2", it.toString()) }
                h3?.let { put("H3", it.toString()) }
                h4?.let { put("H4", it.toString()) }
            }
        }
    }
}