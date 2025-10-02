package Deutsche.FindAged.Tests;

import java.util.*;

/**
 * @author Sarav on 02 Oct 2025
 * @project govtech
 * @package Deutsche.FindAged.Tests
 * @class StudentGrade
 */

public class StudentGrade {
    // Student record using Java 16+ record feature
    public record Student(int id, String name, double cgpa)  {}

    public static void main(String[] args) {
        List<String> events = List.of(
                "ENTER John 3.75 50",
                "ENTER Mark 3.8 24",
                "ENTER Shafaet 3.7 35",
                "SERVED",
                "SERVED",
                "ENTER Samiha 3.85 36",
                "SERVED",
                "ENTER Ashley 3.9 42",
                "ENTER Maria 3.6 46",
                "ENTER Anik 3.95 49",
                "ENTER Dan 3.95 50",
                "SERVED"
        );

        Priorities p = new Priorities();
        List<String> students = p.getStudents(events);

        students.forEach(System.out::println);

    }

    public static class Priorities {
        public List<String> getStudents(List<String> events) {

            Comparator<Student> studentComparator = Comparator
                    .comparingDouble(Student::cgpa).reversed()
                    .thenComparing(Student::name)
                    .thenComparingInt(Student::id);

            PriorityQueue<Student> pq = new PriorityQueue<>(studentComparator);

            for (String event : events) {
                if (event.startsWith("ENTER")) {
                    String[] parts = event.split("\\s+");
                    String name = parts[1];
                    double cgpa = Double.parseDouble(parts[2]);
                    int id = Integer.parseInt(parts[3]);
                    pq.offer(new Student(id, name, cgpa));
                } else if (event.equals("SERVED")) {
                    pq.poll();
                }
            }

            // Using TreeSet is another method. but PriorityQueue would be efficient
            // because real sorting is happening in PriorityQueue
            // only at the time of iteration

            TreeSet<Student> set = new TreeSet<>(studentComparator);

            for (String event : events) {
                if (event.startsWith("ENTER")) {
                    String[] parts = event.split("\\s+");
                    String name = parts[1];
                    double cgpa = Double.parseDouble(parts[2]);
                    int id = Integer.parseInt(parts[3]);
                    set.add(new Student(id, name, cgpa));

                }
                else if (event.equals("SERVED")) {
                    set.pollFirst();
                }
            }

//            return pq.stream().map(Student::name).toList();
            return set.stream().map(Student::name).toList();

        }
    }
}
