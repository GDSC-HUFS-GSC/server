package solution.server.infra.gcp.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import solution.server.global.common.dto.ApiResponse;

@RestController
@RequestMapping("/v1/test")
@AllArgsConstructor
public class ConnectionCheckApi {

//    @GetMapping(value="/{testNumber}")
//    @ResponseStatus(HttpStatus.OK)
//    public ResponseEntity<Long> getConnectTestNumber(@PathVariable Long testNumber){
//        return ResponseEntity.ok(testNumber);
//    }
    @GetMapping(value="/{testNumber}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<Long> getConnectTestNumber(@PathVariable Long testNumber){
        return ApiResponse.success(testNumber);
    }
}
