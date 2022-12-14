package client;


import javax.swing.*;
import abiturklassen.netzklassen.*;

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
