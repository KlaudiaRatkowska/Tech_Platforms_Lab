package main.java;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        main.java.Sorts method = new main.java.Sorts();
        main.java.SortMethod sortMethod;
        System.out.println("Choose sorting: ");
        System.out.println("1 - no sort");
        System.out.println("2 - default sort according to name and surname");
        System.out.println("3 - alternative sort according to major");
        Scanner obj = new Scanner(System.in);
        Integer sort = obj.nextInt();

        if(sort == 1)
            sortMethod = method.new noSort();
        else if(sort == 2)
            sortMethod = method.new defaultSort();
        else
            sortMethod = method.new alternativeSort();


        Set<main.java.Student> student = sortMethod.generateSet();
        Map<main.java.Student, Integer> studentMap = sortMethod.generateMap();
        test(student, sortMethod);
        writeStudents(student, "-");
        ArrayList<Integer> al = new ArrayList<Integer>();
        System.out.println(" ");
        MapStudents(student, studentMap, al);

    }


    private static void test(Set<Student> student, SortMethod sortMethod) {
        Student s1, s2, s3;
        s1 = new Student("Tom","Wash", "IT", 1234, sortMethod.generateSet());
        s2 = new Student("Alice", "Tan", "Law", 2345, sortMethod.generateSet());
        s3 = new Student("John", "Black", "Art", 1345, sortMethod.generateSet());

        s1.getCandidates().add(new Student("Eve", "MaY", "IT", 9876));
        s1.getCandidates().add(new Student("Michael", "Scott", "Business", 9765));
        s1.getCandidates().add(new Student("Jane", "Doe", "Law", 9865));
        s2.getCandidates().add(s1);
        student.add(s1);
        student.add(s2);

        s3.getCandidates().add(new Student("Jake", "Cull", "Business", 9432));
        student.add(new Student("Jenny", "And", "Art", 7654));
        student.add(s3);

        s3 = new Student("Carol", "Glas", "Chemistry", 3412, sortMethod.generateSet());
        s3.getCandidates().add(new Student("Helen", "Jou", "Chemistry", 1524));
        student.add(s3);

    }

    private static void writeStudents(Set<Student> students, String dash) {
        for(Student e : students)
        {
            System.out.print(dash);
            System.out.println(e);
            if(e.getCandidates()!=null)
                writeStudents(e.getCandidates(),dash + "-");
        }
    }

    private static int MapStudents(Set<Student> student, Map<Student, Integer> studentMap, ArrayList<Integer> al) {
        int nb = 0;
        for(Student e : student)
        {
            int nbOfCand = 0;
            if(e.getCandidates()!=null)
            {
                nbOfCand += MapStudents(e.getCandidates(), studentMap, al) + e.getCandidates().size();;
            }
            studentMap.put(e, nbOfCand);
            if(!al.contains(e.getIndexNb()))
                System.out.println(e + " number of candidates: " + studentMap.get(e));
            al.add(e.getIndexNb());
            nb += nbOfCand;
        }
        return nb;
    }
}
