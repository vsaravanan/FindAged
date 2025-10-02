package Deutsche.FindAged.Tests;

/**
 * @author Sarav on 02 Oct 2025
 * @project govtech
 * @package Deutsche.FindAged.Tests
 * @class RegexTest
 */
public class RegexTest {
    public static void main(String[] args) {
        String correctPattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

        String[] testIPs = {
                "000.12.12.034",    // Valid
                "121.234.12.12",    // Valid
                "23.45.12.56",      // Valid
                "00.12.123.123123.123", // Invalid (5 octets)
                "122.23",           // Invalid (only 2 octets)
                "Hello.IP",         // Invalid (contains letters)
                "666.666.23.23",    // Invalid (666 > 255)
                ".213.123.23.32",   // Invalid (starts with dot)
                "23.45.22.32.",     // Invalid (ends with dot)
                "I.Am.not.an.ip",   // Invalid (contains letters)
                "0.0.0.0",          // Valid
                "255.255.255.255",  // Valid
                "256.256.256.256",  // Invalid (256 > 255)
                "1.1.1.1",          // Valid
                "999.999.999.999"   // Invalid (all > 255)
        };

        System.out.println("Testing IP validation:");
        for (String ip : testIPs) {
            boolean matches = ip.matches(correctPattern);
            System.out.println(ip + " : " + matches);
        }
    }
}