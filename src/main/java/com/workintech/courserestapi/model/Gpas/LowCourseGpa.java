package com.workintech.courserestapi.model.Gpas;

import org.springframework.stereotype.Component;

@Component
public class LowCourseGpa implements CourseGpa{

    @Override
    public int getGpa() {
        return 3;
    }
}
