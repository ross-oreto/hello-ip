package app;

import io.jooby.*;
import io.jooby.json.JacksonModule;

import java.util.HashMap;
import java.util.Map;

public class App extends Jooby {

  static String getIp(Context context) {
    Value ipHeader = context.header("X-Forwarded-For");
    return ipHeader.isMissing() ? context.getRemoteAddress() : ipHeader.value().split(",")[0].trim();
  }

  {
    install(new JacksonModule());

    get("/", ctx -> {
      ctx.setResponseType(MediaType.json);
      Map<String, Object> data = new HashMap<String, Object>() {{ put("ip", getIp(ctx)); }};
      ValueNode name = ctx.query("name");
      if (name.isSingle()) data.put("greeting", String.format("Greetings %s", name.value()));
      ctx.setResponseHeader("x-hello-world", "MRO");
      return data;
    });
  }

  public static void main(final String[] args) {
    runApp(args, App::new);
  }

}
