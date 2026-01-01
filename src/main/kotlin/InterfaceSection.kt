package org.matkini

class InterfaceSection(
    val address: IpAddress,
    val privateKey: String,
    val dns: List<IpAddress>? = null,
    val jc: Int,
    val jmin: Int,
    val jmax: Int,
    val s1: Int,
    val s2: Int,
    val h1: Int,
    val h2: Int,
    val h3: Int,
    val h4: Int
) : Section() {
    override fun getProperties(): Map<String, String> {
        return buildMap {
            return buildMap {
                put("Address", address.toString())
                put("PrivateKey", privateKey)
                dns?.let { put("DNS", it.joinToString(",")) }
                put("Jc", jc.toString())
                put("Jmin", jmin.toString())
                put("Jmax", jmax.toString())
                put("S1", s1.toString())
                put("S2", s2.toString())
                put("H1", h1.toString())
                put("H2", h2.toString())
                put("H3", h3.toString())
                put("H4", h4.toString())
            }
        }
    }
}

class InterfaceSectionBuilder : SectionBuilder() {
    var address : IpAddress? = null
    var privateKey : String? = null
    var dns : List<IpAddress>? = null
    var jc : Int? = null
    var jmin : Int? = null
    var jmax : Int? = null
    var s1 : Int? = null
    var s2 : Int? = null
    var h1 : Int? = null
    var h2 : Int? = null
    var h3 : Int? = null
    var h4 : Int? = null

    override fun putProperty(property: String, value: String) {
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

    fun build() : InterfaceSection {
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
        return InterfaceSection(
            address!!,
            privateKey!!,
            dns,
            jc!!,
            jmin!!,
            jmax!!,
            s1!!,
            s2!!,
            h1!!,
            h2!!,
            h3!!,
            h4!!
        )
    }
}