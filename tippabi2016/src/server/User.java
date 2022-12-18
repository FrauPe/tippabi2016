package server;

import abiturklassen.listenklassen.List;
/**
 *
 * @author Q2.02Trell
 * Kommentare Frau P
 */
public class User { // aka Teilnehmer

    String benutzername;
    String passwort;
    static int nextUserID = 0;
    int userID;
    //Noch ein Attribut für die User-Punkte? vgl. Klasse Teilnehmer
    String IP; // Statt Klasse Verbindungsdaten: sinnvoll, NULL bzw. veraltet, falls nicht verbunden...
    int port = 0; // Statt Klasse Verbindungsdaten: sinnvoll, NULL bzw. veraltet, falls nicht verbunden...
    int[][] spieleTipps = new int[51][2]; //Statt Klasse Tipp: sinnvoll!

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

    public int[] getSpielTipps(int pSpiel) {
        pSpiel--;
        if (pSpiel>=0 && pSpiel<=spieleTipps.length) return spieleTipps[pSpiel];
        int[] notipp={-1,-1};
        return notipp;
    }
    
    public void setSpieleTipps(int spielID, int tor1, int tor2) {
        spielID--;
        spieleTipps[spielID][0] = tor1;
        spieleTipps[spielID][1] = tor2;
    }
}
