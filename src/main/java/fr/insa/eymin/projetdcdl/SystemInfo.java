package fr.insa.eymin.projetdcdl;

public class SystemInfo {

    public static String javaVersion() {
        return System.getProperty("java.version") + " zizi";
    }

    public static String javafxVersion() {
        return System.getProperty("javafx.version") + " caca";
    }

}