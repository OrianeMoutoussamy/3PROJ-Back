package net.framinfo.freetube.delegates;

import io.quarkus.security.UnauthorizedException;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotFoundException;
import net.framinfo.freetube.models.auth.Session;
import net.framinfo.freetube.models.auth.User;
import net.framinfo.freetube.models.channel.Channel;

import java.time.Instant;

@ApplicationScoped
public class SessionDelegate {

    public Uni<User> getUserFromToken(String token) {
        return Session.find("token = ?1", token).firstResult()
                .onItem().ifNull().failWith(UnauthorizedException::new)
                .flatMap(it -> isSessionValid((Session) it))
                .onItem().ifNull().failWith(UnauthorizedException::new)
                .onItem().ifNotNull().transform(Session::getUser);
    }

    public Uni<Channel> getChannelFromToken(String token) {
        return getUserFromToken(token)
                .onItem().ifNotNull().transformToUni(it -> Channel.find("userId = ?1", it.getId()).firstResult());
    }

    public Uni<Session> isSessionValid(Session session) {
        return Uni.createFrom().item(session.getExpireAt().compareTo(Instant.now()) >= 0 ? session : null);

    }

    public Uni<Session> createSession(User user) {
        return (new Session(user)).persistAndFlush()
                .onItem().ifNotNull().transformToUni(ignored -> Session.find("user_id = ?1", user.getId()).firstResult());
    }

    public Uni<Session> renewSession(User user) {
        return Session.delete("user_id = ?1", user.getId())
                .flatMap(ignored -> this.createSession(user));
    }

    public Uni<Long> deleteSessionByToken(String token) {
        return Session.delete("token = ?1", token)
                .map(it -> it == 0 ? null : it)
                .onItem().ifNull().failWith(NotFoundException::new);

    }
}
