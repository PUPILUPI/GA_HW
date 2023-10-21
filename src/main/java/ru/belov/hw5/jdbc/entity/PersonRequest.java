package ru.belov.hw5.jdbc.entity;

import java.time.LocalDate;

public record PersonRequest(int page,
                            int limit,
                            String secondName,
                            String firstName,
                            String lastName,
                            LocalDate birthDate,
                            Double salary) {

    public int countNulls() {
        int count = 0;
        if(secondName!=null) count++;
        if(firstName!=null) count++;
        if(lastName!=null) count++;
        if(birthDate!=null) count++;
        if(salary!=null) count++;
        return count;
    }
}
