package vn.sapo.statics;

import org.apache.commons.lang3.StringUtils;

public enum BannerPlatform {
    MOBILE,
    WEB;

    public static BannerPlatform get(String platform) {
            for (BannerPlatform bannerPlatform : BannerPlatform.values()) {
                if (bannerPlatform.name().toLowerCase().equals(platform.toLowerCase())) {
                    return bannerPlatform;
                }
            }
        return null;
    }
}
