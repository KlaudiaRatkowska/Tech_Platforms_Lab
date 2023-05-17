package main.java;
import java.util.Comparator;

public class StudentsComparator implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
         return s1.getMajor().compareTo(s2.getMajor());
          }
}
