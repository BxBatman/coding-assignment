package com.example.codingassignment;

import com.example.codingassignment.exception.CodingAssignmentException;
import com.example.codingassignment.processor.FileProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CodingAssignmentApplication implements CommandLineRunner {
	private final FileProcessor fileProcessor;

	public CodingAssignmentApplication(FileProcessor fileProcessor) {
		this.fileProcessor = fileProcessor;
	}

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(CodingAssignmentApplication.class);
		springApplication.setWebApplicationType(WebApplicationType.NONE);
		springApplication.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (args.length <= 0) {
			throw new CodingAssignmentException("File path is not provided");
		}
		String filePath = args[0];
		if (filePath.isEmpty()) {
			throw new CodingAssignmentException("File path empty");
		}
		fileProcessor.processFile(filePath);
	}
}
