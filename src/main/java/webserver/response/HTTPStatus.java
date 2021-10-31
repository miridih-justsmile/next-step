package webserver.response;

import lombok.Getter;

@Getter
public enum HTTPStatus {
    OK_200(200, "OK"),
    CREATED_201(201, "Created"),
    ACCEPTED_202(201, "Accepted"),
    NO_CONTENT_204(201, "No Content"),
    BAD_REQUEST_400(400, "Bad Request"),
    BAD_UNAUTHORIZED_401(401, "Bad Unauthorized"),
    FORBIDDEN_403(403, "Forbidden"),
    NOT_FOUND_404(404, "Not Found"),
    ;
    private final int status;
    private final String desc;

    HTTPStatus(final int status, final String desc) {
        this.status = status;
        this.desc = desc;
    }
}
