package f4.apigateway.filter;

import f4.apigateway.constant.OpenApiEndpoints;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class RouteValidator {
  public Predicate<ServerHttpRequest> isSecured =
      request ->
          OpenApiEndpoints.allPaths().stream()
              .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
