package tp;


import tp.command.AddAppointmentCommand;
import tp.command.AddDoctorCommand;
import tp.command.AddNurseCommand;
import tp.command.AddPatientCommand;
import tp.command.AddPatientDescriptionCommand;
import tp.command.AddWardCommand;
import tp.command.Command;
import tp.command.DeleteAppointmentCommand;
import tp.command.DeleteDoctorCommand;
import tp.command.DeletePatientCommand;
import tp.command.EditAppointmentCommand;
import tp.command.EditDoctorCommand;
import tp.command.EditPatientCommand;
import tp.command.GetAppointmentsOfDoctorCommand;
import tp.command.HelpCommand;
import tp.command.ListAppointmentListCommand;
import tp.command.ListDoctorListCommand;
import tp.command.ListPatientListCommand;
import tp.command.SearchAppointmentCommand;
import tp.command.SearchDoctorCommand;
import tp.command.SearchPatientCommand;
import tp.command.SortAppointmentByTimeCommand;

import java.util.Scanner;

public class Parser {

    public Parser() {

    }

    public static String getCommand() {
        String command;
        Scanner in = new Scanner(System.in);
        command = in.nextLine();
        return command;
    }

    public Command parseAddDoctor(String fullCommand) throws IHospitalException {
        String id;
        String dummy = fullCommand.trim();
        if (dummy.indexOf("/id") > dummy.indexOf("/n") || dummy.indexOf("/id") > dummy.indexOf("/ph")
                    || dummy.indexOf("/id") > dummy.indexOf("/e") || dummy.indexOf("/n") > dummy.indexOf("/ph")
                    || dummy.indexOf("/n") > dummy.indexOf("/e") || dummy.indexOf("/ph") > dummy.indexOf("/e")) {
            throw new IHospitalException("The format of input is incorrect, "
                                                 + "you may type `help` to view the command format.");
        }

        int idIndex = dummy.indexOf("/id") + 4;
        int nameIndex = dummy.indexOf("/n");
        id = dummy.substring(idIndex, nameIndex).trim();
        int phoneNumberIndex = dummy.indexOf("/ph");
        String name = dummy.substring(nameIndex + 3, phoneNumberIndex).trim();
        int emailIndex = dummy.indexOf("/e");
        String phoneNumber = dummy.substring(phoneNumberIndex + 4, emailIndex).trim();
        int wardNumberIndex = dummy.indexOf("/w");
        String email = dummy.substring(emailIndex + 3, wardNumberIndex).trim();
        String wardNumber = dummy.substring(wardNumberIndex + 3).trim();
        return new AddDoctorCommand(id, name, phoneNumber, email, wardNumber, false);
    }

    public Command parseGetAppointment(String fullCommand) throws IHospitalException {
        //get appointment /d 123456
        String dummy = fullCommand.trim();
        String id =  dummy.substring(dummy.indexOf("/d") + 3).trim();
        return new GetAppointmentsOfDoctorCommand(id);
    }

    public AddNurseCommand parseAddNurse(String fullCommand) throws IHospitalException {
        String id;
        String dummy = fullCommand.trim();
        if (dummy.indexOf("/id") > dummy.indexOf("/n") || dummy.indexOf("/id") > dummy.indexOf("/ph")
                || dummy.indexOf("/id") > dummy.indexOf("/e") || dummy.indexOf("/n") > dummy.indexOf("/ph")
                || dummy.indexOf("/n") > dummy.indexOf("/e") || dummy.indexOf("/ph") > dummy.indexOf("/e")) {
            throw new IHospitalException("The format of input is incorrect, "
                    + "you may type `help` to view the command format.");
        }

        int idIndex = dummy.indexOf("/id") + 4;
        int nameIndex = dummy.indexOf("/n");
        id = dummy.substring(idIndex, nameIndex).trim();
        int phoneNumberIndex = dummy.indexOf("/ph");
        String name = dummy.substring(nameIndex + 3, phoneNumberIndex).trim();
        int emailIndex = dummy.indexOf("/e");
        String phoneNumber = dummy.substring(phoneNumberIndex + 4, emailIndex).trim();
        int titleIndex = dummy.indexOf("/t");
        String email = dummy.substring(emailIndex + 3,titleIndex).trim();
        int wardNumberIndex = dummy.indexOf("/w");
        String title = dummy.substring(titleIndex + 3, wardNumberIndex).trim();
        String wardNumber = dummy.substring(wardNumberIndex + 3).trim();
        return new AddNurseCommand(id, name, phoneNumber, email, title, wardNumber, false);
    }

    public Command parseAddPatient(String fullCommand) throws IHospitalException {
        String id;
        String dummy = fullCommand.trim();
        if (dummy.indexOf("/id") > dummy.indexOf("/n") || dummy.indexOf("/id") > dummy.indexOf("/ph")
                || dummy.indexOf("/id") > dummy.indexOf("/e") || dummy.indexOf("/n") > dummy.indexOf("/ph")
                || dummy.indexOf("/n") > dummy.indexOf("/e") || dummy.indexOf("/ph") > dummy.indexOf("/e")) {
            throw new IHospitalException("The format of input is incorrect, "
                    + "you may type `help` to view the command format.");
        }
        int idIndex = dummy.indexOf("/id") + 4;
        int nameIndex = dummy.indexOf("/n");
        id = dummy.substring(idIndex, nameIndex).trim();
        nameIndex += 3;
        int phoneNumberIndex = dummy.indexOf("/ph");
        String name = dummy.substring(nameIndex, phoneNumberIndex).trim();
        int emailIndex = dummy.indexOf("/e");
        String phoneNumber = dummy.substring(phoneNumberIndex + 4, emailIndex).trim();
        int symptomIndex = dummy.indexOf("/s");
        String email = dummy.substring(emailIndex + 3,symptomIndex).trim();
        int descIndex = dummy.indexOf("/d");
        String symptom = dummy.substring(symptomIndex + 3,descIndex).trim();
        String description = dummy.substring(descIndex + 3).trim();
        return new AddPatientCommand(id, name, phoneNumber, email, symptom, description);
    }

    public Command parseAddWard(String fullCommand) throws IHospitalException {
        String dummy = fullCommand.trim();
        int doctorIndex = dummy.indexOf("/d");
        int patientIndex = dummy.indexOf("/p");
        int nurseIndex = dummy.indexOf("/n");
        String s = dummy.substring(doctorIndex + 3, patientIndex).trim();
        doctorIndex = Integer.parseInt(s);
        s = dummy.substring(patientIndex + 3, nurseIndex).trim();
        patientIndex = Integer.parseInt(s);
        int wardIndex = dummy.indexOf("/w");
        s = dummy.substring(nurseIndex + 3,wardIndex).trim();
        nurseIndex = Integer.parseInt(s);
        s = dummy.substring(wardIndex + 3).trim();
        wardIndex = Integer.parseInt(s);
        return new AddWardCommand(doctorIndex, patientIndex, nurseIndex, wardIndex);
    }

    public Command parseAddAppointment(String fullCommand) throws IHospitalException {
        String time;
        String dummy = fullCommand.trim();
        int timeIndex = dummy.indexOf("/t");
        int doctorIndex = dummy.indexOf("/d");
        timeIndex += 3;
        time = dummy.substring(timeIndex, doctorIndex).trim();
        int patientIndex = dummy.indexOf("/p");
        doctorIndex += 3;
        String s = dummy.substring(doctorIndex, patientIndex).trim();

        doctorIndex = Integer.parseInt(s);
        patientIndex += 3;
        s = dummy.substring(patientIndex).trim();
        patientIndex = Integer.parseInt(s);
        return new AddAppointmentCommand(doctorIndex, patientIndex, time);
    }

    public Command parseAddPatientDescription(String fullCommand) throws IHospitalException {
        String dummy = fullCommand.trim();
        int patientIndex = dummy.indexOf("/p");
        int descriptionIndex = dummy.indexOf("/d");
        int patientID = Integer.parseInt(dummy.substring(patientIndex, descriptionIndex).trim());
        descriptionIndex += 3;
        String description = dummy.substring(descriptionIndex);
        return new AddPatientDescriptionCommand(description, patientID);
    }

    public Command parseAddCommand(String fullCommand) throws IHospitalException {
        if (fullCommand.contains("add doctor")) {
            return parseAddDoctor(fullCommand);
        } else if (fullCommand.contains("add patient")) {
            try {
                return parseAddPatient(fullCommand);
            } catch (Exception e) {
                System.out.println("The input format of the patient information is wrong.");
            }

        } else if (fullCommand.contains("add nurse")) {
            return parseAddNurse(fullCommand);
        } else if (fullCommand.contains("add appointment")) {
            return parseAddAppointment(fullCommand);
        } else if (fullCommand.contains("add patient description")) {
            return parseAddPatientDescription(fullCommand);
        } else if (fullCommand.contains("add ward")) {
            return parseAddWard(fullCommand);
        }
        return null;
    }


    public Command parseDeleteDoctor(String fullCommand) throws IHospitalException {
        String[] dummy = fullCommand.split(" ");
        if (dummy.length <= 2) {
            throw new IHospitalException("Please enter the id of the doctor you want to delete");
        } else {
            int index = Integer.parseInt(dummy[2]);
            return new DeleteDoctorCommand(index);
        }
    }

    public Command parseDeletePatient(String fullCommand) throws IHospitalException {
        String[] dummy = fullCommand.split(" ");
        if (dummy.length <= 2) {
            throw new IHospitalException("Please enter the id of the patient you want to delete");
        } else {
            int index = Integer.parseInt(dummy[2]);
            return new DeletePatientCommand(index);
        }
    }

    public Command parseDeleteAppointment(String fullCommand) throws IHospitalException {
        String[] dummy = fullCommand.split(" ");
        if (dummy.length <= 2) {
            throw new IHospitalException("Please enter the id of the appointment you want to delete");
        } else {
            int index = Integer.parseInt(dummy[2]);
            return new DeleteAppointmentCommand(index);
        }
    }

    public Command parseDeleteCommand(String fullCommand) throws IHospitalException {
        if (fullCommand.contains("delete doctor")) {
            try {
                return parseDeleteDoctor(fullCommand);
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (fullCommand.contains("delete patient")) {
            try {
                return parseDeletePatient(fullCommand);
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (fullCommand.contains("delete appointment")) {
            try {
                return parseDeleteAppointment(fullCommand);
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            throw new IHospitalException("Please enter whether you want to delete a doctor, "
                    + "patient, nurse or appointment");
        }
        return null;
    }

    public Command parseSearchCommand(String fullCommand) throws IHospitalException {
        if (fullCommand.contains("doctor")) {
            String dummy = fullCommand.trim();
            String index = dummy.substring(dummy.indexOf("doctor ") + 7);
            return new SearchDoctorCommand(index);
        } else if (fullCommand.contains("patient")) {
            String dummy = fullCommand.trim();
            String index = dummy.substring(dummy.indexOf("patient ") + 8);
            return new SearchPatientCommand(index);
        } else if (fullCommand.contains("appointment")) {
            String dummy = fullCommand.trim();
            String time = dummy.substring(dummy.indexOf("appointment ") + 12).trim();
            return new SearchAppointmentCommand(time);
        }
        return null;
    }

    public Command parseListCommand(String fullCommand) throws IHospitalException {
        if (fullCommand.contains("list doctor")) {
            return new ListDoctorListCommand();
        } else if (fullCommand.contains("list appointment")) {
            return new ListAppointmentListCommand();
        } else if (fullCommand.contains("list patient")) {
            return new ListPatientListCommand();
        }
        return null;
    }

    public Command parseEditDoctorCommand(String fullCommand) throws IHospitalException {
        String dummy = fullCommand.trim();
        int index = Integer.parseInt(dummy.substring(dummy.indexOf("edit /d") + 8,
                dummy.indexOf("edit /d") + 9));
        if (fullCommand.contains("/ph")) {
            String newInformation = dummy.substring(dummy.indexOf("/ph") + 4);
            return new EditDoctorCommand(index, "ph", newInformation);
        } else if (fullCommand.contains("/e")) {
            String newInformation = dummy.substring(dummy.indexOf("/e") + 3);
            System.out.println("qwq" + newInformation);
            return new EditDoctorCommand(index, "e", newInformation);
        } else if (fullCommand.contains("/n")) {
            String newInformation = dummy.substring(dummy.indexOf("/n") + 3);
            return new EditDoctorCommand(index, "n", newInformation);
        } else {
            throw new IHospitalException("Wrong format detected\n");
        }
    }

    public Command parseEditPatientCommand(String fullCommand) throws IHospitalException {
        String dummy = fullCommand.trim();
        int index = Integer.parseInt(dummy.substring(dummy.indexOf("edit /p") + 8,
                dummy.indexOf("edit /p") + 9));
        if (fullCommand.contains("/ph")) {
            String newInformation = dummy.substring(dummy.indexOf("/ph") + 4);
            return new EditPatientCommand(index, "ph", newInformation);
        } else if (fullCommand.contains("/e")) {
            String newInformation = dummy.substring(dummy.indexOf("/e") + 3);
            return new EditPatientCommand(index, "e", newInformation);
        } else if (fullCommand.contains("/n")) {
            String newInformation = dummy.substring(dummy.indexOf("/n") + 3);
            return new EditPatientCommand(index, "n", newInformation);
        } else {
            throw new IHospitalException("Wrong format detected\n");
        }
    }

    public Command parseEditAppointmentCommand(String fullCommand) throws IHospitalException {
        String dummy = fullCommand.trim();
        int index = Integer.parseInt(dummy.substring(dummy.indexOf("edit /a") + 8,
                dummy.indexOf("edit /a") + 9));
        if (fullCommand.contains("/doctor")) {
            String newInformation = dummy.substring(dummy.indexOf("/doctor") + 8);
            return new EditAppointmentCommand(index, "d", newInformation);
        } else if (fullCommand.contains("/patient")) {
            String newInformation = dummy.substring(dummy.indexOf("/patient") + 9);
            return new EditAppointmentCommand(index, "p", newInformation);
        } else if (fullCommand.contains("/time")) {
            String newInformation = dummy.substring(dummy.indexOf("/time") + 6);
            return new EditAppointmentCommand(index, "t", newInformation);
        } else {
            throw new IHospitalException("Wrong format detected\n");
        }
    }

    public Command parse(String fullCommand) throws IHospitalException {
        if (fullCommand.contains("add")) {
            return parseAddCommand(fullCommand);
        } else if (fullCommand.contains("get") && fullCommand.contains("appointment")) {
            return parseGetAppointment(fullCommand);
        } else if (fullCommand.contains("sort appointment")) {
            return new SortAppointmentByTimeCommand();
        } else if (fullCommand.contains("delete")) {
            return parseDeleteCommand(fullCommand);
        } else if (fullCommand.contains("list")) {
            return parseListCommand(fullCommand);
        } else if (fullCommand.contains("help")) {
            return new HelpCommand();
        } else if (fullCommand.contains("search")) {
            return parseSearchCommand(fullCommand);
        } else if (fullCommand.contains("edit /d")) {
            return parseEditDoctorCommand(fullCommand);
        } else if (fullCommand.contains("edit /p")) {
            return parseEditPatientCommand(fullCommand);
        } else if (fullCommand.contains("edit /a")) {
            return parseEditAppointmentCommand(fullCommand);
        } else {
            throw new IHospitalException("Invalid command given\n");
        }
    }
}
