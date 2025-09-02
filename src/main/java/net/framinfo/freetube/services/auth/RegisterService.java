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
        return Uni.createFrom().item(User.find("email = ?1", user.getEmail()).count() == 0 ? user : null)
                .onItem().ifNull().failWith(InternalServerErrorException::new)
                .onItem().ifNotNull().transform(it -> {
                    Channel channel = new Channel();
                    channel.setUser(it);
                    channel.setUsername(it.getEmail());
                    channel.setDescription("Welcome to Freetube !");
                    it.persist();
                    channel.persist();
                    return Response.status(200).build();
                });
    }
}
