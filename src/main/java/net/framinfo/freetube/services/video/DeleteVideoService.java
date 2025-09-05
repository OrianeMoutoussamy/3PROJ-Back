package net.framinfo.freetube.services.video;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class DeleteVideoService extends AbstractVideoService {

    public Uni<Response> run(String token, String id) {
        return this.checkOwnership(token, id)
                .flatMap(PanacheEntityBase::delete)
                .map(ignored -> Response.status(200).build());
    }
}
