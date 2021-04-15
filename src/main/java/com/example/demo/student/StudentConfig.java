package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student mariam = new Student(
                    1L,
                    "Mariam",
                    LocalDate.of(2000, Month.JANUARY, 5),
                    "mariam.jamal@gmail.com"
            );

            Student alex = new Student(
                    "Alex",
                    LocalDate.of(2004, Month.MARCH, 12),
                    "alex21@gmail.com"
            );
            repository.saveAll(List.of(mariam, alex)
            );
        };
    }
}

