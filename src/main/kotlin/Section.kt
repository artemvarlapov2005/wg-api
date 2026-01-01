package org.matkini

abstract class Section {
    abstract fun getProperties() : Map<String, String>
}

abstract class SectionBuilder {
    abstract fun putProperty(property : String, value : String)
}

const val INTERFACE_SECTION = "Interface"
const val PEER_SECTION = "Peer"