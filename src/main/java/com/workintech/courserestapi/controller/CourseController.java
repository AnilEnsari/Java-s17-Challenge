package com.workintech.courserestapi.controller;

import com.workintech.courserestapi.model.Course;
import com.workintech.courserestapi.model.CourseResponse;
import com.workintech.courserestapi.model.Gpas.CourseGpa;
import com.workintech.courserestapi.model.Gpas.HighCourseGpa;
import com.workintech.courserestapi.model.Gpas.LowCourseGpa;
import com.workintech.courserestapi.model.Gpas.MediumCourseGpa;
import com.workintech.courserestapi.exceptions.CourseException;
import com.workintech.courserestapi.exceptions.CourseValidation;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/courses")

public class CourseController {

    private List<Course> courses;
    private CourseGpa lowCourseGpa;
    private CourseGpa mediumCourseGpa;
    private CourseGpa highCourseGpa;


    @PostConstruct
    public void init() {
        courses = new ArrayList<>();
    }

    @Autowired
    public void gpaController(@Qualifier("lowCourseGpa") CourseGpa lowCourseGpa,
                              @Qualifier("mediumCourseGpa") CourseGpa mediumCourseGpa,
                              @Qualifier("highCourseGpa") CourseGpa highCourseGpa) {
        this.lowCourseGpa = lowCourseGpa;
        this.mediumCourseGpa = mediumCourseGpa;
        this.highCourseGpa = highCourseGpa;
    }

    @GetMapping("/")
    public List<Course> findAll() {
        return courses;
    }

    @GetMapping("/{name}")
    public Course find(@PathVariable String name) {
        CourseValidation.isCourseEmpty(courses);

        for (Course course : courses) {
            if (course.getName().equalsIgnoreCase(name)) {
                return course;
            }
        }
        throw new CourseException("There is no course with the given name: " + name, HttpStatus.NOT_FOUND);

    }

    @PostMapping("/")
    public CourseResponse save(@RequestBody Course course) {
        CourseValidation.isCreditValid(course);
        CourseValidation.isCourseAddedBefore(courses, course);
        double totalGpa = 0;
        if (course.getCredit() <= 2) {
            totalGpa = course.getGrade().getCoefficient() * course.getCredit() * ((LowCourseGpa) lowCourseGpa).getGpa();
            courses.add(course);

        } else if (course.getCredit() == 3) {
            totalGpa = course.getGrade().getCoefficient() * course.getCredit() * ((MediumCourseGpa) mediumCourseGpa).getGpa();
            courses.add(course);
        } else if (course.getCredit() == 4) {
            totalGpa = course.getGrade().getCoefficient() * course.getCredit() * ((HighCourseGpa) highCourseGpa).getGpa();
            courses.add(course);
        }

        courses.add(course);
        return new CourseResponse(course, totalGpa);
    }

    @PutMapping("/{name}")
    public CourseResponse update(@PathVariable String name, @RequestBody Course course) {
        CourseValidation.isCreditValid(course);

        double totalGpa = 0;
        if (course.getCredit() <= 2) {
            totalGpa = course.getGrade().getCoefficient() * course.getCredit() * ((LowCourseGpa) lowCourseGpa).getGpa();

        } else if (course.getCredit() == 3) {
            totalGpa = course.getGrade().getCoefficient() * course.getCredit() * ((MediumCourseGpa) mediumCourseGpa).getGpa();

        } else if (course.getCredit() == 4) {
            totalGpa = course.getGrade().getCoefficient() * course.getCredit() * ((HighCourseGpa) highCourseGpa).getGpa();
        }
        for (Course searchedCourse : courses) {
            if (searchedCourse.getName().equals(name)) {
                searchedCourse.setCredit(course.getCredit());
                searchedCourse.setGrade(course.getGrade());
                return new CourseResponse(course, totalGpa);
            }

        }

        throw new CourseException("There is no course with the given name: " + name, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{name}")
    public Course delete(@PathVariable String name) {

        CourseValidation.isCourseEmpty(courses);


        for (Course searchedCourse : courses) {
            if (searchedCourse.getName().equals(name)) {
                Course deletedCourse = new Course(searchedCourse.getName(), searchedCourse.getCredit(), searchedCourse.getGrade());

                courses.remove(searchedCourse);
                return deletedCourse;
            }

        }

        throw new CourseException("The course you want to is not in the list " + name, HttpStatus.NOT_FOUND);
    }
}
