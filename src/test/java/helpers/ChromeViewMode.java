package helpers;

import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class ChromeViewMode {


    public static ChromeOptions getMobileOptions(ViewMode mode) {
        ChromeOptions options = new ChromeOptions();
        if(mode == ViewMode.MOBILE) {
            Map<String, Object> deviceMetrics = new HashMap<>();
            deviceMetrics.put("width", 375);
            deviceMetrics.put("height", 812);
            deviceMetrics.put("pixelRatio", 3.0);

            Map<String, Object> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceMetrics", deviceMetrics);
            mobileEmulation.put("userAgent",
                    "Mozilla/5.0 (iPhone; CPU iPhone OS 15_2 like Mac OS X) " +
                            "AppleWebKit/605.1.15 (KHTML, like Gecko) Version/15.2 Mobile/15E148 Safari/604.1");
            options.setExperimentalOption("mobileEmulation", mobileEmulation);
        }else {
            options.addArguments("start-maximized");
        }
        return options;
    }
}
