package main.java;

import java.util.*;

public class Sorts {
    public class noSort implements SortMethod {
        @Override
        public Set<Student> generateSet()
        {
            return new HashSet<>();
        }

        @Override
        public Map<Student, Integer> generateMap()
        {
            return new HashMap<>();
        }
    }

    public class defaultSort implements SortMethod {
        @Override
        public Set<Student> generateSet()
        {
            return new TreeSet<>();
        }

        @Override
        public Map<Student, Integer> generateMap()
        {
            return new TreeMap<>();
        }
    }

    public class alternativeSort implements SortMethod {

        @Override
        public TreeSet generateSet()
        {
            StudentsComparator comparator = new StudentsComparator();
            return new TreeSet<>(comparator);
        }

        @Override
        public Map<Student, Integer> generateMap()
        {
            StudentsComparator comparator = new StudentsComparator();
            return new TreeMap<>(comparator);
        }

    }

}
