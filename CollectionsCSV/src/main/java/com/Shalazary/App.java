package com.Shalazary;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class App {

    public static void main(String[] args) throws Exception {

        CSVParser csvParser = new CSVParserBuilder().withSeparator(';').withIgnoreQuotations(true).build();

        InputStream inputDivisionsStream = ClassLoader.getSystemResourceAsStream("divisions.csv");
        if (inputDivisionsStream == null)
            throw new FileNotFoundException();
        CSVReader csvDivisionsReader = new CSVReaderBuilder(new InputStreamReader(inputDivisionsStream)).withCSVParser(csvParser).withSkipLines(1).build();

        DivisionMapper divisionMapper = new DivisionMapper();
        CustomCSVReader<Division> divisionReader = new CustomCSVReader<>(csvDivisionsReader, divisionMapper);

        List<Division> divisionList = divisionReader.readAllToList();

        Map<String, Division> divisionsMap = new HashMap<>();
        for(var division : divisionList)
            divisionsMap.put(division.getName(), division);

        csvDivisionsReader.close();
        inputDivisionsStream.close();

        InputStream inputPersonsStream = ClassLoader.getSystemResourceAsStream("foreign_names.csv");
        if (inputPersonsStream == null)
            throw new FileNotFoundException();
        CSVReader csvPersonsReader = new CSVReaderBuilder(new InputStreamReader(inputPersonsStream)).withCSVParser(csvParser).withSkipLines(1).build();

        PersonMapper personMapper = new PersonMapper(divisionsMap);
        CustomCSVReader<Person> personsReader = new CustomCSVReader<>(csvPersonsReader, personMapper);

        List<Person> personsList = personsReader.readAllToList();

        csvPersonsReader.close();
        inputPersonsStream.close();
    }
}
