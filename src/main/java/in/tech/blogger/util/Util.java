package in.tech.blogger.util;

import java.text.Normalizer;

public class Util {
    public static String toFriendlyURL(String string) {
        if (string != null) {
            return Normalizer.normalize(string.toLowerCase(), Normalizer.Form.NFD)
                    .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                    .replaceAll("[^\\p{Alnum}]+", "-");
        }
        return null;
    }
}

