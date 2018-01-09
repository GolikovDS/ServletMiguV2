package ru.artsok.tcp;


import org.apache.log4j.Logger;
import ru.artsok.tcp.entity.MiguHandleImpl;
import ru.artsok.tcp.entity.interfaces.MiguHandle;

import java.io.*;
import java.net.Socket;

public class SocketRunnable implements Runnable, Serializable {

    final static Logger LOGGER = Logger.getLogger(SocketRunnable.class);
    private Socket socket;
    public static boolean drowse = false;


    public SocketRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "cp1251"));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "cp1251"));
            MiguHandleImpl miguHandle = new MiguHandleImpl();
            socket.setSoTimeout(10000);

            System.err.println("Socket is open in " + Thread.currentThread().getName());

            while (true) {
                if (drowse) {
//                    System.out.println(Thread.currentThread().getName() + " drowse - getNumb");
                    bufferedWriter.write("getState\n");
                    bufferedWriter.flush();
                    Thread.sleep(5000);
                } else {
//                    System.out.println(Thread.currentThread().getName() + " not drowse - getState");
                    bufferedWriter.write("getState\n");
                    bufferedWriter.flush();
                    miguHandle.getMigu(bufferedReader.readLine());
                    Thread.sleep(1000);
                }
            }
        } catch (Throwable throwable) {
            for(int numb : MiguHandleImpl.getThreadLocal().get())
                MiguHandle.miguMap.getMap().remove(numb);
            System.err.println("Нет соеденения - " + throwable.getMessage());

        }
    }

    private int getValue(String data) {
        return Integer.parseInt("1");
    }

}
