package org.example.task3;

public class Mark {
    private Student student;
    private String subject;
    private int mark;

    public Mark(Student student, String subject, int mark) {
        this.student = student;
        this.subject = subject;
        this.mark = mark;
    }

    public Student getStudent() {
        return student;
    }

    public String getSubject() {
        return subject;
    }

    public int getMark() {
        return mark;
    }
}
