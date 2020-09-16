package learn.spr.jstmlsf5.core;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public class NumberGeneratorImpl implements NumberGenerator {
    private final Random random = new Random();

    @Autowired
    @MinNumber
    private int minNumber;

    @Autowired
    @MaxNumber
    private int maxNumber;

    @Override
    public int next() {
        return minNumber + random.nextInt(maxNumber - minNumber);
    }

    @Override
    public int getMinNumber() {
        return minNumber;
    }

    @Override
    public int getMaxNumber() {
        return maxNumber;
    }
}
