package io.nicolau.finances_gateway.routers;

import io.nicolau.finances_gateway.config.UrlProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.rewritePath;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
public class CreditRoute {

    private final UrlProperties url;

    public CreditRoute(UrlProperties url) {
        this.url = url;
    }

    @Bean
    public RouterFunction<ServerResponse> creditRequests() {
        String baseUrl = url.creditCard();

        return route("list_all_with_limit")
                .GET("/v1/credit/list/{limit}", http(baseUrl))
                .before(rewritePath("/v1/credit/list/(?<segment>.*)", "/credit/list/${segment}"))
                .build()
        .and(route("credit_card_create")
                .POST("/v1/credit/create", http(baseUrl))
                .before(rewritePath("/v1/credit/create", "/credit/create"))
                .build());
    }
}
