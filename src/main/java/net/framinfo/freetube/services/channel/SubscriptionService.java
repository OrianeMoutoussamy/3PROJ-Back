package net.framinfo.freetube.services.channel;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import net.framinfo.freetube.delegates.SessionDelegate;
import net.framinfo.freetube.models.channel.Subscription;
import net.framinfo.freetube.models.channel.SubscriptionId;

@ApplicationScoped
public class SubscriptionService {

    @Inject
    SessionDelegate sessionDelegate;

    public Uni<Response> subscribe(String token, String channelId) {
        return sessionDelegate.getChannelFromToken(token)
                .flatMap(it -> {
                    SubscriptionId subscriptionId = new SubscriptionId(it.getId(), Long.parseLong(channelId));
                    Subscription subscription = new Subscription();
                    subscription.setId(subscriptionId);
                    return subscription.persistAndFlush();
                })
                .onItem().ifNotNull().transform(it -> Response.status(200).build());
    }

    public Uni<Response> unsubscribe(String token, String channelId) {
        return sessionDelegate.getChannelFromToken(token)
                .flatMap(it -> Subscription.delete("id = ?1", new SubscriptionId(it.getId(), Long.parseLong(channelId))))
                .onItem().ifNotNull().transform(it -> Response.status(200).build());
    }
}
