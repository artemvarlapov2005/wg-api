package org.matkini

data class ConfigFile(
    val interfaceSection : InterfaceSection,
    val peerSections : List<PeerSection>
)

class ConfigFileBuilder {
    private var interfaceSection : InterfaceSection? = null
    private var peerSections : MutableList<PeerSection> = mutableListOf()

    fun addInterfaceSection(section : InterfaceSection) {
        if (interfaceSection != null) {
            throw IllegalStateException("Interface section already exists")
        }
        interfaceSection = section
    }

    fun addPeerSection(section : PeerSection) {
        peerSections.add(section)
    }

    fun build() : ConfigFile {
        require(interfaceSection != null)

        return ConfigFile(interfaceSection!!, peerSections)
    }
}