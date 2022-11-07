package endpoints;

import java.util.Random;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import lombok.extern.slf4j.Slf4j;
import producer.ProducerService;

@Path("configure")
@Produces("application/json")
@Consumes("application/json")
@Slf4j
public class ConfigurationEndpoint {
  @Inject
  ProducerService producerService;

  @Path("sentPayload")
  @POST
  public void configureNumberPayloadSize(Integer payloadSize) {
    producerService.sentPayload();
  }
}
