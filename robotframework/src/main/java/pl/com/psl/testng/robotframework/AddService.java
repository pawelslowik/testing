package pl.com.psl.testng.robotframework;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by psl on 25.04.17.
 */
public class AddService {

    public BigDecimal add(List<BigDecimal> values) {
        return values.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
