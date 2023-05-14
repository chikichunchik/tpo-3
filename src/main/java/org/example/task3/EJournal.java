package org.example.task3;

import java.util.Collection;
import java.util.Map;

public class EJournal {
    private final Map<Student, Student> students;

    public EJournal(Map<Student, Student> students) {
        this.students = students;
    }

    public boolean setMarkForStudent(Mark mark) {
        return students.get(mark.getStudent()).addMark(mark.getMark(), mark.getSubject());
    }

    public Collection<Student> getStudents() {
        return students.values();
    }
}
