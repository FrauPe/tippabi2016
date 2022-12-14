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
        while(userList.hasAccess()){
            if(benutzername == userList.getContent().getBenutzername()){
                if(passwort == userList.getContent().getPasswort()){
                    if(userList.getContent().getIP() == null && userList.getContent().getPort() == 0) return 1;
                    else return 2;}
                else return 3;}                       
            else return 4;}     
        return 5;    
}
    
    public void erstelleUser(String benutzername, String passwort){
        userList.append(new User(benutzername, passwort));
    }
}


