package org.example

import java.nio.file.Files
import java.nio.file.Path

class Reader(val path: Path) {
    var currentSection : Section? = null

    var interfaceSection : InterfaceSection? = null
    var peerSections : List<PeerSection> = listOf()

    fun readFile() : ConfigFile {
        currentSection = null;

        val lines = Files.lines(path)

        lines.forEach {
            val trimmed = it.replace(" ", "")
            if (isStartSection(trimmed)) {
                savePrevious()
                val sectionName = getSectionName(trimmed)

                if (sectionName == INTERFACE_SECTION) {
                    currentSection = InterfaceSection()
                }

                if (sectionName == PEER_SECTION) {
                    currentSection = PeerSection()
                }
            } else {
                if (isPropertySection(trimmed)) {
                    val (property, value) = getProperty(it)
                    currentSection?.putProperty(property, value)
                }
            }
        }

        savePrevious()

        require(interfaceSection != null)

        return ConfigFile(interfaceSection!!, peerSections)
    }

    private fun savePrevious() {
        currentSection?.checkSection()
        when (currentSection) {
            is InterfaceSection -> {
                interfaceSection = currentSection as InterfaceSection
            }
            is PeerSection -> {
                peerSections = peerSections + (currentSection as PeerSection)
            }
        }
    }
}