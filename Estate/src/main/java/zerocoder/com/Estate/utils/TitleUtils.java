package zerocoder.com.Estate.utils;

public class TitleUtils {
    public static String toTitleCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        String[] words = input.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word.toLowerCase()).append(" ");
        }
        String result = sb.toString().trim();
        return result.substring(0, 1).toUpperCase() + result.substring(1);
    }
}
