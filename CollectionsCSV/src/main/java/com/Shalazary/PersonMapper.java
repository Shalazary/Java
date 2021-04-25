package com.Shalazary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class PersonMapper extends Mapper<String[], Person> {
    private Map<String, Division> divisionsMap;

    public PersonMapper(Map<String, Division> divisionsMap) {
        this.divisionsMap = divisionsMap;
    }


    @Override
    public Person map(String[] mappingObj) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return new Person(Integer.parseInt(mappingObj[0]), mappingObj[1], mappingObj[2],
                          formatter.parse(mappingObj[3]), divisionsMap.get(mappingObj[4]),
                          Double.parseDouble(mappingObj[5]));
    }
}
