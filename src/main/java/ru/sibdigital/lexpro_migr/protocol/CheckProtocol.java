package ru.sibdigital.lexpro_migr.protocol;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Data
public final class CheckProtocol {

    private static CheckProtocol instance;
    private StringBuilder protocol;

    private CheckProtocol(){
        protocol = new StringBuilder();
    };

    public static CheckProtocol getInstance(){
        if(instance == null){
            instance = new CheckProtocol();
        }
        synchronized (CheckProtocol.class){
            if(instance == null){
                instance = new CheckProtocol();
            }
            return instance;
        }
    }

    public void addRow(String row){
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.protocol.append(String.format("[%s]: %s\n", now, row));
        log.warn(row);
    }

    public void addRows(StringBuilder rows){
        this.protocol.append(rows);
    }

    public void saveToFile(String filename) {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(this.protocol.toString());
            printWriter.close();
        } catch (IOException e){
            log.error("saveToDb", e);
        }
    }
}
