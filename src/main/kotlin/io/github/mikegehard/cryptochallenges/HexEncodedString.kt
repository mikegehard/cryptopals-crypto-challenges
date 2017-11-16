package io.github.mikegehard.cryptochallenges

import org.apache.commons.codec.binary.Hex
import java.math.BigInteger
import java.nio.charset.Charset
import java.util.*


data class HexEncodedString(private val original: String) {
    fun toPlaintext(): String = toByteArray().toString(Charset.defaultCharset())
    fun toByteArray(): ByteArray = BigInteger(original, RADIX).toByteArray()
    fun toBase64(): String = Base64.getEncoder().encodeToString(toByteArray())

    companion object {
        private val RADIX = 16
        fun from(bytes: ByteArray): HexEncodedString =
                HexEncodedString(Hex.encodeHexString(bytes))
    }

    fun singleCharDecode(): String {

        val possiblePlainTexts = (0..255).map {
            it.toChar()
        }.map {
            it to toByteArray().singleCharXor(it)
        }.map { pair: Pair<Char, ByteArray> ->
            HexEncodedString.from(pair.second).toPlaintext()
        }

        return possiblePlainTexts
                .map {
                    Pair(it.score(), it)
                }
                .sortedByDescending { it.first }
                .first()
                .second
    }
}
