package com.example.thirtyseven.lastlessons;

class Lesson {

    private String lessonName;
    private String teacher;
    private int time;
    private int audience;

    public Lesson() {
    }

    public Lesson(String lessonName, String teacher, int time, int audience) {
        this.lessonName = lessonName;
        this.teacher = teacher;
        this.time = time;
        this.audience = audience;
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
