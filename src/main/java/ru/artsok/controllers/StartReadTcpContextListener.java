package ru.artsok.controllers;


import org.apache.log4j.Logger;
import ru.artsok.tcp.SocketRunnable;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.net.ServerSocket;
import java.net.Socket;


@WebListener
public class StartReadTcpContextListener implements ServletContextListener {
    final static Logger logger = Logger.getLogger(StartReadTcpContextListener.class);


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        logger.debug("START!");

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

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
