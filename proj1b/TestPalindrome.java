import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome("appa"));
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("bb"));
        assertFalse(palindrome.isPalindrome("bc"));
        assertTrue(palindrome.isPalindrome("  "));
        assertTrue(palindrome.isPalindrome(" b "));
        assertTrue(palindrome.isPalindrome(" bb "));
        assertTrue(palindrome.isPalindrome("b"));
        assertFalse(palindrome.isPalindrome("b "));
        assertFalse(palindrome.isPalindrome("bb "));
        assertFalse(palindrome.isPalindrome("Aa"));
        assertFalse(palindrome.isPalindrome("!a"));
        assertTrue(palindrome.isPalindrome("=="));
        assertTrue(palindrome.isPalindrome("!aba!"));

        OffByOne obo = new OffByOne();

        assertTrue(palindrome.isPalindrome("acdb", obo));
        assertTrue(palindrome.isPalindrome("acb", obo));
        assertFalse(palindrome.isPalindrome("appa", obo));
        assertTrue(palindrome.isPalindrome("ab", obo));
        assertTrue(palindrome.isPalindrome("ba", obo));
        assertFalse(palindrome.isPalindrome("aB", obo));
        assertFalse(palindrome.isPalindrome("Ba", obo));
        assertTrue(palindrome.isPalindrome("&%", obo));
        assertTrue(palindrome.isPalindrome("acegikmoqsuwyzxvtrpnljhfdb", obo));
        assertFalse(palindrome.isPalindrome("&=", obo));
        assertFalse(palindrome.isPalindrome("bb", obo));
        assertTrue(palindrome.isPalindrome("b", obo));
    }
}
