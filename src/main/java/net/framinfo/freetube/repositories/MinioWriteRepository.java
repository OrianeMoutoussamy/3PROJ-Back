package net.framinfo.freetube.repositories;

import io.minio.PutObjectArgs;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import net.framinfo.freetube.models.utils.MinioFilename;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Repository to write data in MinIO
 */
@Slf4j
@ApplicationScoped
public class MinioWriteRepository extends MinioRepository {

    /**
     * Function to write data in MinIO
     *
     * @param filename    the desired filename
     * @param dataToWrite the data to write
     * @return true if successful, false otherwise
     */
    public boolean run(MinioFilename filename, String dataToWrite) {
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(filename.toString())
                    .stream(new ByteArrayInputStream(
                                    dataToWrite.getBytes(StandardCharsets.UTF_8)),
                            -1,
                            10485760)
                    .contentType(filename.getContentType())
                    .build());
            return true;
        } catch (Exception e) {
            log.error("Error when writing data to MinIO.", e);
            return false;
        }
    }

    /**
     * Function to write data in MinIO
     *
     * @param filename    the desired filename
     * @param dataToWrite the data to write
     * @param tags        tags to be added to the data in MinIO
     * @return true if successful, false otherwise
     */
    public boolean run(MinioFilename filename, String dataToWrite, Map<String, String> tags) {
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(filename.toString())
                    .stream(new ByteArrayInputStream(
                                    dataToWrite.getBytes(StandardCharsets.UTF_8)),
                            -1,
                            10485760)
                    .tags(tags)
                    .contentType(filename.getContentType())
                    .build());
            return true;
        } catch (Exception e) {
            log.error("Error when writing data to MinIO.", e);
            return false;
        }
    }

    /**
     * Function to write data in MinIO
     *
     * @param filename    the desired filename
     * @param dataToWrite the data to write
     * @return true if successful, false otherwise
     */
    public boolean run(MinioFilename filename, InputStream dataToWrite) {
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(filename.toString())
                    .stream(dataToWrite,
                            dataToWrite.available(),
                            -1)
                    .contentType(filename.getContentType())
                    .build());
            return true;
        } catch (Exception e) {
            log.error("Error when writing data to MinIO.", e);
            return false;
        }
    }

    /**
     * Function to write data in MinIO
     *
     * @param filename    the desired filename
     * @param dataToWrite the data to write
     * @param tags        tags to be added to the data in MinIO
     * @return true if successful, false otherwise
     */
    public boolean run(MinioFilename filename, InputStream dataToWrite, Map<String, String> tags) {
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(filename.toString())
                    .stream(dataToWrite,
                            dataToWrite.available(),
                            -1)
                    .tags(tags)
                    .contentType(filename.getContentType())
                    .build());
            return true;
        } catch (Exception e) {
            log.error("Error when writing data to MinIO.", e);
            return false;
        }
    }
}


