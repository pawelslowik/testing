package pl.com.psl.testing.cucumber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by psl on 04.03.17.
 */
@Component
public class MaxValueSelectionService {

    private static Logger LOGGER = LoggerFactory.getLogger(MaxValueSelectionService.class);

    public int selectMaxValue(int value1, int value2){
        LOGGER.info("Received values1={} and value2={}", value1, value2);
        int maxValue = value1 > value2 ? value1 : value2;
        LOGGER.info("Returning max value={}", maxValue);
        return maxValue;
    }
}
