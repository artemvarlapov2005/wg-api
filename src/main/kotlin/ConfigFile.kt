package org.example

data class ConfigFile(
    val interfaceSection : InterfaceSection,
    val peerSections : List<PeerSection>
)
