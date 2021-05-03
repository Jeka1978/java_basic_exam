package com.epam.infra;

/**
 * @author Evgeny Borisov
 */
public class PersonService {

    PersonRepo repo;
    PersonAssist assist;
    @InjectByType
    PersonHolder holder;

    @InjectRandomInt(min = 2,max = 19)
    private int stamId;

    private PersonManager personManager;

    public PersonService(PersonRepo repo, PersonAssist assist) {
        this.repo = repo;
        this.assist = assist;
    }
}
