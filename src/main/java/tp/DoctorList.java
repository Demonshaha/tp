package tp;

import tp.person.Doctor;
import tp.person.Person;

import javax.print.Doc;
import java.util.ArrayList;

public class DoctorList {
    public static String boundary = "____________________________________________________________"
                                            + System.lineSeparator();
    protected ArrayList<Doctor> doctors = new ArrayList<>();
    protected int size;

    public DoctorList() {
        size = 0;
    }

    public Person getDoctor(int index) {
        return doctors.get(index);
    }

    /**
     *
     * @param doctor the doctor will be added
     */
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        size++;
        System.out.println(boundary + "Noted. I've added this doctor:");
        System.out.println(doctors.get(size - 1));
        System.out.print("Now you have " + size
                                 + " doctors recorded in the system." + System.lineSeparator() + boundary);
    }

    /**
     * Prints the current list of doctors.
     */
    public void printDoctorList() {
        System.out.println(boundary + "Here are the doctors in this hospital:");
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + ". " + getDoctor(i));
        }
        System.out.print("You have " + size + " doctors recorded in the system."
                                 + System.lineSeparator() + boundary);
    }

    /**
     * Delete a doctor from doctor list.
     *
     * @param index Index of the doctor to be removed.
     */
    public void deleteDoctor(int index) {
        System.out.println(boundary + "Noted. I've removed this doctor:");
        System.out.println(doctors.get(index));
        System.out.print("Now you have " + (size - 1)
                                 + " doctors in the system." + System.lineSeparator() + boundary);
        doctors.remove(index - 1);
        size -= 1;
    }
}