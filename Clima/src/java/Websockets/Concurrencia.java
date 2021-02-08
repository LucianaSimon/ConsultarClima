/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.io.IOException;
import java.util.LinkedList;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Luciana
 */
@ServerEndpoint("/endpoint")
public class Concurrencia {


    public static final LinkedList<Session> peers = new LinkedList();
    
    @OnClose
    public void onClose(Session peer) {
        peers.remove(peer);
        aviso();
    }
    
    @OnOpen
    public void OnOpen(Session peer) throws IOException {
        peers.add(peer); //Agrego peer a la lista
        aviso();
    }

    @OnError
    public void onError(Throwable t) {
    }
    
    public void aviso(){
        try {
            for (Session p : peers) {
                if (p.isOpen()) {
                    //Le envio un aviso a todos los peers con la cantidad de usuarios 
                    p.getBasicRemote().sendText(String.valueOf(peers.size()));
                }
            }
        } catch (IOException e) {
        }
    }
}
