package ru.sibdigital.lexpro_migr;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.sibdigital.lexpro_migr.controller.ImportController;
import ru.sibdigital.lexpro_migr.protocol.CheckProtocol;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@SpringBootApplication
public class LexproMigrApplication implements CommandLineRunner {

	@Autowired
	ImportController importController;

	@Value("${spr.start_id:0}")
	Long sprStartId;
	@Value("${docums.start_id:0}")
	Long documsStartId;
	@Value("${files.start_id:0}")
	Long filesStartId;


	public static void main(String[] args) {
		SpringApplication.run(LexproMigrApplication.class, args);
	}

	@Override
	public void run(String... args)  {

		String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm_ss"));
		String checkProtocolFilename = "protocol_"+now+".log";
		CheckProtocol checkProtocol = CheckProtocol.getInstance();

		checkProtocol.addRow("spr.start_id: " + sprStartId);
		checkProtocol.addRow("docums.start_id: " + documsStartId);
		checkProtocol.addRow("files.start_id: " + filesStartId);

		try {
			for (String arg : args) {
				switch (arg) {
					case "spr": {
						checkProtocol.addRow("Импортирование справочников");
						importController.exportSprEntities(sprStartId);
						checkProtocol.addRow("Импортирование справочников завершено");
						break;
					}
					case "docums": {
						checkProtocol.addRow("Импортирование документов");
						importController.exportDocumsEntity(documsStartId);
						checkProtocol.addRow("Импортирование документов завершено");
						break;
					}
					case "files": {
						checkProtocol.addRow("Импортирование файлов");
						importController.exportFilesEntity(filesStartId);
						checkProtocol.addRow("Импортирование файлов завершено");
						break;
					}
					default: {
						System.out.println("Usage: java -jar lexpro_migr.jar spr docums files");
						log.info("Usage: java -jar lexpro_migr.jar spr docums files");
					}
				}
			}

			checkProtocol.saveToFile("logs" + File.separator + checkProtocolFilename);

		} catch (Exception e){
			log.error("app", e);
			checkProtocol.saveToFile("logs" + File.separator + checkProtocolFilename);
		}
	}
}
