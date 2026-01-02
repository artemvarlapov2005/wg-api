package org.matkini

import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Stream

class Reader {
    private constructor()

    companion object {
        fun readFile(path: Path) : ConfigFile {
            val lines = Files.lines(path, Charsets.UTF_8)

            return readStream(lines)
        }

        fun readStream(lines : Stream<String>) : ConfigFile {
            val builder = ConfigFileBuilder()

            var currentSectionBuilder : SectionBuilder? = null

            lines.forEach {
                if (isStartSection(it)) {
                    saveSection(builder, currentSectionBuilder)
                    val sectionName = getSectionName(it)

                    if (sectionName == INTERFACE_SECTION) {
                        currentSectionBuilder = InterfaceSectionBuilder()
                    }

                    if (sectionName == PEER_SECTION) {
                        currentSectionBuilder = PeerSectionBuilder()
                    }
                } else if (isPropertySection(it)) {
                    val (property, value) = getProperty(it)
                    currentSectionBuilder?.putProperty(property, value)
                }
            }

            saveSection(builder, currentSectionBuilder)

            return builder.build()
        }

        private fun saveSection(builder: ConfigFileBuilder, sectionBuilder: SectionBuilder?) {
            when (sectionBuilder) {
                is InterfaceSectionBuilder -> {
                    builder.addInterfaceSection(sectionBuilder.build())
                }
                is PeerSectionBuilder -> {
                    builder.addPeerSection(sectionBuilder.build())
                }
            }
        }
    }
}

