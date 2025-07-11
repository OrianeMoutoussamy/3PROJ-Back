package net.framinfo.freetube.repositories;

import io.minio.MinioClient;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * Abstract repository for MinIO
 */
public abstract class MinioRepository {

    @ConfigProperty(name = "minio.video.bucket")
    protected String bucketName;

    @Inject
    MinioClient minioClient;
}
