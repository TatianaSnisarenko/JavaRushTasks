package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message) {
        for (Map.Entry<String, Connection> pair : connectionMap.entrySet()) {
            String key = pair.getKey();
            Connection value = pair.getValue();
            try {
                value.send(message);
            } catch (IOException e) {
                System.out.println("Сообщение не отправлено!");
            }
        }
    }


    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt());) {
            System.out.println("Сервер запущен!");
            while (true) {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        public void run(){
            ConsoleHelper.writeMessage("Установлено соединение с удаленным адресом: " + socket.getRemoteSocketAddress());
            String userName = null;

            try (Connection connection = new Connection(socket)){
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                notifyUsers(connection, userName);
                serverMainLoop(connection, userName);
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом");
            }finally{
                if(userName != null){
                    connectionMap.remove(userName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                }

            }
            ConsoleHelper.writeMessage("Соединение с удаленным адресом закрыто.");
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message answer = connection.receive();
                if (answer.getType() == MessageType.USER_NAME) {
                    String name = answer.getData();
                    if (!name.isEmpty() && !connectionMap.containsKey(name)) {
                        connectionMap.putIfAbsent(name, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        return name;
                    }

                }
            }
        }

        private void notifyUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> pair : connectionMap.entrySet()) {
                String otherUsername = pair.getKey();
                if (!otherUsername.equals(userName)) {
                    connection.send(new Message(MessageType.USER_ADDED, otherUsername));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            while(true){
                Message userMessage = connection.receive();
                if(userMessage != null && userMessage.getType() == MessageType.TEXT){
                    String newMessage = userName + ": " + userMessage.getData();
                    sendBroadcastMessage(new Message(MessageType.TEXT, newMessage));
                }else{
                    ConsoleHelper.writeMessage("Error!");
                }
            }
        }
    }

}
