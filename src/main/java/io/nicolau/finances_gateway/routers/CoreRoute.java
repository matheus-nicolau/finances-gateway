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

        return route("list_one_client")
                                .GET("/v1/list/{email}", http(baseUrl))
                                .before(rewritePath("/v1/list/(?<segment>.*)", "/clients/${segment}"))
                                .build()
                .and(route("list_all_clients")
                                .GET("/v1/list", http(baseUrl))
                                .before(rewritePath("/v1/list", "/clients/list"))
                                .build())
                .and(route("create_client")
                                .POST("/v1/create", http(baseUrl))
                                .before(rewritePath("/v1/create", "/clients/create"))
                                .build())
                .and(route("remove_client")
                                .DELETE("/v1/remove/{email}", http(baseUrl))
                                .before(rewritePath("/v1/remove/(?<segment>.*)",
                                                                             "/clients/remove/${segment}"))
                                .build());

    }

}
