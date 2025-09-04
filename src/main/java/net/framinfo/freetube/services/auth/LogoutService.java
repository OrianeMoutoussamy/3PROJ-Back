package net.framinfo.freetube.services.auth;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import net.framinfo.freetube.delegates.SessionDelegate;

@ApplicationScoped
public class LogoutService {

    @Inject
    SessionDelegate sessionDelegate;

    public Uni<Response> run(String token) {
        return sessionDelegate.deleteSessionByToken(token)
                .map(ignored -> Response.status(200).build());
    }
}
