import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Households {


    public static void main(String[] args) throws Exception {

        String path = "data.txt";
        File file = new File(path);
        Scanner sc = new Scanner(file);

        Map<String, List<Person>> houses = new HashMap<>();

        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            Person person = createPerson(data);
            String address = person.getAddress().toUpperCase();

            if (houses.containsKey(address)) {
                houses.get(address).add(person);
            } else {
                List<Person> household = new ArrayList<>();
                household.add(person);
                houses.put(address, household);
            }
        }

        for (Map.Entry<String, List<Person>> entry: houses.entrySet()) {
            printFormatter(entry);
        }
        sc.close();
    }

    
    private static Person createPerson(String line) {
        String[] data = line.split(",");

        String fName = trimString(data[0]);
        String lName = trimString(data[1]);
        String address = trimString(data[2]) + " " + trimString(data[3]) + " " + trimString(data[4]);
        
        int age = Integer.parseInt(trimString(data[5]));

        Person person = new Person(fName, lName, address, age);
        return person;
    }

    private static String trimString(String data) {
        String res = data.substring(1, data.length() - 1);

        return res;
    }

    private static void printFormatter(Map.Entry<String, List<Person>> entry) {
        System.out.println(entry.getKey().hashCode() + " " + entry.getValue().size());
        List<Person> currentList = entry.getValue();
        Collections.sort(currentList);

        for (Person person: entry.getValue()) {
            if (person.getAge() >= 19) {
                System.out.println("    " + person.getInfo());
            }
        }
    }

}

/*
 * This class Person keeps track of an individuals data
 * Will 
 */
class Person implements Comparable<Person>{
    String fName;
    String lName;
    String address;
    int age;

    public Person(String fName, String lName, String address, int age) {
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.age = age;
    }

    @Override public int compareTo(Person person2) {
        final int compare = lName.compareTo(person2.getLastName());
        if (compare == 0) {
            return fName.compareTo(person2.getFirstName());
        } 
        return compare;
    }


    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public String getInfo() {
        return fName + " | " + lName + " | " + address + " | " + age;
    }

    public String getLastName() {
        return lName;
    }

    public String getFirstName() {
        return fName;
    }
}




