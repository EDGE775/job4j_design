package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int count = 15;
        long longCount = 6L;
        byte byteCount = 1;
        short shortCount = 124;
        float floatCount = 4.2F;
        double doubleCount = 5.7D;
        char charVariable = 'a';
        boolean bool = true;
        LOG.info("Primitive variable 1:{}", count);
        LOG.info("Primitive variable 2:{}", longCount);
        LOG.info("Primitive variable 3:{}", byteCount);
        LOG.info("Primitive variable 4:{}", shortCount);
        LOG.info("Primitive variable 5:{}", floatCount);
        LOG.info("Primitive variable 6:{}", doubleCount);
        LOG.info("Primitive variable 7:{}", charVariable);
        LOG.info("Primitive variable 8:{}", bool);
    }
}