package dev.juliomchn.gatewaytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayTestApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/db/**")
						.filters(f -> f
							.prefixPath("/api")
							.addResponseHeader("X-Powered-By","Mchn Gateway Service")
						)
						.uri("http://localhost:8000")
				)
				.build();
	}

}
