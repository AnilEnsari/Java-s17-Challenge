package com.workintech.courserestapi.controller;

import com.workintech.courserestapi.model.Course;
import com.workintech.courserestapi.model.Gpas.CourseGpa;
import com.workintech.courserestapi.model.Gpas.HighCourseGpa;
import com.workintech.courserestapi.model.Gpas.LowCourseGpa;
import com.workintech.courserestapi.model.Gpas.MediumCourseGpa;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courses")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseController {

    private List<Course> courses;
    private CourseGpa courseGpa;
  private   Map<String, Integer> courseAndGpa;

    @PostConstruct
    public void init() {
        courses = new ArrayList<>();
        courseAndGpa = new HashMap<>();
    }

    @Autowired
    public void gpaController(CourseGpa courseGpa) {
        this.courseGpa = courseGpa;
    }

    @GetMapping("/")
    public List<Course> findAll() {
        return courses;
    }

    @GetMapping("/{name}")
    // TODO [Anil] eğer name içeren course yoksa hata ver
    public Course find(String name) {
        for (Course course : courses) {
            if (course.getName().equals(name)) {
                return course;
            }
        }
        return null;
    }

    @PostMapping("/")
    public Map<String, Integer> save(@RequestBody Course course) {
        // TODO [Anil] course.get 0 ve 4 arasında integer olmak zorunda

        int totalGpa = 0;
        if (course.getCredit() <= 2) {
            courseGpa = new LowCourseGpa();
        } else if (course.getCredit() == 3) {
            courseGpa = new MediumCourseGpa();
        } else if (course.getCredit() == 4) {
            courseGpa = new HighCourseGpa();
        }

        totalGpa = course.getGrade().getCoefficient() * course.getCredit() * courseGpa.getGpa();
       courses.add(course);
        courseAndGpa.put(course.getName(),totalGpa);
        return courseAndGpa ;
    }
}
