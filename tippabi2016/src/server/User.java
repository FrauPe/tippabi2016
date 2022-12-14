package server;


import abiturklassen.listenklassen.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Q2.02Trell
 */
public class User {
    String benutzername;
    String passwort;
    static int nextUserID = 0;
    int userID;
    String IP;
    int port = 0;
    List<Integer> spieleTipps = new List<>();

    public User(String benutzername, String passwort) {
        this.benutzername = benutzername;
        this.passwort = passwort;
        userID = nextUserID++;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public static int getUserID() {
        return nextUserID;
    }

    public static void setUserID(int userID) {
        User.nextUserID = userID;
    }

    public static int getNextUserID() {
        return nextUserID;
    }

    public static void setNextUserID(int nextUserID) {
        User.nextUserID = nextUserID;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public List<Integer> getSpieleTipps() {
        return spieleTipps;
    }

    public void setSpieleTipps(List<Integer> spieleTipps) {
        this.spieleTipps = spieleTipps;
    }

}
