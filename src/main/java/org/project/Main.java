package org.project;

import org.project.server.Server;

import java.util.logging.Logger;

/**
 * Startpunkt der Applikation. Startet den Server auf Port 9090.
 * */
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Server server = new Server(9090);
        logger.info("Created Server");
        server.start();
    }
}