package formatter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValueFormatter {
    public static final String SEPARATOR = ": ";
    public static final int VALUE_POSITION = 1;
    public static final String LINE_SEPARATOR = " ";
    public static final int FIRST_VALUE_POSITION = 0;
    public static final String LOCAL_SSD_REGEX = "\\d+x\\d+";

    public static String getValueFromString(String inputString) {
        return inputString.split(SEPARATOR)[VALUE_POSITION];
    }

    public static String getFirstValueInLine(String inputString) {
        return inputString.split(LINE_SEPARATOR)[FIRST_VALUE_POSITION];
    }

    public static String getLocalSSDValue(String inputString) {
        String localSsd = "";

        Pattern pattern = Pattern.compile(LOCAL_SSD_REGEX);
        Matcher matcher = pattern.matcher(inputString);

        if (matcher.find()) {
            localSsd = matcher.group();
        }

        return localSsd;
    }
}
