package org.example

import java.nio.file.Files
import java.nio.file.Path

class Reader {
    private constructor()

    companion object {
        fun readFile(path: Path) : ConfigFile {
            var currentSection : Section? = null

            val lines = Files.lines(path, Charsets.UTF_8)

            val builder = ConfigFileBuilder()

            lines.forEach {
                val trimmed = it.replace(" ", "")
                if (isStartSection(trimmed)) {
                    saveSection(builder, currentSection)
                    val sectionName = getSectionName(trimmed)

                    if (sectionName == INTERFACE_SECTION) {
                        currentSection = InterfaceSection()
                    }

                    if (sectionName == PEER_SECTION) {
                        currentSection = PeerSection()
                    }
                } else {
                    if (isPropertySection(trimmed)) {
                        val (property, value) = getProperty(trimmed)
                        currentSection?.putProperty(property, value)
                    }
                }
            }

            saveSection(builder, currentSection)

            return builder.build()
        }

        private fun saveSection(builder: ConfigFileBuilder, section: Section?) {
            section?.checkSection()
            when (section) {
                is InterfaceSection -> {
                    builder.addInterfaceSection(section)
                }
                is PeerSection -> {
                    builder.addPeerSection(section)
                }
            }
        }
    }
}

