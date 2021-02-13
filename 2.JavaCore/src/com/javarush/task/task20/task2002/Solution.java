package com.javarush.task.task20.task2002;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File yourFile = File.createTempFile("your_file_name", null);
            OutputStream outputStream = new FileOutputStream("D:\\data.txt");
            InputStream inputStream = new FileInputStream("D:\\data.txt");

            JavaRush javaRush = new JavaRush();
            User user1 = new User();
            user1.setFirstName("Bdfyjd");
            user1.setLastName("Bdfy");
            Calendar birthday1 = new GregorianCalendar(2012, 2,25);
            user1.setBirthDate(birthday1.getTime());
            user1.setMale(true);
            user1.setCountry(User.Country.UKRAINE);
            javaRush.users.add(user1);
            User user2 = new User();
            user2.setFirstName("Annnaksjf");
            user2.setLastName("Ann");
            Calendar birthday2 = new GregorianCalendar(2012, 2,25);
            user2.setBirthDate(birthday2.getTime());
            user2.setMale(false);
            user2.setCountry(User.Country.UKRAINE);
            javaRush.users.add(user2);



            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //here check that the javaRush object is equal to the loadedObject object - проверьте тут, что javaRush и loadedObject равны
            System.out.println(javaRush.equals(loadedObject));

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            PrintWriter writer = new PrintWriter(outputStream);
            String isUsersFull = users.size() != 0 ? "yes" : "no";
            writer.println(isUsersFull);
            writer.flush();
            if(users.size() != 0){
                for (int i = 0; i < users.size(); i++) {
                    writer.println(users.get(i).getFirstName());
                    writer.println((users.get(i).getLastName()));
                    Date birthDate = users.get(i).getBirthDate();
                    writer.println((birthDate.getTime()));
                    String isMale = "false";
                    if(users.get(i).isMale()) {
                        isMale = "true";
                    }
                    writer.println((isMale));
                    writer.println((users.get(i).getCountry().getDisplayName()));

                }
                writer.flush();
            }
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String isUsersFull = reader.readLine();
            if(isUsersFull.equals("yes")){
                while (reader.ready()){
                    String firstName = reader.readLine();
                    String lastName = reader.readLine();
                    String date = reader.readLine();

                    Date birthDate = new Date(Long.parseLong(date));
                    String SisMale = reader.readLine();
                    boolean isMale = false;
                    if(SisMale.equals("true")){
                        isMale = true;
                    }
                    String countryName = reader.readLine();
                    User.Country country = User.Country.OTHER;
                    if(countryName.equals("Ukraine")){
                        country = User.Country.UKRAINE;
                    }else if(countryName.equals("Russia")){
                        country = User.Country.RUSSIA;
                    }
                    User user = new User();
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setBirthDate(birthDate);
                    user.setMale(isMale);
                    user.setCountry(country);
                    users.add(user);
                }
            }

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
