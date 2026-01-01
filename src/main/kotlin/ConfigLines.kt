package org.matkini

fun isStartSection(line : String) = line.startsWith("[") && line.endsWith("]")

fun isPropertySection(line : String) = line.contains("=")

fun getSectionName(line : String) = line.substring(1, line.length - 1)

fun getProperty(line : String) : Pair<String, String> = line.split("=", limit = 2).let { it[0] to it[1] }