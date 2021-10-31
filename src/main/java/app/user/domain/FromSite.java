package app.user.domain;

import java.util.Arrays;

public enum FromSite {
    DEFAULT, NAVER, KAKAO, GOOGLE;

    public static FromSite findSite(final String fromSiteStr) {
        return Arrays.stream(values()).filter(data -> data.name().equalsIgnoreCase(fromSiteStr)).findFirst().orElse(DEFAULT);
    }
}
