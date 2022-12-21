package server;

import abiturklassen.netzklassen.*;
/**
 *
 * @author q2.02Schaaf
 * Kommentare FrauPe
 */
public class Tippserver extends Server {

    Kundenverwaltung data;//Ersetzt teilnehmersammlung, gut!
    javax.swing.JTextArea output;//Vereinfacht Kommunikation mit GUI - siehe ChatClient-Anwendung, gut!
    String filePath;//Noch nicht genutzt...ok!

    public Tippserver(javax.swing.JTextArea out, String pFilePath) {
        super(2000);
        data = new Kundenverwaltung();
        data.load(pFilePath);//macht noch nix - ok!
        filePath = pFilePath;//macht noch nix - ok!
        output = out;
        data.erstelleUser("Benutzername", "Passwort");//interner Test, naja ok :-)
    }

    public void processNewConnection(String pClientIP, int pClientPort) {
        
    }

    public void processMessage(String pClientIP, int pClientPort, String pMessage) { // Befehl Platz und Befehl Punkte muss noch realisiert werden
        output.append(pClientIP + ": " + String.valueOf(pClientPort) + "-> " + pMessage + "\n");
        String reply = "";
        String croppedClientIP = pClientIP.substring(1);
        int userID = data.getUserID(croppedClientIP, pClientPort);
        String[] command = pMessage.split(" ");

        try {
            switch (command[0]) {
                case "ANMELDEN":
                    int tmp = data.anmelden(command[1], command[2], croppedClientIP, pClientPort);
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
                    break;
                case "TIPP":
                    if (userID == -1) {
                        reply = "-ERR Du bist nicht angemeldet.";
                        break;
                    }
                    if (Integer.valueOf(command[1]) > 51 || Integer.valueOf(command[1]) <= 0) {
                        reply = "-ERR Die Spiel Nr. " + command[1] + " ist ungültig.";
                        break;
                    }
                    data.setzeTipp(userID, Integer.valueOf(command[1]), Integer.valueOf(command[2]), Integer.valueOf(command[3]));
                    reply = "+OK Der Tipp auf Spiel Nr. " + command[1] + " wurde gesetzt.";
                    break;
                case "ABMELDEN":
                    if (userID == -1) {
                        reply = "-ERR Du bist nicht angemeldet.";
                        break;
                    }
                    data.abmelden(userID);
                    reply = "+OK Du wurdest abgemeldet.";
                    break;
                case "SPIEL":
                    if (userID == -1) {
                        reply = "-ERR Du bist nicht angemeldet.";
                        break;
                    }
                    if (Integer.valueOf(command[1]) > 51 || Integer.valueOf(command[1]) <= 0) {
                        reply = "-ERR Die Spiel Nr. " + command[1] + " ist ungültig.";
                        break;
                    }
                    int[] tipp = data.getTipp(userID, Integer.valueOf(command[1]));
                    if (tipp[0] == -1 || tipp[1] == -1) {
                        reply = "-ERR Kein Tipp für Spiel Nr. " + command[1] + " abgegeben.";
                        break;
                    }
                    reply = "+OK Dein Tipp für Spiel " + command[1] + ": " + tipp[0] + ":" + tipp[1];
                    break;
//                case "SMMT":
//                    reply = "+OK Die Meisten Tipps wurden für Spiel " + data.getSMMT() + " abgegeben.";
//                    break;  
                case "PUNKTE":
                    if (userID == -1) {
                        reply = "-ERR Du bist nicht angemeldet.";
                        break;
                    }
                    reply = "+OK Du hast " + data.getPunkte(userID) + " Punkte erzielt.";
                    break;
                case "PLATZ":
                    if (userID == -1) {
                        reply = "-ERR Du bist nicht angemeldet.";
                        break;
                    }
                    reply = "+OK Du bist auf dem" + data.getPlatz(userID) + ". Platz.";
                    break;
                default:
                    reply = "-ERR Befehl ungültig.";
                    break;

            }
        } catch (Exception e) {
            reply = "-ERR";
            output.append("SERVER ERROR while processing command: " + e.getMessage() + "\n");
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
        return data.save(filePath);//macht noch nix - ok!
    }

public void werteSpiele(int SpielNr)

}
