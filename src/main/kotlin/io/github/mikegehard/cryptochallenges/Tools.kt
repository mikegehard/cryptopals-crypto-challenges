package io.github.mikegehard.cryptochallenges

import java.util.*
import kotlin.experimental.xor

data class HexEncodedString(private val original: String) {
    fun toCharArray():CharArray = original.toCharArray()
}

object Tools {
    private val HEX = 16

    fun toBase64(hexValue: HexEncodedString): String =
            Base64.getEncoder().encodeToString(decode(hexValue))

    private fun decode(hexValue: HexEncodedString): ByteArray {
        val chars = hexValue.toCharArray()
        val even = chars.filterIndexed { i, _ -> i % 2 == 0 }
        val odd = chars.filterIndexed { i, _ -> i % 2 != 0 }

        return even.zip(odd)
                .map { it.toList().toCharArray().joinToString(separator = "") }
                .map { it.toByte(HEX) }
                .toByteArray()
    }

    fun XOR(a: HexEncodedString, b: HexEncodedString): String =
            decode(a).zip(decode(b))
                    .map { (a, b) -> a.xor(b) }
                    .joinToString(separator = "") { it.toString(HEX) }
}
