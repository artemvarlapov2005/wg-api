package org.matkini

fun isStartSection(line : String) = line
    .withoutSpaces()
    .startsWith("[") && line.endsWith("]")

fun isPropertySection(line : String) = line
    .withoutSpaces()
    .contains("=")

fun isVersion(line : String) = line
    .withoutSpaces()
    .startsWith("#v")

fun getVersion(line : String) = line
    .withoutSpaces()
    .substring(2, line.length)
    .toLong()

fun getSectionName(line : String) = line
    .withoutSpaces()
    .substring(1, line.length - 1)

fun getProperty(line : String) : Pair<String, String> = line
    .split("=", limit = 2)
    .let { it[0].trim() to it[1].trim() }

fun String.withoutSpaces() = this.replace(" ", "")