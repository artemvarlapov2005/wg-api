package org.matkini

import java.time.Instant
import java.time.LocalDateTime

data class ConfigFile(
    val interfaceSection : InterfaceSection,
    val peerSections : List<PeerSection>,
) {
    var updateTime : Instant? = null

    constructor(
        interfaceSection : InterfaceSection,
        peerSections : List<PeerSection>,
        updateTime : Instant?
    ) : this(interfaceSection, peerSections) {
        this.updateTime = updateTime
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