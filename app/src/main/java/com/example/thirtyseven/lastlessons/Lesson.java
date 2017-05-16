package com.example.thirtyseven.lastlessons;

class Lesson {

    private String lessonName;
    private String teacher;
    private int time;
    private String audience;
    private boolean isNormal;
    private String oddOrEven;

    public Lesson(String lessonName, String teacher, int time, String audience, boolean isNormal, String oddOrEven) {
        this.lessonName = lessonName;
        this.teacher = teacher;
        this.time = time;
        this.audience = audience;
        this.isNormal = isNormal;
        this.oddOrEven = oddOrEven;
    }



    public boolean isNormal() {
        return isNormal;
    }

    public void setNormal(boolean normal) {
        isNormal = normal;
    }

    public String getOddOrEven() {
        return oddOrEven;
    }

    public void setOddOrEven(String oddOrEven) {
        this.oddOrEven = oddOrEven;
    }

    public Lesson() {
    }

    public Lesson(String lessonName, String teacher, int time, String audience, boolean isNormal) {
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

    public void setAudience(String audience) {
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

    public String getAudience() {
        return audience;
    }
}
