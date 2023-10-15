package com.example.titemag;

public class PreviousData {
    String chief;
    String image;
    String pdf;
    String theme;
    public PreviousData() {

    }

    public PreviousData(String chief, String image, String pdf, String theme) {
        this.chief = chief;
        this.image = image;
        this.pdf = pdf;
        this.theme = theme;
    }

    public String getChief() {
        return chief;
    }

    public void setChief(String chief) {
        this.chief = chief;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
