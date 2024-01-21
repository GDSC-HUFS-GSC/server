package solution.server.global.file.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import solution.server.global.file.domain.application.ImageFileHandle;
import solution.server.infra.gcp.storage.domain.application.GCPStorageService;

@Configuration
public class FileConfig {
    private final GCPStorageService gcpStorageService;

    public FileConfig(GCPStorageService gcpStorageService) {
        this.gcpStorageService = gcpStorageService;
    }

    @Bean
    public ImageFileHandle imageFileHandle() {
            return gcpStorageService;
    }
}
