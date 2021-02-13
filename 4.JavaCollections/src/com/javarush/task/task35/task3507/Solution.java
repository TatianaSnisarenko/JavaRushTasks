package com.javarush.task.task35.task3507;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
Реализуй логику метода getAllAnimals.
Аргумент метода pathToAnimals - это абсолютный путь к директории, в которой хранятся скомпилированные классы.
Путь не обязательно содержит / в конце.
НЕ все классы наследуются от интерфейса Animal.
НЕ все классы имеют публичный конструктор без параметров.
Только для классов, которые наследуются от Animal и имеют публичный конструктор без параметров, - создать по одному объекту.
Добавить созданные объекты в результирующий сет и вернуть.
Метод main не участвует в тестировании.
*/

public class Solution {
    public static void main(String[] args) throws UnsupportedEncodingException, ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) throws ClassNotFoundException, UnsupportedEncodingException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Set<Animal> animalSet = new HashSet<>();

        String decodePathToAnimals = URLDecoder.decode(pathToAnimals, "UTF-8");
        File[] files = new File(decodePathToAnimals).listFiles();
        AnimalLoader animalLoader = new AnimalLoader();

        for(File file: files){
            Class classFromFile = animalLoader.findClass(file.getAbsolutePath());
            System.out.println("classFromFile is: " + classFromFile);
            if(Animal.class.isAssignableFrom(classFromFile)){
                System.out.println(classFromFile + "implements Animal");
                Constructor[] constructors = classFromFile.getConstructors();
                for(Constructor constructor: constructors){
                    if(constructor.getParameterCount() == 0){
                        animalSet.add((Animal) constructor.newInstance());

                    }
                }
            }
        }
        return animalSet;
    }


}
class AnimalLoader extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try{
            Path file = Paths.get(name);
            byte[] bytes = Files.readAllBytes(file);
            return defineClass(null, bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }
}
