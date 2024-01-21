package solution.server.infra.gcp.storage.config;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

@Configuration
public class GCPStorageConfig {

    @Value("${cloud.gcp.storage.project-id}")
    private String gcpProjectId;
    @Bean
    public Storage gcpStorage() throws IOException {
        String keyFileName = "tragle-gcp-info.json";
        InputStream keyFile = ResourceUtils.getURL("classpath:" + keyFileName).openStream();
        Credentials credentials = GoogleCredentials.fromStream(keyFile);
        StorageOptions storageOptions = StorageOptions.newBuilder()
                .setCredentials(credentials)
                .setProjectId(gcpProjectId)
                .build();
        return storageOptions.getService();
    }
}
