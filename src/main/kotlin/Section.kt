package org.example

abstract class Section {
    abstract fun putProperty(property : String, value : String)
    abstract fun checkSection()
}

const val INTERFACE_SECTION = "Interface"
const val PEER_SECTION = "Peer"