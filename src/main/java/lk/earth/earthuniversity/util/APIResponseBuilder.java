package lk.earth.earthuniversity.util;

import lk.earth.earthuniversity.model.response.APISuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;

public class APIResponseBuilder {

    public static <T> ResponseEntity<APISuccessResponse<T>> getResponse(T data, int count) {
        return ResponseEntity.ok(new APISuccessResponse<>(
                data,
                Map.of("count", count),
                Map.of("self", buildCurrentRequestUrl())
        ));
    }

    public static <T> ResponseEntity<APISuccessResponse<T>> postResponse(T data, Object id) {
        return new ResponseEntity<>(new APISuccessResponse<>(
                data,
                Map.of("status", "created"),
                Map.of("self", buildUrlWithPath("/" + id))
        ), HttpStatus.CREATED);
    }

    public static <T> ResponseEntity<APISuccessResponse<T>> putResponse(T data,Object id) {
        return new ResponseEntity<>(new APISuccessResponse<>(
                data,
                Map.of("status", "updated"),
                Map.of("self", buildCurrentRequestUrl())
        ), HttpStatus.OK);
    }

    public static <T> ResponseEntity<APISuccessResponse<T>> deleteResponse(Integer id) {
        return new ResponseEntity<>(new APISuccessResponse<>(
                null,
                Map.of("status", "deleted"),
                Map.of("self", buildCurrentRequestUrl())
        ), HttpStatus.NO_CONTENT);
    }

    private static String buildCurrentRequestUrl() {
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .toUriString();
    }

    private static String buildUrlWithPath(String pathSegment) {
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path(pathSegment)
                .toUriString();
    }
}
