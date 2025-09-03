package net.framinfo.freetube.services.auth;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.core.Response;
import net.framinfo.freetube.models.auth.User;
import net.framinfo.freetube.models.channel.Channel;


@ApplicationScoped
public class RegisterService {

    public Uni<Response> run(User user) {
        return User.find("email = ?1", user.getEmail()).firstResult()
                .onItem().ifNotNull().failWith(InternalServerErrorException::new)
                .onItem().ifNull().continueWith(user)
                .onItem().ifNotNull().transformToUni(it -> user.persist())
                .onItem().ifNotNull().transformToUni(it -> {
                    Channel channel = new Channel();
                    channel.setUser(user);
                    channel.setUsername(user.getEmail());
                    channel.setDescription("Welcome to Freetube !");
                    return channel.persist();
                })
                .onItem().ifNotNull().transform(it -> Response.status(200).build());
    }
}
