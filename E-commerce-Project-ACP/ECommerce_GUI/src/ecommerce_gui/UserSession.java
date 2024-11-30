package ecommerce_gui;

public class UserSession {
    private static String role;

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        UserSession.role = role;
    }
}
