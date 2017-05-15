package com.example.thirtyseven.lastlessons;

class Lesson {

    private String lessonName;
    private String teacher;
    private int time;
    private int audience;

    public Lesson(String lessonName, String teacher, int time, int audience, boolean isNormal, char oddOrEven) {
        this.lessonName = lessonName;
        this.teacher = teacher;
        this.time = time;
        this.audience = audience;
        this.isNormal = isNormal;
        this.oddOrEven = oddOrEven;
    }

    private boolean isNormal;
    private char oddOrEven;

    public boolean isNormal() {
        return isNormal;
    }

    public void setNormal(boolean normal) {
        isNormal = normal;
    }

    public char getOddOrEven() {
        return oddOrEven;
    }

    public void setOddOrEven(char oddOrEven) {
        this.oddOrEven = oddOrEven;
    }

    public Lesson() {
    }

    public Lesson(String lessonName, String teacher, int time, int audience, boolean isNormal) {
        this.lessonName = lessonName;
        this.teacher = teacher;
        this.time = time;
        this.audience = audience;
        this.isNormal = isNormal;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setAudience(int audience) {
        this.audience = audience;
    }

    public String getLessonName() {
        return lessonName;
    }

    public String getTeacher() {
        return teacher;
    }

    public int getTime() {
        return time;
    }

    public int getAudience() {
        return audience;
    }
}
