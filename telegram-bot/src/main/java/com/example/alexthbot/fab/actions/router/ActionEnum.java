package com.example.alexthbot.fab.actions.router;

public enum ActionEnum {

    START("/start"),
    REGISTRATION("/registration"),
    CHOSE_ROLE("/chose_role"),
    REGISTRATION_WAITING_LOGIN("/wait login"),
    REGISTRATION_WAITING_PASSWORD("/wait password"),
    CHOOSE_DOCTOR("/choose doctor"),
    CHOOSE_PROCEDURE_TOOTH("/choose procedure tooth"),
    CHOOSE_PROCEDURE_DRAGS("/choose procedure drags"),
    CHOOSE_TIME("/choose time"),
    CHOOSE_FIRST_NAME("/choose first name"),
    CHOOSE_LAST_NAME("/choose last name"),
    CHOOSE_DATE("/choose date"),
    MIDDLE_BOOKED("/middle_booked"),
    SHOW_APPOINTMENTS("/show_appointments"),
    LOGIN_AUTH("/login_auth"),
    PASSWORD_AUTH("/password_auth"),
    CHOOSE_DOCTOR_AFTER_LOGIN("/choose_doctor_after_login"),
    CHOOSE_DOCTOR_SECOND_TIME("/choose_doctor_second_time"),
    CHOOSE_LOGIN_OR_REGISTRATION("/Action_Wait_Login_Or_Registration"),
    ANY(null);

    private String command;

    ActionEnum(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static ActionEnum interpret(String value) {
        if (value == null || value.isEmpty()) {
            return ANY;
        }

        value = value.trim();

        if (START.command.equals(value)) return START;
        if (REGISTRATION.command.equals(value)) return REGISTRATION;
        if (CHOSE_ROLE.command.equals(value)) return CHOSE_ROLE;
        if (REGISTRATION_WAITING_LOGIN.command.equals(value)) return REGISTRATION_WAITING_LOGIN;
        if (REGISTRATION_WAITING_PASSWORD.command.equals(value)) return REGISTRATION_WAITING_PASSWORD;
        if (CHOOSE_FIRST_NAME.command.equals(value)) return CHOOSE_FIRST_NAME;
        if (CHOOSE_LAST_NAME.command.equals(value)) return CHOOSE_LAST_NAME;
        if (CHOOSE_PROCEDURE_TOOTH.command.equals(value)) return CHOOSE_PROCEDURE_TOOTH;
        if (CHOOSE_PROCEDURE_DRAGS.command.equals(value)) return CHOOSE_PROCEDURE_DRAGS;
        if (CHOOSE_TIME.command.equals(value)) return CHOOSE_TIME;
        if (CHOOSE_DOCTOR.command.equals(value)) return CHOOSE_DOCTOR;
        if (CHOOSE_DATE.command.equals(value)) return CHOOSE_DATE;
        if (MIDDLE_BOOKED.command.equals(value)) return MIDDLE_BOOKED;
        if (SHOW_APPOINTMENTS.command.equals(value)) return SHOW_APPOINTMENTS;
        if (LOGIN_AUTH.command.equals(value)) return LOGIN_AUTH;
        if (PASSWORD_AUTH.command.equals(value)) return PASSWORD_AUTH;
        if (CHOOSE_DOCTOR_AFTER_LOGIN.command.equals(value)) return CHOOSE_DOCTOR_AFTER_LOGIN;
        if (CHOOSE_DOCTOR_SECOND_TIME.command.equals(value)) return CHOOSE_DOCTOR_SECOND_TIME;
        if (CHOOSE_LOGIN_OR_REGISTRATION.command.equals(value)) return CHOOSE_LOGIN_OR_REGISTRATION;
        return ANY;
    }

}
