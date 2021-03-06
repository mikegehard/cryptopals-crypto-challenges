import io.github.mikegehard.cryptochallenges.*
import org.junit.Assert.assertEquals
import org.junit.Test
import java.nio.file.FileSystems
import java.nio.file.Files

class Set1 {

    @Test
    fun canConvertFromHexToBase64() {
        val base64Value = "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t"

        val hexValue = HexEncodedString("49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d")
        assertEquals(
                base64Value,
                hexValue.toBase64()
        )
    }

    @Test
    fun fixedXOR() {
        val originalValue = HexEncodedString("1c0111001f010100061a024b53535009181c")
        val foobar = HexEncodedString("686974207468652062756c6c277320657965")
        val expectedValue = HexEncodedString("746865206b696420646f6e277420706c6179")

        assertEquals(expectedValue,
                HexEncodedString.from(
                        originalValue
                                .toByteArray()
                                .Xor(foobar.toByteArray())
                )
        )
    }

    @Test
    fun testHexToPlaintext() {
        val input = HexEncodedString("746865206b696420646f6e277420706c6179")
        val expected = "the kid don't play"

        assertEquals(expected, input.toPlaintext())
    }

    @Test
    fun canScoreAStringWithAllLetters() {
        val s = "hi"

        assertEquals(53.76, s.score(), 0.001)
    }

    @Test
    fun canScoreAStringWithMixedChars() {
        val s = "h+"

        assertEquals(0.0, s.score(), 0.001)
    }

    @Test
    fun properlyScoresWithAnApostrophy() {
        val s = "MC's"

        assertEquals(67.72, s.score(), 0.001)
    }

    @Test
    fun properlyScoresWithSpace() {
        val s = "MC's with"

        assertEquals(163.48, s.score(), 0.001)
    }

    @Test
    fun testFindDecodedString() {
        val encodedString = HexEncodedString("1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736")
        val expected = "Cooking MC's like a pound of bacon"

        assertEquals(expected, encodedString.singleCharDecode())
    }

    @Test
    fun detectProperEncodedString() {
        val lines = Files.readAllLines(
                FileSystems.getDefault().getPath(".", "files", "single-char-or.txt")
        )

        val expected = "Now that the party is jumping"
        val actual = lines
                .map { HexEncodedString(it).singleCharDecode().trim() }
                .findActualSentence()

        assertEquals(expected, actual)
    }

    @Test
    fun canEncodeWithRepeatingKeyXor() {
        val input = "Burning 'em, if you ain't quick and nimble\nI go crazy when I hear a cymbal"
        val expected = HexEncodedString("0b3637272a2b2e63622c2e69692a23693a2a3c6324202d623d63343c2a26226324272765272a282b2f20430a652e2c652a3124333a653e2b2027630c692b20283165286326302e27282f")

        assertEquals(expected, input.encodeWithRepeatingKeyXor("ICE"))
    }

    @Test
    fun canCalculateHammingDistance() {
        val a = "this is a test"
        val b = "wokka wokka!!!"

        assertEquals(37, a.hammingDistanceFrom(b))
    }
}


