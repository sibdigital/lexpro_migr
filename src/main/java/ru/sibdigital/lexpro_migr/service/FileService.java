package ru.sibdigital.lexpro_migr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FileService {

    @Autowired
    ResourceLoader resourceLoader;

    public String getStringFromFile(String path){
        try (InputStream inputStream = resourceLoader.getResource(path).getInputStream();) {
            String query = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
            return query;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "";
        }
    }

    public List<String> listFilesUsingDirectoryStream(String dir) throws IOException {
        List<String> list = new ArrayList<>();
        File folder = resourceLoader.getResource(dir).getFile();
        if (folder.exists() && folder.isDirectory()) {
            list = Arrays.stream(folder.list())
                    .sorted(Comparator.comparing(this::getNumber))
                    .collect(Collectors.toList());
        }

        return list;
    }

    public String findFileBySubstrName(String dir, String subName) {
        String substrName = "";
        try {
            File folder = resourceLoader.getResource(dir).getFile();
            if (folder.exists() && folder.isDirectory()) {
                Optional<String> fileName = Arrays.stream(folder.list()).filter(name -> name.contains(subName)).findFirst();
                substrName = fileName.orElse("");
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return substrName;
    }

    private Integer getNumber(String name) {
        Integer idx = name.indexOf("_");
        String sub = name.substring(0, idx);
        return Integer.valueOf(sub);
    }
}
