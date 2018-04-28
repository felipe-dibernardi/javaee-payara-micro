package br.com.fthiago.condominio.filter;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

/**
 * Classe que implementa o filtro para Cross-Origin Resource Sharing (CORS) para o WebService.
 * @author Felipe Di Bernardi S Thiago
 */
@Provider
public class CORSResponseFilter implements ContainerResponseFilter {

    @Override
    public final void filter(final ContainerRequestContext requestContext, 
            final ContainerResponseContext responseContext)
            throws IOException {
        
        MultivaluedMap<String, Object> headers = responseContext.getHeaders();

        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
        headers.add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Codingpedia, authorization, "
                + "Origin");
    }

}
