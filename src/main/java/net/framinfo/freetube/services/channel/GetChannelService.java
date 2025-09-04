package net.framinfo.freetube.services.channel;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import net.framinfo.freetube.delegates.SessionDelegate;
import net.framinfo.freetube.models.channel.Channel;
import net.framinfo.freetube.models.channel.Subscription;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class GetChannelService {

    @Inject
    SessionDelegate sessionDelegate;

    public Uni<Channel> runSelf(String token) {
        return sessionDelegate.getUserFromToken(token)
                .flatMap(it -> Channel.find("user_id = ?1", it.getId()).firstResult());
    }

    public Uni<List<Channel>> getSubscriptions(String token) {
        return sessionDelegate.getUserFromToken(token)
                .flatMap(it -> Subscription.find("subscriber_id = ?1", it.getId()).list())
                .flatMap(it -> {
                    List<Uni<Channel>> toCombine = new ArrayList<>();
                    for (PanacheEntityBase sub : it) {
                        toCombine.add(Channel.findById(((Subscription) sub).getId().getChannelId()));
                    }
                    return Uni.join().all(toCombine).andCollectFailures();
                });
    }

    public Uni<Channel> run(String id) {
        return Channel.findById(id);
    }
}
