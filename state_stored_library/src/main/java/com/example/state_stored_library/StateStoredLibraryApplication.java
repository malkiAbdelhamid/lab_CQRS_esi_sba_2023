package com.example.state_stored_library;

import com.example.coreapi.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AxonConfig.class})
public class StateStoredLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(StateStoredLibraryApplication.class, args);
    }

}
