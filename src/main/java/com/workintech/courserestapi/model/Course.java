package com.workintech.courserestapi.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
   private String name ;
   private int credit ;
  private   Grade grade;
}
