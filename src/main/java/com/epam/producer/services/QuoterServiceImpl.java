package com.epam.producer.services;

import com.epam.infra.RandomUtil;
import com.epam.infra.Singleton;
import com.epam.producer.repos.QuoterRepo;

import java.util.List;

/**
 * @author Evgeny Borisov
 */
@Singleton
public class QuoterServiceImpl implements QuoterService {
    private final QuoterRepo quoterRepo;
    private List<String> allQuotes;

    public QuoterServiceImpl(QuoterRepo quoterRepo) {
        this.quoterRepo = quoterRepo;
        allQuotes = quoterRepo.getAllQuotes();

    }

    @Override
    public String getRandomQuoteText() {
        return RandomUtil.getRandomItem(quoterRepo.getAllQuotes());
    }
}
