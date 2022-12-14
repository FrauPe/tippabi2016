/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import abiturklassen.listenklassen.List;

/**
 *
 * @author q2.02Schaaf
 */
public class Kundenverwaltung {

    List<User> userList = new List<>();

    public int anmelden(String benutzername, String passwort, String ip, int port) {
        userList.toFirst();
        while (userList.hasAccess()) {
            if (benutzername == userList.getContent().getBenutzername()) {
                if (passwort == userList.getContent().getPasswort()) {
                    if (userList.getContent().getIP() == null && userList.getContent().getPort() == 0) {
                        userList.getContent().setIP(ip);
                        userList.getContent().setPort(port);
                        return 0;
                    } else {
                        return 1;
                    }
                } else {
                    return 2;
                }
            }
            userList.next();
        }
        return 3;
    }

    public void erstelleUser(String benutzername, String passwort) {
        userList.append(new User(benutzername, passwort));
    }

    public int getUserID(String ip, int port) {
        userList.toFirst();
        while (userList.hasAccess()) {
            if (ip == userList.getContent().getIP() && port == userList.getContent().getPort()) {
                return userList.getContent().getUserID();
            }
            userList.next();
        }
        return -1;
    }

    public void setzeTipp(int userID, int spielID, int tor1, int tor2) {
        userList.toFirst();
        while (userList.hasAccess()) {
            if (userID == userList.getContent().getUserID()) {
                userList.getContent().setSpieleTipps(spielID, tor1, tor2);
            }
        }
        userList.next();
    }

    public int[] getTipp(int userID, int spielID) {
        userList.toFirst();
        while (userList.hasAccess()) {
            if (userID == userList.getContent().getUserID()) {
                return userList.getContent().getSpieleTipps()[spielID];
            }
            userList.next();
        }

        return null;
    }

    public void abmelden(int userID) {
        userList.toFirst();
        while (userList.hasAccess()) {
            if (userID == userList.getContent().getUserID()) {
                userList.getContent().setIP(null);
                userList.getContent().setPort(0);
            }
            userList.next();
        }
    }
}
