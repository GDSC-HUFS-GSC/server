package solution.server.global.file.domain.application;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class ImageFileNameUtil {

    public String cutFullFileUrlIntoNameOnly(String fileUrl) {
        int lastIndex = fileUrl.indexOf(".com/") + 5;
        if (lastIndex != -1 && lastIndex < fileUrl.length()) {
            return fileUrl.substring(lastIndex);
        }
        else{
            throw new IllegalArgumentException("fileUrl is Error");
        }
    }
    public String cutFullFileUrlIntoNameOnlyGCP(String fileUrl) {
        int lastIndex = fileUrl.indexOf(".com") + 4;
        int firstIndex = fileUrl.indexOf("?GoogleAccessId=gsc-storage@tragle");
        if (lastIndex != -1 && lastIndex < fileUrl.length()) {
            var result = fileUrl.substring(lastIndex,firstIndex);
            return result;
        }
        else{
            throw new IllegalArgumentException("fileUrl is Error");
        }
    }
    public String getLocalDateTimeMilliseconds() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return Long.toString(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }
}
