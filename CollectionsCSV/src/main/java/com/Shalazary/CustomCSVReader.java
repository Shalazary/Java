package com.Shalazary;

import com.opencsv.CSVReader;

import java.util.ArrayList;
import java.util.List;

public class CustomCSVReader <T>  {
    private final CSVReader reader;
    private final Mapper<String[], T> mapper;

    public CustomCSVReader(CSVReader reader, Mapper<String[], T> mapper){
        this.reader = reader;
        this.mapper = mapper;
    }

    public T readNext() throws Exception {
        String[] line = reader.readNext();
        if(line == null)
            return null;
        return mapper.map(line);
    }

    public List<T> readAllToList() throws Exception {
        List<T> res = new ArrayList<T>();
        T line;
        while ((line = readNext()) != null)
            res.add(line);
        return res;
    }
}
