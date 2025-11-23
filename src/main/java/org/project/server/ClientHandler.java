package org.project.server;

import org.project.protocol.HttpRequest;
import org.project.protocol.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * Behandelt Client-Verbindungen und verarbeitet HTTP-Anfragen.
 */
public class ClientHandler extends Thread{
    private final Socket socket;

    private final Router router;

    private static final Logger logger = Logger.getLogger(ClientHandler.class.getName());

    /**
     * Erstellt einen neuen ClientHandler für den angegebenen Socket.
     *
     * @param socket der Socket des Clients
     */
    public ClientHandler(Socket socket){
        this.socket = socket;
        this.router = new Router();
    }

    /**
     * Liest HTTP-Anfragen vom Client, verarbeitet sie über den Router und
     * sendet die entsprechenden HTTP-Antworten zurück.
     */
    public void run(){
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OutputStream out = this.socket.getOutputStream();
            while(!this.socket.isClosed()){
                HttpRequest request = HttpRequest.parse(in);
                if (request == null){
                    break;
                }
                logger.info("Received new HttpRequest");
                HttpResponse response = this.router.handle(request);
                logger.info("Created HttpResponse");
                out.write(response.toBytes());
                out.flush();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
