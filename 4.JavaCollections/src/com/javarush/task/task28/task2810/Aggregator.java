package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.*;
import com.javarush.task.task28.task2810.view.HtmlView;
import com.javarush.task.task28.task2810.view.View;

public class Aggregator {
    public static void main(String[] args) {
        Provider[] providers = {new Provider(new HHStrategy()), new Provider(new HabrCareerStrategy())};
        HtmlView view = new HtmlView();
        Model model = new Model(view, providers);
        Controller controller = new Controller(model);
        view.setController(controller);
        view.userCitySelectEmulationMethod();

    }
}
