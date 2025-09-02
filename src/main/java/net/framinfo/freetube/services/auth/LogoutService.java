package net.framinfo.freetube.services.auth;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class LogoutService {

    public Uni<Response> run() {
        return Uni.createFrom().item(Response.status(200).build()); //TODO handle JWT
    }
}
