package com.javarush.task.task37.task3713.space.crew;

public abstract class AbstractCrewMember {
    public enum CompetencyLevel {
        NOVICE,
        INTERMEDIATE,
        ADVANCED,
        EXPERT
    }

    protected CompetencyLevel competencyLevel;

    protected AbstractCrewMember nextCrewMember;

    public void setNextCrewMember(AbstractCrewMember nextCrewMember) {
        this.nextCrewMember = nextCrewMember;
    }
//Попробуйте без цикла while, он тут не сильно нужен.
//1. Проверяем входящий competencyLevel  == competencyLevel объекта, если да - doTheJob.
//2. Иначе вызываем метод handleRequest у поля nextCrewMember текущего объекта

    public void handleRequest(CompetencyLevel competencyLevel, String request) {
        if (competencyLevel == this.competencyLevel) {
            doTheJob(request);
        } else if (nextCrewMember != null) {
            nextCrewMember.handleRequest(competencyLevel, request);
        }
    }

    protected abstract void doTheJob(String request);
}
