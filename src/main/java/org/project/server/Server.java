package org.project.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * Server-Klasse, die Clients über einen Socket akzeptiert und verwaltet.
 */
public class Server {
    private final int port;
    private boolean running;

    private static final Logger logger = Logger.getLogger(Server.class.getName());

    /**
     * Erstellt einen Server auf dem angegebenen Port.
     */
    public Server(int port){
        this.port = port;
    }

    /**
     * Startet den Server und akzeptiert eingehende Client-Verbindungen.
     */
    public void start(){
        logger.info("Starting Server on port " + this.port);

        this.setRunning(true);
        try {
            ServerSocket server = new ServerSocket(port);
            while(running){
                Socket client = server.accept();
                logger.info("Accepted new Client");
                new ClientHandler(client).start();
            }
            server.close();
        } catch(IOException e){
            logger.warning("IOException at Server");
        }
    }

    /**
     * Setzt den Status, ob der Server läuft.
     *
     * @param running true, wenn der Server laufen soll, sonst false
     */
    public void setRunning(boolean running){
        this.running = running;
    }
}
