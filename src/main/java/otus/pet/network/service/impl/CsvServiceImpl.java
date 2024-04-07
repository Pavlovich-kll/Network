package otus.pet.network.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import otus.pet.network.repository.UserRepository;
import otus.pet.network.service.CsvService;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class CsvServiceImpl implements CsvService {

    private final UserRepository userRepository;
    private final ResourceLoader resLoader;

    @Override
    public void insertDataFromCsv() throws IOException {
        Resource res = resLoader.getResource("classpath:csv/people1.csv");
        Path csvFilePath = res.getFile().toPath();

        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath.toString()))) {
            String[] nextLine;
            while((nextLine = reader.readNext()) != null) {
                String line = nextLine[0];
                String[] strings = line.split(";");
                String firstName = strings[0];
                String lastName = strings[1];
                String birthDate = strings[2];
                String city = strings[3];
                userRepository.fulfillTableFromCsv(firstName, lastName, birthDate, city);
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
