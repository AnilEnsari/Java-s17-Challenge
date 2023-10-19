package com.workintech.courserestapi.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Grade {
   private int coefficient;
    private String note;
}
