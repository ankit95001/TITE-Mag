package com.example.titemag;

public class DataClass {
    private final String Painting_name;
    private final String Student_name;
    private final String Enrollment;
    private final String image;
    public DataClass(String Painting_name, String Student_name, String Enrollment, String image) {
        this.Painting_name = Painting_name;
        this.Student_name = Student_name;
        this.Enrollment = Enrollment;
        this.image = image;
    }

    public String getName() {
        return Painting_name;
    }

    public String getStudent_name() {
        return Student_name;
    }

    public String getEnrollment() {
        return Enrollment;
    }

    public String getImage() {
        return image;
    }
}
