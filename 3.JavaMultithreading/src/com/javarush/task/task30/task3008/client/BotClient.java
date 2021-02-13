package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BotClient extends Client {
    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        int random = (int) (Math.random() * 100);
        String name = String.format("date_bot_%d", random);

        return name;
    }

    public class BotSocketThread extends SocketThread {

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (message.contains(": ")) {
                String[] strings = message.split(": ");
                String name = strings[0];
                String text = strings[1];
                DateFormat format = null;
                if (text.equals("дата")) {
                    format = new SimpleDateFormat("d.MM.YYYY");
                } else if (text.equals("день")) {
                    format = new SimpleDateFormat("d");
                } else if (text.equals("месяц")) {
                    format = new SimpleDateFormat("MMMM");
                } else if (text.equals("год")) {
                    format = new SimpleDateFormat("YYYY");
                } else if (text.equals("время")) {
                    format = new SimpleDateFormat("H:mm:ss");
                } else if (text.equals("час")) {
                    format = new SimpleDateFormat("H");
                } else if (text.equals("минуты")) {
                    format = new SimpleDateFormat("m");
                } else if (text.equals("секунды")) {
                    format = new SimpleDateFormat("s");
                }
                if (format != null) {
                    Calendar date = Calendar.getInstance();
                    sendTextMessage("Информация для " + name + ": " + format.format(date.getTime()));
                }
            }else{
                return;
            }
        }

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }
    }


    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }
}
