package in.jp.trial.sb.sample.application;

import org.springframework.core.io.ClassPathResource;

public class FileUtils {

    public static boolean checkFileExistAtLeastOne(String filePath1, String filePath2) {
        boolean fileOneExist = new ClassPathResource(filePath1).exists();
        boolean fileTwoExist =  new ClassPathResource(filePath2).exists();
        if(!fileOneExist && !fileTwoExist) {
            throw new RuntimeException("Both default and profile properties file are not present.");
        }
        return true;
    }
}
