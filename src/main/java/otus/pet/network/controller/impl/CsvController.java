package otus.pet.network.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import otus.pet.network.service.CsvService;

import java.io.IOException;

@RestController
@RequestMapping("/csv")
@RequiredArgsConstructor
public class CsvController {

    private final CsvService csvService;

    @GetMapping("/fulfillDb")
    ResponseEntity<HttpStatus> fulfillDb() throws IOException {
        csvService.insertDataFromCsv();
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
