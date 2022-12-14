/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import abiturklassen.netzklassen.*;
import abiturklassen.listenklassen.List;
import javax.swing.JFrame;

/**
 *
 * @author q2.02Schaaf
 */
public class Tippserver extends Server {

    Kundenverwaltung data;
    javax.swing.JTextArea output;
    String filePath;

    public Tippserver(javax.swing.JTextArea out, String pFilePath) {
        super(2000);
        data = new Kundenverwaltung();
        data.load(pFilePath);
        filePath = pFilePath;
        output = out;
    }

    public void processNewConnection(String pClientIP, int pClientPort) {

    }

    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        output.append(pClientIP + ": " + String.valueOf(pClientPort) + "-> " + pMessage + "\n");
        String reply = "";
        int userID = data.getUserID(pClientIP, pClientPort);
        String[] command = pMessage.split(" ");

        try {
            switch (command[0]) {
                case "ANMELDEN":
                    int tmp = data.anmelden(command[1], command[2], pClientIP, pClientPort);
                    switch (tmp) {
                        case 0:
                            reply = "+OK " + command[1] + " ist angemeldet.";
                            break;
                        case 1:
                            reply = "-ERR Der Name " + command[1] + " ist bereits angemeldet.";
                            break;
                        case 2:
                            reply = "-ERR Das Kennwort ist falsch.";
                            break;
                        case 3:
                            reply = "-ERR " + command[1] + " ist unbekannt.";
                            break;
                    }
                case "TIPP":
                    if (userID == -1) {
                        reply = "-ERR Du bist nicht angemeldet";
                        break;
                    }
                    if (Integer.valueOf(command[1]) > 51 && Integer.valueOf(command[1]) <= 0) {
                        reply = "-ERR Die Spiel Nr. " + command[1] + " ist ungültig.";
                        break;
                    }
                    data.setzeTipp(userID, Integer.valueOf(command[1]), Integer.valueOf(command[2]), Integer.valueOf(command[3]));
                    break;
                case "ABMELDEN":
                    if (userID == -1) {
                        reply = "-ERR Du bist nicht angemeldet";
                        break;
                    }
                    data.abmelden(userID);
                case "SPIEL":
                    if (userID == -1) {
                        reply = "-ERR Du bist nicht angemeldet";
                        break;
                    }
                    if (Integer.valueOf(command[1]) > 51 && Integer.valueOf(command[1]) <= 0) {
                        reply = "-ERR Die Spiel Nr. " + command[1] + " ist ungültig.";
                        break;
                    }
                    int[] tipp = data.getTipp(userID, Integer.valueOf(command[1]));
                    if (tipp[0] == -1 || tipp[1] == -1) {
                        reply = "-ERR Kein Tipp für Spiel Nr. " + command[1] + " abgegeben.";
                        break;
                    }
                    reply = "+OK Dein Tipp für Spiel " + command[1] + " " + tipp[0] + " " + tipp[1];
                    break;
                default:
                    reply = "-ERR Befehl ungültig.";
                    break;

            }
        } catch (Exception e) {
            reply = "-ERR";
        }
        send(pClientIP, pClientPort, reply);
        output.append("-> " + reply + "\n");

    }

    public void processClosedConnection(String pClientIP, int pClientPort) {
        int userID = data.getUserID(pClientIP, pClientPort);
        if (userID != -1) {
            data.abmelden(userID);
        }
    }

    public int shutdown() {
        return data.save(filePath);
    }

}
