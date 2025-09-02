package net.framinfo.freetube.services.auth;

import io.smallrye.mutiny.Uni;
import io.vertx.ext.auth.impl.jose.JWT;
import jakarta.enterprise.context.ApplicationScoped;
import net.framinfo.freetube.models.auth.User;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class LoginService {

    public Uni<Response> run(User user) {
        return Uni.createFrom().item(User.find("email = ?1 and password = ?2", user.getEmail(), user.getPassword()))
                .onItem().ifNull().failWith(NotFoundException::new)
                .onItem().ifNotNull().transform(it -> Response.status(200).entity(new JWT()).build()); //TODO generate JWT
    }
}
