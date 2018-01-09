package ru.artsok;


import org.apache.log4j.Logger;
import ru.artsok.tcp.SocketRunnable;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class TesterApp {
    final static Logger logger = Logger.getLogger(TesterApp.class);

    public static void main(String[] args) throws PropertyVetoException, IOException, SQLException {
        logger.debug("START test!");

        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(7588);
                while (true) {
                    Socket socket = null;
                    socket = serverSocket.accept();
                    new Thread(new SocketRunnable(socket)).start();
                }
            } catch (Exception e) {
                logger.debug(e.getMessage());
            }
        }).start();
    }
}
