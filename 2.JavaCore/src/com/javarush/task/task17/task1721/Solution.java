package com.javarush.task.task17.task1721;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
Сделать метод joinData транзакционным, т.е. если произошел сбой, то данные не должны быть изменены.
1. Считать с консоли 2 имени файла.
2. Считать построчно данные из файлов. Из первого файла - в allLines, из второго - в forRemoveLines.
В методе joinData:
3. Если список allLines содержит все строки из forRemoveLines, то удалить из списка allLines все строки, которые есть в forRemoveLines.
4. Если условие из п.3 не выполнено, то:
4.1. очистить allLines от данных
4.2. выбросить исключение CorruptedDataException
Метод joinData должен вызываться в main. Все исключения обработайте в методе main.
Не забудь закрыть потоки.


*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) {
        Solution solution = new Solution();

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String s1 = reader.readLine();
            String s2 = reader.readLine();
            reader.close();

            BufferedReader fileReader1 = new BufferedReader(new InputStreamReader(new FileInputStream(s1)));
            while (fileReader1.ready()) {
                allLines.add(fileReader1.readLine());
            }
            fileReader1.close();
            BufferedReader fileReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(s2)));
            while (fileReader2.ready()) {
                forRemoveLines.add(fileReader2.readLine());
            }
            fileReader2.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            solution.joinData();
        } catch (CorruptedDataException e) {
            System.out.println("Данные из листа не совпадают с ремув-листом");


        }
    }

    public void joinData() throws CorruptedDataException{


        if(allLines.containsAll(forRemoveLines)){
            allLines.removeAll(forRemoveLines);
        }else{
            allLines.clear();
            throw new CorruptedDataException();
        }

    }
}
