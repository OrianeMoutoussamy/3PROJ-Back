package net.framinfo.freetube.delegates;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

@ApplicationScoped
public class JWTDelegate {

    @Inject
    JsonWebToken jwt;

    public String generateToken(String username) {
        return Jwt.issuer("https://freetube.io")
                .upn(username)
                .claim(Claims.auth_time, System.currentTimeMillis())
                .sign();
    }

    public boolean hasValidJwt() {
        return jwt.getClaimNames() != null;
    }
}
