package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Grade {
    //int coefficient, String note bu iki değişkenide set eden bir adet constructor tanımlayınız
    int coefficient;
    String note;
}
