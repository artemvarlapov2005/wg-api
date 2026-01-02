package org.matkini

import java.nio.file.Files
import java.nio.file.Path
import java.time.Instant

class Writer {
    private constructor()

    companion object {
        fun writeToFile(configFile: ConfigFile, path: Path, includeTime : Boolean = true) {
            Files.write(path, getLines(configFile, includeTime), Charsets.UTF_8)
        }

        fun getLines(configFile: ConfigFile, includeTime : Boolean = true) : List<String> {
            val lines = mutableListOf<String>()

            if (includeTime) lines.add("#v${Instant.now().epochSecond}")

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