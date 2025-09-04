package net.framinfo.freetube.delegates;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotFoundException;
import net.framinfo.freetube.models.auth.Session;
import net.framinfo.freetube.models.auth.User;

import java.time.Instant;

@ApplicationScoped
public class SessionDelegate {

    public Uni<Session> isSessionValid(Long userId) {
        return Session.findById(userId)
                .onItem().ifNotNull().transform(it -> ((Session) it).getExpireAt().compareTo(Instant.now()) >= 0 ? (Session) it : null);

    }

    public Uni<Session> createSession(User user) {
        return (new Session(user)).persistAndFlush()
                .onItem().ifNotNull().transformToUni(ignored -> Session.findById(user.getId()));
    }

    public Uni<Session> renewSession(User user) {
        return Session.deleteById(user.getId())
                .flatMap(ignored -> this.createSession(user));
    }

    public Uni<Boolean> deleteSession(User user) {
        return Session.deleteById(user.getId());
    }

    public Uni<Session> createSession(Long userId) {
        return User.findById(userId)
                .onItem().ifNull().failWith(InternalServerErrorException::new)
                .onItem().ifNotNull().transformToUni(it -> (new Session((User) it)).persistAndFlush())
                .onItem().ifNotNull().transformToUni(ignored -> Session.findById(userId));
    }

    public Uni<Session> renewSession(Long userId) {
        return Session.deleteById(userId)
                .flatMap(ignored -> this.createSession(userId));

    }

    public Uni<Boolean> deleteSession(Long userId) {
        return Session.deleteById(userId);
    }

    public Uni<Long> deleteSessionByToken(String token) {
        return Session.delete("token = ?1", token)
                .map(it -> it == 0 ? null : it)
                .onItem().ifNull().failWith(NotFoundException::new);

    }
}
