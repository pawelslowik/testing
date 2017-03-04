package pl.com.psl.testing.cucumber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by psl on 03.03.17.
 */
@Component
public class RequestProcessingService {

    private static Logger LOGGER = LoggerFactory.getLogger(RequestProcessingService.class);

    public String processRequest(String request){
        LOGGER.info("Received request={}", request);
        if(request == null || request.trim().isEmpty()){
            LOGGER.info("Returning invalid response");
            return "invalid response";
        }
        if("failure request".equals(request)){
            LOGGER.info("Returning failure response");
            return "failure response";
        }
        LOGGER.info("Returning success response");
        return "success response";
    }
}
