package com.Shalazary;

public class DivisionMapper extends Mapper<String[], Division> {

    @Override
    public Division map(String[] mappingObj) {
        return new Division(Integer.parseInt(mappingObj[0]), mappingObj[1]);
    }
}
