package app;

import io.jooby.MockRouter;
import io.jooby.StatusCode;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTest {
  @Test
  public void welcome() {
    MockRouter router = new MockRouter(new App());
    router.get("/?name=Ross", rsp -> {
      assertTrue(rsp.value(Map.class).containsKey("ip"));
      assertEquals(StatusCode.OK, rsp.getStatusCode());
    });
  }
}
