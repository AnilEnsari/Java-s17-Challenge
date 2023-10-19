package com.workintech.courserestapi.exceptions;

import com.workintech.courserestapi.model.Course;
import org.springframework.http.HttpStatus;

import java.util.List;

public class CourseValidation {
    public static void isCourseEmpty (List<Course> courses){
        if( courses.isEmpty()) {
            throw new CourseException("There is no saved course.", HttpStatus.NOT_FOUND);
        }
    }
    public static void isCreditValid (Course course){
        if (course.getCredit()<=0){
            throw new CourseException("The credit of the course must be higher than 0",HttpStatus.BAD_REQUEST);
        }
        else if (course.getCredit()>4){

            throw new CourseException("The credit of the course can not be higher than 4",HttpStatus.BAD_REQUEST);

        }


    }
    public static void isCourseAddedBefore (List <Course>courses,Course course){
       if(!courses.isEmpty()){
           for (Course currentCourse : courses ){

               if(currentCourse.getName().equals(course.getName())){

                   throw new CourseException("The course had already been added before: "+ course.getName(),
                           HttpStatus.BAD_REQUEST);

               }

           }



       }

    }

}
