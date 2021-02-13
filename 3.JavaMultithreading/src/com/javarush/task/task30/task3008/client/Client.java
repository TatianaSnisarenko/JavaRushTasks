package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

    protected String getServerAddress() {
        ConsoleHelper.writeMessage("Введите адрес сервера");
        return ConsoleHelper.readString();
    }

    protected int getServerPort() {
        ConsoleHelper.writeMessage("Введите номер порта");
        return ConsoleHelper.readInt();
    }

    protected String getUserName() {
        ConsoleHelper.writeMessage("Введите имя пользователя");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole() {
        return true;
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Во время отправки произошло исключение IOException");
            clientConnected = false;
        }
    }

    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                ConsoleHelper.writeMessage("Во время ожидания соединения возникла ошибка!");
                System.exit(1);
            }
            if (clientConnected) {
                notify();
                ConsoleHelper.writeMessage("Соединение установлено.\nДля выхода наберите команду 'exit'.");

                while (clientConnected) {
                    String message = ConsoleHelper.readString();
                    if (message.equalsIgnoreCase("exit")) {
                        break;
                    } else if (shouldSendTextFromConsole()) {
                        sendTextMessage(message);
                    }
                }
            } else {
                ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
            }
        }
    }


    public class SocketThread extends Thread {
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage("Участник " + userName + " присоединился к чату.");
        }

        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage("Участник " + userName + " покинул чат.");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this) {
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if(message == null || message.getType() == null){
                    throw new IOException("Unexpected MessageType");
                } else if (message.getType().equals(MessageType.NAME_REQUEST) || message.getType().equals(MessageType.NAME_ACCEPTED)) {
                    if (message.getType().equals(MessageType.NAME_REQUEST)) {
                        String userName = getUserName();
                        connection.send(new Message(MessageType.USER_NAME, userName));
                    } else if (message.getType().equals(MessageType.NAME_ACCEPTED)) {
                        notifyConnectionStatusChanged(true);
                        return;
                    }
                } else {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

           protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() != null) {

                    switch (message.getType()) {
                        case TEXT:
                            processIncomingMessage(message.getData());
                            break;
                        case USER_ADDED:
                            informAboutAddingNewUser(message.getData());
                            break;
                        case USER_REMOVED:
                            informAboutDeletingNewUser(message.getData());
                            break;
                        default:
                            throw new IOException("Unexpected MessageType");
                    }
                } else {
                    throw new IOException("Unexpected MessageType");
                }

            }
        }

        public void run(){
            String address = getServerAddress();
            int port = getServerPort();
            try(Socket socket = new Socket(address, port)) {
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            } catch (IOException | ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }

        }

    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();

    }
}
