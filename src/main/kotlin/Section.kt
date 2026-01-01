package org.example

abstract class Section {
    abstract fun putProperty(property : String, value : String)
    abstract fun checkSection()
    abstract fun getProperties() : Map<String, String>
}

const val INTERFACE_SECTION = "Interface"
const val PEER_SECTION = "Peer"