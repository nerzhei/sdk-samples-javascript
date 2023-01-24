package com.server.reveal;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.infragistics.reveal.engine.init.InitializeParameterBuilder;
import com.infragistics.reveal.engine.init.RevealEngineInitializer;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/")
public class RevealJerseyConfig extends ResourceConfig 
{
    public RevealJerseyConfig()
    {
        RevealEngineInitializer.initialize(new InitializeParameterBuilder()
        .setDashboardProvider(new RevealDashboardProvider())
        .build());
        
        //register all Reveal classes in JAX-RS context
        for (Class<?> clazz : RevealEngineInitializer.getClassesToRegister()) {
        	register(clazz);
        }

        //register the cors filter for debugging
        register(CorsFilter.class);  

        //register export endpoint
        register(RevealExportController.class);
    }
}