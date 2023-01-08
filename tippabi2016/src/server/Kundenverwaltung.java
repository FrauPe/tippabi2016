package server;

import abiturklassen.listenklassen.List;

/**
 *
 * @author q2.02Schaaf
 * Kommentare FrauPe
 */
public class Kundenverwaltung {//aka - wäre im Abiturvorschlag Teil der Serverklasse, gefällt mir so besser!

    List<User> userList = new List<>();
    
    public int anmelden(String benutzername, String passwort, String ip, int port) {
        userList.toFirst();
        while (userList.hasAccess()) {
            if (benutzername.equals(userList.getContent().getBenutzername())) {
                if (passwort.equals(userList.getContent().getPasswort())) {
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
            if (ip.equals(userList.getContent().getIP()) && port == userList.getContent().getPort()) {
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
                return;
            }
            userList.next();
        }
    }

    public int[] getTipp(int userID, int spielID) {
        userList.toFirst();
        while (userList.hasAccess()) {
            if (userID == userList.getContent().getUserID()) {
                return userList.getContent().getSpieleTipps()[spielID - 1];
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
                return;
            }
            userList.next();
        }
    }
    public int SMMT() {
        int[] tippzahl = new int[51];
        int maxtipps = 0;
        userList.toFirst();
        while (userList.hasAccess()) {
            User u = userList.getContent();
            for (int i = 0; i < 51; i++) {
                if (u.getSpielTipps(i) != null) {
                    tippzahl[i]++;
                }
            }
            userList.next();    
            for (int i = 1; i < 51; i++) {
                if (tippzahl[i] > tippzahl[maxtipps]) {
                    maxtipps = i;
                }
            }
        }
    return maxtipps;
    }

 
    public int save(String filePath) {
        return 0;
    }

    public int load(String filePath) {
        return 0;
    }
}
