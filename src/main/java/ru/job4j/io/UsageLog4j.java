package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Kadavr";
        byte age = 33;
        short pocketMoney = 12543;
        char sex = 'M';
        int savings = 874859;
        long antCount = 94596946947L;
        float height = 1.66F;
        double weight = 77.7777D;
        boolean isMarried = false;
        LOG.debug("User info name : {}, age : {}", name, age);
        LOG.error("Sex : {}, marital status : {}", sex, isMarried);
        LOG.warn("Height : {}, weight : {}", height, weight);
        LOG.info("Savings : {}, pocket money: {}", savings, pocketMoney);
        LOG.info("Ants number : {}", antCount);
    }
}