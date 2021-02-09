package com.RandomGeneratorGenerator.Generator;

import com.RandomGeneratorGenerator.repository.NameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;

@Component
public class RandomGenerator {

    private final NameRepository nameRepository;

    @Autowired
    public RandomGenerator(NameRepository nameRepository) {
        this.nameRepository = nameRepository;
    }

    public long[] generateNumber() {
        ArrayList<Long> namesFromDb = new ArrayList<>();
        long totalNames =  nameRepository.count();
        int i;
        long[] chosenNames = new long[10];

        Random rnd = new Random();

        for (int e = 1; e <= totalNames; e++) {
            long drawnNumber = rnd.nextInt(e);
            namesFromDb.add(drawnNumber);
        }
        for (i = 0; i < chosenNames.length; ++i) {
            chosenNames[i] = namesFromDb.get(i);
        }
        return chosenNames;
    }
}
