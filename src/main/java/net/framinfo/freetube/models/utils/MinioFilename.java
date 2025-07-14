package net.framinfo.freetube.models.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.LinkedList;

@Getter @Setter
public class MinioFilename {

    /**
     * Desired filename
     */
    private String filename = "tmp";

    private String componentUuid = "";

    /**
     * Desired folder (do not include slashes)
     * Example: ["myfolder", "mysubfolder"]
     */
    private LinkedList<String> folder = new LinkedList<>();

    /**
     * Desired extension (do not include dots)
     * Example: parquet
     */
    private String extension = "";

    /**
     * Defines the content type for this file
     * Example: text/csv
     */
    private String contentType = "application/json";

    public MinioFilename() {}

    /**
     * Create a new filename
     * @param filename must not be blank
     * @param folder leave blank if not desired
     * @param extension leave blank if not desired
     */
    public MinioFilename(String filename, String extension, String... folder) {
        this.filename = filename;
        this.extension = extension;
        if (folder.length > 0) this.folder.addAll(Arrays.asList(folder));
    }

    /**
     * Generate filename from string.
     * ONLY WORKS FOR MAXIMUM ONE FOLDER.
     */
    public static MinioFilename fromString(String filename) {
        String folder = "";
        String extension = "";
        String fn = filename.split("/")[1].split("\\.")[0];
        if (filename.contains("/")) {
            folder = filename.split("/")[0];
        }
        if (filename.contains(".")) {
            extension = filename.split("\\.")[1];
        }
        return new MinioFilename(fn, folder, extension);
    }

    @Override
    public String toString() {
        StringBuffer toReturn = new StringBuffer();
        folder.forEach(folstr -> toReturn.append(folstr).append("/"));
        toReturn.append(filename);
        if(!componentUuid.isBlank()) toReturn.append("_").append(componentUuid);
        if (!extension.isBlank()) toReturn.append(".").append(extension);
        return toReturn.toString();
    }

    public void addFolder(String folder) {
        this.folder.add(folder);
    }
}
