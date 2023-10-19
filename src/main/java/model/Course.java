package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    //String name, int credit, Grade grade isimli 2 fielda sahip olmalı.
    String name ;
    int credit ;
    Grade grade;
}
