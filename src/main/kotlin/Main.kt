package org.example

import java.nio.file.Path

fun main() {
    val config = Reader.readFile(Path.of("/Users/a.varlapov/ansible/amnezia_users/35.228.187.128/10.0.0.7.conf"))

    Writer.writeToFile(config, Path.of("/Users/a.varlapov/ansible/amnezia_users/35.228.187.128/10.0.0.7.conf"))
}