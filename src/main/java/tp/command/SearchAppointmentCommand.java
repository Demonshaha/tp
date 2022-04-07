package tp.command;

import tp.*;

public class SearchAppointmentCommand extends Command {
    protected String time;

    public SearchAppointmentCommand(String time) {
        this.time = time;
    }

    @Override
    public String execute(DoctorList doctorList, PatientList patientList, NurseList nurseList,
                          WardList wardList, AppointmentList appointmentList, Ui ui,
                          DoctorStorage doctorStorage, WardStorage wardStorage,
                          PatientStorage patientStorage, NurseStorage nurseStorage,
                          AppointmentStorage appointmentStorage) throws IHospitalException {
        Appointment curr = appointmentList.searchAppointmentByTime(time);
        if (curr == null) {
            return String.format("There is no appointment at " + time + "\n");
        }
        return String.format("The appointments found are here" + curr + "\n");
    }
}