package io.github.mikegehard.cryptochallenges

import kotlin.experimental.xor

fun ByteArray.Xor(other: ByteArray) = zip(other)
        .map { (a, b) -> a.xor(b) }
        .toByteArray()

fun ByteArray.singleCharXor(c: Char): ByteArray =
        map { it.xor(c.toByte()) }.toByteArray()

fun String.score(): Double {
    val scores = hashMapOf(
            'E' to 56.88, 'M' to 15.36,
            'A' to 43.31, 'H' to 15.31,
            'R' to 38.64, 'G' to 12.59,
            'I' to 38.45, 'B' to 10.56,
            'O' to 36.51, 'F' to 9.24,
            'T' to 35.43, 'Y' to 9.06,
            'N' to 33.92, 'W' to 6.57,
            'S' to 29.23, 'K' to 5.61,
            'L' to 27.98, 'V' to 5.13,
            'C' to 23.13, 'X' to 1.48,
            'U' to 18.51, 'Z' to 1.39,
            'D' to 17.25, 'J' to 1.00,
            'P' to 16.14, 'Q' to 1.00
    )

    return if (all { it.isLetter() || it == '\'' || it.isWhitespace() }) {
        toUpperCase()
                .map { scores.getOrDefault(it, 0.0) }
                .reduce(Double::plus)
    } else {
        0.0
    }
}

fun List<String>.findActualSentence() =
        map { Pair(it.score(), it) }
                .sortedByDescending { it.first }
                .first().second
