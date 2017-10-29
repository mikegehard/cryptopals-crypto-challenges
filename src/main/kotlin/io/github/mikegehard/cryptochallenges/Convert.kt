package io.github.mikegehard.cryptochallenges

import java.util.Base64


object Convert {
    fun toBase64(hexValue: String): String {
        val chars = hexValue.toCharArray()
        val even = chars.filterIndexed({ i, _ -> i % 2 == 0 })
        val odd = chars.filterIndexed({ i, _ -> i % 2 != 0 })

        val bytes = even.zip(odd)
                .map({ it.toList().toCharArray().joinToString(separator = "") })
                .map({ it.toByte(16) })
                .toByteArray()

        return Base64.getEncoder().encodeToString(bytes)
    }
}
