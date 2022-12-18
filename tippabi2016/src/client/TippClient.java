package client;


import javax.swing.*;
import abiturklassen.netzklassen.*;

/**
 * Kommentare FrauPe - minimalistisch aber tatsächlich ausreichend, 
 * da der Tippclient nur die Servernachrichten weitergeben muss und
 * keine Logik umsetzen muss. Die Erzeugung der korrekten Nachrichten
 * für jeden Knopf in der GUI wäre eigentlich die Client-Logik, die hier 
 * hinein gehört - allerdings ist der Aufwand, dies aus der GUI hierhin 
 * auszulagern zu groß.
 */
public class TippClient extends Client {
  JTextArea textbereich;
  
  public TippClient(String serverIP, JTextArea pTextbereich){
    super(serverIP, 2000);
    textbereich=pTextbereich;
  }
  
  public void processMessage(String pMessage){
    if (this.istVerbunden())
       textbereich.setText(textbereich.getText() + pMessage + "\n");
  }
  
  public void disconnect() {}
}
