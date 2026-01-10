package org.matkini

import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

fun ConfigFile.toEncoded(key : String) : ConfigFile {
    val key = SecretKeySpec(key.toByteArray(Charsets.UTF_8), "AES")

    val privateKey = this.interfaceSection.privateKey

    val cipher = Cipher.getInstance("AES")
    cipher.init(Cipher.ENCRYPT_MODE, key)

    val changedPrivate = Base64.getEncoder().encodeToString(cipher.doFinal(privateKey.toByteArray()))

    return copy(
        interfaceSection = interfaceSection.copy(
            privateKey = changedPrivate
        ),
    )
}

fun String.privKeyEncoded(key : String) : String {
    val key = SecretKeySpec(key.toByteArray(Charsets.UTF_8), "AES")

    val cipher = Cipher.getInstance("AES")
    cipher.init(Cipher.ENCRYPT_MODE, key)

    val changedPrivate = Base64.getEncoder().encodeToString(cipher.doFinal(this.toByteArray()))

    return changedPrivate
}

fun String.privKeyDecoded(key : String) : String {
    val key = SecretKeySpec(key.toByteArray(Charsets.UTF_8), "AES")

    val cipher = Cipher.getInstance("AES")

    cipher.init(Cipher.DECRYPT_MODE, key)

    val decryptedPrivate = String(cipher.doFinal(Base64.getDecoder().decode(this)))

    return decryptedPrivate
}

fun ConfigFile.toDecoded(key : String) : ConfigFile {
    val key = SecretKeySpec(key.toByteArray(Charsets.UTF_8), "AES")

    val privateKey = this.interfaceSection.privateKey

    val cipher = Cipher.getInstance("AES")

    cipher.init(Cipher.DECRYPT_MODE, key)

    val decryptedPrivate = String(cipher.doFinal(Base64.getDecoder().decode(privateKey)))

    return copy(
        interfaceSection = interfaceSection.copy(
            privateKey = decryptedPrivate
        )
    )
}