import io.github.mikegehard.cryptochallenges.Convert
import org.junit.Assert.assertEquals
import org.junit.Test

class Set1 {

    @Test
    fun canConvertFromHexToBase64() {
        val base64Value = "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t"

        val hexValue = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d"
        assertEquals(
                base64Value,
                Convert.toBase64(hexValue))
    }

    @Test
    fun fixedXOR(){
        val originalValue = "1c0111001f010100061a024b53535009181c"
        val foobar = "686974207468652062756c6c277320657965"
        val expectedValue = "746865206b696420646f6e277420706c6179"

        assertEquals(expectedValue, Convert.XOR(originalValue, foobar))
    }
}
