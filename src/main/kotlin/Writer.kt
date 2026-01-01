package org.example

import java.nio.file.Files
import java.nio.file.Path
import java.util.Base64

class Writer {
    private constructor()

    companion object {
        fun writeToFile(configFile: ConfigFile, path: Path) {
            Files.write(path, getLines(configFile), Charsets.UTF_8)
        }

        fun getLines(configFile: ConfigFile) : List<String> {
            val lines = mutableListOf<String>()

            configFile.interfaceSection.let {
                lines.add("[Interface]")
                lines.addAll(it.getProperties().map { (key, value) -> "$key = $value" })
                lines.add("")
            }

            configFile.peerSections.forEach {
                lines.add("[Peer]")
                lines.addAll(it.getProperties().map { (key, value) -> "$key = $value" })
                lines.add("")
            }

            return lines
        }
    }
}