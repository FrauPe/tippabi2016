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
    int[][] spieleTipps = new int[51][2];

    public User(String benutzername, String passwort) {
        this.benutzername = benutzername;
        this.passwort = passwort;
        userID = nextUserID++;
        for (int i = 0; i < 51; i++) {
            spieleTipps[i][0] = -1;
            spieleTipps[i][1] = -1;
        }
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

    public int[][] getSpieleTipps() {
        return spieleTipps;
    }

    public void setSpieleTipps(int spielID, int tor1, int tor2) {
        spielID--;
        spieleTipps[spielID][0] = tor1;
        spieleTipps[spielID][1] = tor2;
    }
}
