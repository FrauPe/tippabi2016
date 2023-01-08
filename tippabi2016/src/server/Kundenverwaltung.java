package server;

import abiturklassen.listenklassen.List;

/**
 *
 * @author q2.02Schaaf 
 * Kommentare FrauPe
 */
public class Kundenverwaltung {//aka - wäre im Abiturvorschlag Teil der Serverklasse, gefällt mir so besser!

    List<User> userList = new List<>();

    final int[][] gameResults = {{2,1},{0,1},{2,1},{1,1},{0,1},
                                 {1,0},{2,0},{1,0},{1,1},{0,2},
                                 {0,2},{1,1},{1,2},{1,1},{2,0},
                                 {2,1},{0,2},{0,0},{1,0},{2,2},
                                 {3,0},{3,0},{1,1},{0,0},{0,1},
                                 {0,0},{0,0},{0,3},{0,1},{0,1},
                                 {2,1},{0,2},{2,1},{3,3},{0,1},
                                 {0,1},{4,5},{1,0},{0,1},{2,1},
                                 {3,0},{0,4},{2,0},{1,2},{3,5},
                                 {3,1},{6,5},{5,2},{2,0},{0,2},
                                 {1,0}};

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

    /**
     * Vermutlich für die User-Liste, sinnvoll, noch nicht umgesetzt...
     */
    public int save(String filePath) {
        return 0;
    }

    public int load(String filePath) {
        return 0;
    }

    public void werteSpiel(int spielNr) {
        for (userList.toFirst(); userList.hasAccess(); userList.next()) {
            User user = userList.getContent();
            int[] tipp = user.getSpielTipps(spielNr);
            if (gameResults[spielNr - 1][0] == tipp[0]) {
                user.setPunkte(user.getPunkte() + 1);
            }
            if (gameResults[spielNr - 1][1] == tipp[1]) {
                user.setPunkte(user.getPunkte() + 1);
            }
            if (gameResults[spielNr - 1][0] == tipp[0] && gameResults[spielNr - 1][1] == tipp[1]) {
                user.setPunkte(user.getPunkte() + 1);
            }
        }
        
    }

    public void werteSpieleBis(int SpielNr) {
        for (userList.toFirst(); userList.hasAccess(); userList.next()) {
            userList.getContent().setPunkte(0);
        }
        for (int i = 1; i <= SpielNr; i++) {
            werteSpiel(i);
        }
    }

    public int getPlatz(int userID) {
        int platz = 1;
        int userPunkte = 0;
        for (userList.toFirst(); userList.hasAccess(); userList.next()) {
            if (userID == userList.getContent().getUserID()) {
                userPunkte = userList.getContent().getPunkte();
            }
        }
        for (userList.toFirst(); userList.hasAccess(); userList.next()) {
            if (userList.getContent().getPunkte() > userPunkte) {
                platz++;
            }
        }
        return platz;
    }

    public int getPunkte(int userID) {
        return userList.getContent().getPunkte();
    }
    
    public int SMMT() {
        int[] tippzahl = new int[51];
        int maxtipps = 0;
        userList.toFirst();
        while (userList.hasAccess()) {
            User u = userList.getContent();
            for (int i = 0; i < 51; i++) {
                if (u.getSpielTipps(i+1)[0] != -1) {//i+1, da Methode Spiele von 1 bis 51 erwartet...
                    tippzahl[i]++;
                }
            }
            userList.next();    
        }
        for (int i = 1; i < 51; i++) {
            if (tippzahl[i] > tippzahl[maxtipps]) {
                maxtipps = i;
            }
        }
        return maxtipps++;//maxtipps+1, da die Spiele von 1 bis 51 gezählt werden sollen
    }
}