package ru.sibdigital.lexpro_migr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import ru.sibdigital.lexpro_migr.utils.FileUtils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public InputStream getFileBySubstrNameJar(String dir, String subName) throws IOException, URISyntaxException {
        List<Path> result = getPathsFromResourceJAR(dir);
        for (Path path : result) {
            System.out.println("Path : " + path);

            String filePathInJAR = path.toString();
            // Windows will returns /json/file1.json, cut the first /
            // the correct path should be json/file1.json
            if (filePathInJAR.startsWith("/")) {
                filePathInJAR = filePathInJAR.substring(1, filePathInJAR.length());
            }

            System.out.println("filePathInJAR : " + filePathInJAR);

            if(filePathInJAR.contains(subName)) {
                // read a file from resource folder
                ClassLoader classLoader = getClass().getClassLoader();
                InputStream inputStream = classLoader.getResourceAsStream(filePathInJAR);

                // the stream holding the file content
                if (inputStream == null) {
                    throw new IllegalArgumentException("file not found! " + filePathInJAR);
                } else {
                    return inputStream;
                }
            }
        }
        return null;
    }

    private List<Path> getPathsFromResourceJAR(String folder)
            throws URISyntaxException, IOException {

        List<Path> result;

        // get path of the current running JAR
        String jarPath = getClass().getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .toURI()
                .getPath();
        System.out.println("JAR Path :" + jarPath);

        // file walks JAR
        URI uri = URI.create("jar:file:" + jarPath);
        try (FileSystem fs = FileSystems.newFileSystem(uri, Collections.emptyMap())) {
            result = Files.walk(fs.getPath(folder))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
        }

        return result;
    }

    public String getStringFromFileJar(InputStream is) {
        try (is) {
            String query = new BufferedReader(
                    new InputStreamReader(is, StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.joining("\n"));
            return query;
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "";
        }
    }

    private InputStream getFileAsIOStream(final String fileName)
    {
        InputStream ioStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(fileName);

        if (ioStream == null) {
            throw new IllegalArgumentException(fileName + " is not found");
        }
        return ioStream;
    }
}
