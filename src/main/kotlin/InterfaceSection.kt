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
            "address" -> address = value.let {
                it.split("/").let {
                    IpAddress(it[0], mask = it[1])
                }
            }
            "privateKey" -> privateKey = value
            "dns" -> dns = value.let {
                it.split(",").map { ip -> IpAddress(ip) }
            }
            "jc" -> jc = value.toInt()
            "jmin" -> jmin = value.toInt()
            "jmax" -> jmax = value.toInt()
            "s1" -> s1 = value.toInt()
            "s2" -> s2 = value.toInt()
            "h1" -> h1 = value.toInt()
            "h2" -> h2 = value.toInt()
            "h3" -> h3 = value.toInt()
            "h4" -> h4 = value.toInt()
        }
    }
}