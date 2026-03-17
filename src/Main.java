import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Student> map = new HashMap<>();

        map.put("A1", new Student("Ali", 3.8, 20));
        map.put("A2", new Student("Bek", 3.5, 21));
        map.put("A3", new Student("Zara", 3.6, 19));
        map.put("A4", new Student("Dan", 3.5, 22));
        map.put("A5", new Student("Aika", 4.0, 20));
        map.put("A6", new Student("Timur", 3.2, 23));

        for (String id : map.keySet()) {
            System.out.println(id + " -> " + map.get(id));
        }

        System.out.println();
        System.out.println("Finding A3: " + map.get("A3"));

        map.remove("A6");

        Student s = map.get("A1");
        if (s != null) {
            s.setGpa(3.95);
        }

        List<Student> list = new ArrayList<>(map.values());

        System.out.println("\n--- Sorted by GPA ---");
        Collections.sort(list);
        for (Student st : list) {
            System.out.println(st);
        }

        System.out.println("\n--- Sorted by Name ---");
        list.sort(Comparator.comparing(Student::getName));
        for (Student st : list) {
            System.out.println(st);
        }

        System.out.println("\n=== Task 2 ===");
        List<Student> top = new ArrayList<>(map.values());
        top.sort((a, b) -> Double.compare(b.getGpa(), a.getGpa()));
        for (int i = 0; i < 3 && i < top.size(); i++) {
            System.out.println(top.get(i));
        }

        System.out.println("\n=== Task 3 ===");
        HashMap<Double, List<String>> gpaMap = new HashMap<>();
        for (Student st : map.values()) {
            double gpa = st.getGpa();
            gpaMap.putIfAbsent(gpa, new ArrayList<>());
            gpaMap.get(gpa).add(st.getName());
        }

        for (Double gpa : gpaMap.keySet()) {
            List<String> names = gpaMap.get(gpa);
            if (names.size() > 1) {
                System.out.println("GPA " + gpa + " -> " + String.join(", ", names));
            }
        }

        System.out.println("\n=== Task 4 ===");
        HashMap<Course, List<Student>> courses = new HashMap<>();
        Course math = new Course("Math");
        Course java = new Course("Java");

        courses.put(math, new ArrayList<>());
        courses.put(java, new ArrayList<>());

        courses.get(math).add(map.get("A1"));
        courses.get(math).add(map.get("A2"));
        courses.get(java).add(map.get("A3"));
        courses.get(java).add(map.get("A4"));
        courses.get(java).add(map.get("A5"));

        for (Course c : courses.keySet()) {
            System.out.println(c);
            for (Student st : courses.get(c)) {
                System.out.println("  " + st);
            }
        }

        System.out.println("\n=== Task 5 ===");
        List<Student> fin = new ArrayList<>(map.values());
        fin.sort((a, b) -> {
            int cmp = Double.compare(b.getGpa(), a.getGpa());
            if (cmp != 0) {
                return cmp;
            }
            return a.getName().compareTo(b.getName());
        });

        for (Student st : fin) {
            System.out.println(st);
        }
    }
}
