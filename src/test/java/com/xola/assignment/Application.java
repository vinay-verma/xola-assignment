package com.xola.assignment;

import org.jline.reader.Parser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.jline.JLineShellAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.xola.assignment", exclude = {JLineShellAutoConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Parser parser() {
        return (var1, var2, var3) -> null;
    }
}
