import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Households {


    public static void main(String[] args) throws Exception{

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

        // Prints out data of households in a formatted manner. 
        for (Map.Entry<String, List<Person>> entry: houses.entrySet()) {
            printFormatter(entry);
        }
        sc.close();
    }

    /*
     * This method serves as a helper function given a line of data, will return a Person object. 
     * Will catch if age entered is not a number. 
     */
    private static Person createPerson(String line) {
        String[] data = formatData(line);
        String fName = data[0];
        String lName = data[1];
        String address = data[2] + " " + data[3] + " " + data[4];
        int age = -1;
        try {
            age = Integer.parseInt(data[5]);
        } catch (NumberFormatException e) {
            System.err.println("Invalid data. Age needs to be a number. \n" +  e);
        }
        
        Person person = new Person(fName, lName, address, age);
        return person;
    }

    /*
     * This method given a line of data as described in the documents, 
     * will return a String[] representation of the data.
     */
    private static String[] formatData(String data) {
        String[] res = new String[6];

        int resIdx = 0;
        int left = 1;
        int right = 1;
        
        // Finds data within quotation marks and adds to resulting String[]
        while (right < data.length()) {
            if (data.charAt(right) == '\"') {
                res[resIdx] = data.substring(left, right);
                left = right + 3; // Move the left pointer 3 further than right pointer to be at the start of the next data entry. 
                right = right + 3;// Moves the right pointer to the start of the next data entry. 
                resIdx++;
            } else {
                right++;
            }
        }
        return res;
    }

    /*
     * Helper function to output formatted data to console. 
     * Given an entry, will print out each household as defined in the Output Format Example. 
     */
    
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
 * This class keeps track of an individuals data.
 */
class Person implements Comparable<Person>{
    String fName;
    String lName;
    String address;
    int age;

    /* 
     * Constructor for Person class. 
     */
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

    /* 
     * Returns address as String
     */
    public String getAddress() {
        return address;
    }

    /* 
     * Returns age as Integer
     */
    public int getAge() {
        return age;
    }

    /* 
     * Returns information about person in a formatted String. 
     */
    public String getInfo() {
        return fName + " | " + lName + " | " + address + " | " + age;
    }

    /* 
     * Returns last name as String
     */
    public String getLastName() {
        return lName;
    }

    /* 
     * Returns first name as String
     */
    public String getFirstName() {
        return fName;
    }
}




