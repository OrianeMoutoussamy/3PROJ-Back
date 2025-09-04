package net.framinfo.freetube.services.auth;

import io.smallrye.mutiny.Uni;
import io.vertx.ext.auth.impl.jose.JWT;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import net.framinfo.freetube.delegates.SessionDelegate;
import net.framinfo.freetube.models.auth.User;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class LoginService {

    @Inject
    SessionDelegate sessionDelegate;

    public Uni<Response> run(User user) {
        return User.find("email = ?1 and password = ?2", user.getEmail(), user.getPassword()).firstResult()
                .onItem().ifNull().failWith(NotFoundException::new)
                .onItem().ifNotNull().transformToUni(it -> sessionDelegate.renewSession((User) it))
                .onItem().ifNotNull().transform(it -> Response.status(200).entity(it.getToken()).build());
    }
}
