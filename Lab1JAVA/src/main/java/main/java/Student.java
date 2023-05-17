package main.java;

import java.util.Objects;
import java.util.Set;
import java.util.Comparator;

public class Student implements Comparable<Student> {
    private String name;
    private String surname;
    private int indexNb;
    private String major;
    private Set<Student> candidates;

    public Student(String name, String surname, String major, int indexNb, Set<Student> set){
        this.name = name;
        this.surname = surname;
        this.major = major;
        this.indexNb = indexNb;
        this.candidates = set;
    }

    public Student(String name, String surname, String major, int indexNb) {
        this.name = name;
        this.surname = surname;
        this.major = major;
        this.indexNb = indexNb;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (this.indexNb != other.indexNb) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.major, other.major)) {
            return false;
        }
        if (!Objects.equals(this.candidates, other.candidates)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash = 17 * hash + name.hashCode();
        hash = 17 * hash + surname.hashCode();
        hash = 17 * hash + indexNb;
        hash = 17 * hash + major.hashCode();
        return hash;
    }

    @Override
    public int compareTo(Student other) {
        if(name.compareTo(other.name)!=0)
            return name.compareTo(other.name);
        if(surname.compareTo(other.surname)!=0)
            return surname.compareTo(other.surname);
        return 0;
    }

    public String toString() {
        return name + " " + surname + " " + major + " " + indexNb;
    }

    public String getMajor() {
        return major;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getIndexNb() {
        return indexNb;
    }

    public Set<Student> getCandidates() {
        return candidates;
    }
}
