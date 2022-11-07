package producer;

import dto.NumberPayload;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

@ApplicationScoped
public class ProducerService {

  @Inject
  Logger logger;

  @Inject
  @Channel("numbers-payload")
  Emitter<NumberPayload> payloadEmitter;

  private final Random random = new Random();
  private NumberPayload payload;

  private NumberPayload generatePayload() {
    logger.info("Generating payload...");
    NumberPayload numberPayload = new NumberPayload();
    List<Double> numbersList = new ArrayList<>();

    for (int i = 0; i < 10; i++) {
      numbersList.add(0 + random.nextDouble() * 1000000);
    }

    numberPayload.setNumbersList(numbersList);
    Date date = new Date();
    numberPayload.setTimestamp(new Timestamp(date.getTime()));
    return numberPayload;
  }

  public void sentPayload() {
    logger.info("Sending message...");
    payload = generatePayload();
    payloadEmitter.send(payload);
    logger.info("Message sent!");
  }
}
