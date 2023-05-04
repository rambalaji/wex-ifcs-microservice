package com.wexinc.wexifcsmicroservice.route;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class IFCSEmbossCardRoute extends RouteBuilder {
    @Autowired
    private CamelContext camelContext;

    public void configure() throws Exception {

        restConfiguration().component("http").bindingMode(RestBindingMode.json);

        onException(HttpOperationFailedException.class)
                .handled(true)
                .process("httpExceptionHandler");



       // from("direct:emboss")
         //       .setHeader("Content-Type", constant(MediaType.APPLICATION_JSON))
           //     .toD("rest:post:?host=http://10.29.83.86:8082/embossCard/invokeEmbossCard");
    }

}
