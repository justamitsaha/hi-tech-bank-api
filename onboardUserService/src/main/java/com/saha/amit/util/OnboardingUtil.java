package com.saha.amit.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class OnboardingUtil {

    static int min = 100000;
    static int max = 999999;

    public static String generateApplicationID(){
        var date = new Date();
        String secs = String.valueOf((date.getTime())/1000);
        String applicationID = secs +"-"+(int)Math.floor(Math.random() * (max - min + 1) + min);
        return applicationID;
    }

    public static String generateRandomSixDigit(){
        return String.valueOf(((int)Math.floor(Math.random() * (max - min + 1) + min)));
    }
    public static String getFileNameForStoring(MultipartFile multipartFile, String applicationId){
        String random = String.valueOf((int)Math.floor(Math.random() * (max - min + 1) + min));

        var fileName = multipartFile.getOriginalFilename();
        String fileExtension = "";
        int lastIndexOf = fileName.lastIndexOf(".");
        if (!(lastIndexOf == -1)) {
            fileExtension = fileName.substring(lastIndexOf+1);
        }
        return applicationId+"-"+random+"."+fileExtension;
    }

    public static String saveFileLocally(String tmpdir, MultipartFile multipartFile, String applicationId) throws IOException {
        String random = String.valueOf((int)Math.floor(Math.random() * (max - min + 1) + min));

        var fileName = multipartFile.getOriginalFilename();
        String fileExtension = "";
        int lastIndexOf = fileName.lastIndexOf(".");
        if (!(lastIndexOf == -1)) {
            fileExtension = fileName.substring(lastIndexOf+1);
        }

        byte[] bytes = multipartFile.getBytes();
        Path path = Paths.get(tmpdir+ applicationId+"-"+random+"."+fileExtension);
        Files.write(path, bytes);
        System.out.println(path + multipartFile.getOriginalFilename());
        return path.toString();
    }
}
