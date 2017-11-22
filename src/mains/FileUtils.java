package mains;

public class FileUtils {
    public static String getFileExtension(String name) {
        int extIndex = name.lastIndexOf(".");

        if (extIndex == -1) {
            return "";
        } else {
            return name.substring(extIndex + 1);
        }
    }
}
