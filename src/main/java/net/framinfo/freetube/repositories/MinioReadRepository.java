package net.framinfo.freetube.repositories;

import io.minio.GetObjectArgs;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import net.framinfo.freetube.models.MinioFilename;

import java.io.InputStream;

/**
 * Repository to read data from MinIO
 */
@Slf4j
@ApplicationScoped
public class MinioReadRepository extends MinioRepository {

    /**
     * Reads a set filename from MinIO.
     * @param filename the desired filename
     * @return the string value if the filename exists, null otherwise
     */
    public String run(MinioFilename filename) {
        try (InputStream is = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(filename.toString())
                        .build())
        ) {
            return new String(is.readAllBytes());
        } catch (Exception e) {
            log.error("Error when reading data from MinIO.", e);
            return null;
        }
    }

    /**
     * Reads a set filename from MinIO.
     * @param filename the desired filename
     * @return the string value if the filename exists, null otherwise
     */
    public InputStream runAsInputStream(MinioFilename filename) {
        try (InputStream is = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(filename.toString())
                        .build())
        ) {
            return is;
        } catch (Exception e) {
            log.error("Error when reading data from MinIO.", e);
            return null;
        }
    }
}

