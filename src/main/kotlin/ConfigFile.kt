package org.matkini

import java.time.Instant
import java.time.LocalDateTime

data class ConfigFile(
    val interfaceSection : InterfaceSection,
    val peerSections : List<PeerSection>,
    val updateTime: Instant? = null
) {
    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + interfaceSection.hashCode()
        result = 31 * result + peerSections.hashCode()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ConfigFile

        if (interfaceSection != other.interfaceSection) return false
        if (peerSections != other.peerSections) return false

        return true
    }
}

class ConfigFileBuilder {
    private var interfaceSection : InterfaceSection? = null
    private var peerSections : MutableList<PeerSection> = mutableListOf()
    private var updateTime : Instant? = null

    fun addInterfaceSection(section : InterfaceSection) {
        if (interfaceSection != null) {
            throw IllegalStateException("Interface section already exists")
        }
        interfaceSection = section
    }

    fun addUpdateTime(time : Instant) {
        updateTime = time
    }

    fun addPeerSection(section : PeerSection) {
        peerSections.add(section)
    }

    fun build() : ConfigFile {
        require(interfaceSection != null)

        return ConfigFile(interfaceSection!!, peerSections, updateTime = this.updateTime)
    }
}