package org.matkini

import java.nio.file.Files
import java.nio.file.Path

class Writer {
    private constructor()

    companion object {
        fun writeToFile(configFile: ConfigFile, path: Path) {
            Files.write(path, getLines(configFile), Charsets.UTF_8)
        }

        fun getLines(configFile: ConfigFile) : List<String> {
            val lines = mutableListOf<String>()

            configFile.interfaceSection.let {
                lines.add("[$INTERFACE_SECTION]")
                lines.addAll(it.getProperties().flatMap { (key, value) ->
                    value.map {
                        "$key = $it"
                    }
                })
                lines.add("")
            }

            configFile.peerSections.forEach {
                lines.add("[$PEER_SECTION]")
                lines.addAll(it.getProperties().flatMap { (key, value) ->
                    value.map {
                        "$key = $it"
                    }
                })
                lines.add("")
            }

            return lines
        }
    }
}