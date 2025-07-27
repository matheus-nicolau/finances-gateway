package io.nicolau.finances_gateway.routers;

import io.nicolau.finances_gateway.config.UrlProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.rewritePath;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
public class CoreRoute {

    private final UrlProperties url;

    public CoreRoute(UrlProperties url) {
        this.url = url;
    }

    @Bean
    public RouterFunction<ServerResponse> coreClientRequests() {
        String baseUrl = url.coreClient();
        return route("list_all_clients")
                                .GET("/list", http(baseUrl))
                                .before(rewritePath("/list", "/clients/list"))
                                .build();
    }

}
