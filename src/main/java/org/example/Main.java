package org.example;

import org.example.task1.Bank;
import org.example.task1.Transferer;
import org.example.task2.Consumer;
import org.example.task2.Transfer;
import org.example.task2.Producer;
import org.example.task3.EJournal;
import org.example.task3.Mark;
import org.example.task3.Student;
import org.example.task3.Teacher;

import java.util.*;

public class Main {


    public static void main(String[] args) {
        testTask3();
    }

    private static void testTask1() {
        int NACCOUNTS = 100;
        int INITIAL_BALANCE = 10000;
        Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
        int i;
        for (i = 0; i < NACCOUNTS; i++) {
            Transferer t = new Transferer(b, i, INITIAL_BALANCE);
            t.setPriority(Thread.NORM_PRIORITY + i % 2);
            t.start();
        }
    }

    private static void testTask2() {
        Transfer transfer = new Transfer();
        (new Thread(new Producer(transfer, 100))).start();
        (new Thread(new Consumer(transfer))).start();
    }

    private static void testTask3() {
        Student[] students = {
                new Student("Andrii", "IT-01"),
                new Student("Igor", "IT-01"),
                new Student("Valeria", "IT-01"),
                new Student("Iryna", "IT-01"),
                new Student("Vlad", "IT-01"),
                new Student("Jimmy", "IT-01"),
                new Student("Why", "IT-02"),
                new Student("Danylo", "IT-02"),
                new Student("Andrii", "IT-02"),
                new Student("Olena", "IT-02"),
                new Student("Dasha", "IT-02"),
                new Student("Kateryna", "IT-02"),
                new Student("Bob", "IT-03"),
                new Student("Foo", "IT-03"),
                new Student("Maryna", "IT-03"),
                new Student("Carl", "IT-03"),
                new Student("Lee", "IT-03"),
                new Student("JJ", "IT-03"),
        };

        Mark[] marks1 = {
                new Mark(students[2], "Math", 69),
                new Mark(students[7], "AI", 67),
                new Mark(students[6], "Math", 60),
                new Mark(students[9], "PE", 67),
                new Mark(students[4], "Math", 66),
                new Mark(students[0], "Math", 64),
        };
        Mark[] marks2 = {
                new Mark(students[2], "Math", 79),
                new Mark(students[4], "OP", 77),
                new Mark(students[6], "Math", 70),
                new Mark(students[7], "AI", 77),
                new Mark(students[9], "PE", 76),
                new Mark(students[0], "Math", 74),
        };
        Mark[] marks3 = {
                new Mark(students[12], "Math", 89),
                new Mark(students[13], "Math", 87),
                new Mark(students[14], "Math", 80),
                new Mark(students[15], "Math", 87),
                new Mark(students[16], "Math", 86),
                new Mark(students[17], "Math", 84),
        };
        Mark[] marks4 = {
                new Mark(students[1], "Math", 99),
                new Mark(students[3], "Math", 97),
                new Mark(students[5], "Math", 90),
                new Mark(students[10], "Math", 97),
                new Mark(students[8], "Math", 96),
                new Mark(students[11], "Math", 94),
        };

        Map<Student, Student> studentMap = new HashMap<>();
        String[] subjects = {
                "Math",
                "AI",
                "PE",
                "OP"
        };
        Arrays.stream(students).forEach(student -> studentMap.put(student, new Student(student.getName(), student.getGroup(), subjects)));
        EJournal journal = new EJournal(studentMap);

        Runnable[] teachers = {
                new Teacher(journal, Arrays.stream(marks1).toList()),
                new Teacher(journal, Arrays.stream(marks2).toList()),
                new Teacher(journal, Arrays.stream(marks3).toList()),
                new Teacher(journal, Arrays.stream(marks4).toList())
        };
        List<Thread> started = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Arrays.stream(teachers).map(Thread::new).forEach(thread -> {
                thread.start();
                started.add(thread);
            });
        }
        for (Thread teacher : started) {
            try {
                teacher.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        int numOfMarks = 0;
        for (Student student : journal.getStudents()) {
            numOfMarks += student.getMarks(subjects[0]).size();
            System.out.println(student);
        }
        System.out.println("Marks count: ");
        System.out.println(numOfMarks);
    }

}