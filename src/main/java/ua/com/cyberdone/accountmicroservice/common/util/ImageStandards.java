package ua.com.cyberdone.accountmicroservice.common.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ImageStandards {
    PROFILE_IMAGE(168, 168),
    CARD_BIG_IMAGE(300, 300);

    private final int width;
    private final int height;
}
