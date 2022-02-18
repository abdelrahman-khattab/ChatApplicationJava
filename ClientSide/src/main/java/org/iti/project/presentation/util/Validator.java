package org.iti.project.presentation.util;

public class Validator {

    private Validator() {

    }

    public static boolean nameValidation(String clientName) {

        if (clientName.trim().matches("^[A-Za-z ]{5,29}$") && clientName != null)
            return true;

        return false;
    }

    public static boolean phoneValidation(String clientPhone) {

        if (clientPhone.trim().matches("01(0|1|2|5)\\d{8}") && clientPhone != null)
            return true;

        return false;
    }

    public static boolean emailValidation(String clientEmail) {

        if (clientEmail.trim().matches("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") && clientEmail != null)
            return true;

        return false;
    }

    public static boolean passwordValidation(String clientPassword) {

        if (clientPassword.trim().matches("(^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[_&.])(?=\\S+$).{8,}$)")
                && clientPassword != null)
            return true;
        return false;
    }


}
