package com.example.titemag;

public class ImageData {
    private String enrollment;
    private String image;
    private String name;
    private String student_name;


    public ImageData() {
    }

    public ImageData(String enrollment, String image, String name, String student_name) {
        this.enrollment = enrollment;
        this.image = image;
        this.name = name;
        this.student_name = student_name;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }
}
