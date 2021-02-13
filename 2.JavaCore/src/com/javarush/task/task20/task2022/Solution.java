package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/
public class Solution implements Serializable, AutoCloseable {
    private transient FileOutputStream stream;
    private String fileName;

    public Solution(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        this.stream = new FileOutputStream(fileName);
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.stream = new FileOutputStream(fileName, true);
    }

    @Override
    public void close() throws Exception {

        System.out.println("Closing everything!");
        stream.close();
    }

 /*   Написать код проверки самостоятельно в методе main:
            1) создать экземпляр класса Solution
2) записать в него данные - writeObject
3) сериализовать класс Solution - writeObject(ObjectOutputStream out)
4) десериализовать, получаем новый объект
5) записать в новый объект данные - writeObject
6) проверить, что в файле есть данные из п.2 и п.5

*/

    public static void main(String[] args) {

        try {
            Solution solution = new Solution("D:\\data.txt");

            solution.writeObject("something");


            FileOutputStream outputStream = new FileOutputStream("D:\\result.txt");
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(solution);



            FileInputStream fis = new FileInputStream("D:\\result.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Solution loadedSolution = (Solution) ois.readObject();



            loadedSolution.writeObject("more writing");
            System.out.println(loadedSolution.fileName);

            oos.close();
            ois.close();
            solution.close();
            loadedSolution.close();



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
