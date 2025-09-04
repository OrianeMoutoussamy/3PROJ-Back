package net.framinfo.freetube.services.auth;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.core.Response;
import net.framinfo.freetube.delegates.SessionDelegate;
import net.framinfo.freetube.models.auth.User;
import net.framinfo.freetube.models.channel.Channel;


@ApplicationScoped
public class RegisterService {

    @Inject
    SessionDelegate sessionDelegate;

    public Uni<Response> run(User user) {
        return User.find("email = ?1", user.getEmail()).firstResult()
                .onItem().ifNotNull().failWith(InternalServerErrorException::new)
                .onItem().ifNull().continueWith(user)
                .onItem().ifNotNull().transformToUni(ignored -> user.persist())
                .onItem().ifNotNull().transformToUni(ignored -> {
                    Channel channel = new Channel();
                    channel.setUser(user);
                    channel.setUsername(user.getEmail());
                    channel.setDescription("Welcome to Freetube !");
                    return channel.persist();
                })
                .onItem().ifNotNull().transformToUni(ignored -> sessionDelegate.createSession(user))
                .onItem().ifNotNull().transform(ignored -> Response.status(200).build());
    }
}
