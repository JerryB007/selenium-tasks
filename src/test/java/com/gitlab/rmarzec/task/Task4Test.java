package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.model.YTTile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static com.gitlab.rmarzec.framework.utils.WebElementUtils.findElementSafety;
import static org.testng.Assert.assertEquals;

public class Task4Test extends BaseTest {
    private static final String YOUTUBE_PAGE_URL = "https://www.youtube.com/";
    private static final String LIVE_FLAG = "LIVE";

    @Test
    void givenYoutubePage_whenGet_thenThumbnailListOk() {
        webDriver.get(YOUTUBE_PAGE_URL);
        acceptCookieByClick(By.xpath("//yt-button-shape/button[starts-with(@aria-label,'Accept')]"));

        final String thumbnailSelector = "//div[@id='content']/ytd-rich-grid-media/div[@id='dismissible']";
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(thumbnailSelector), 12));

        final List<YTTile> result = new ArrayList<>();

        final List<WebElement> thumbnailList = webDriver.findElements(By.xpath(thumbnailSelector));
        for (int i = 0; i < 12; i++) {
            final WebElement thumbnail = thumbnailList.get(i);
            final WebElement titleAnchor = thumbnail.findElement(By.xpath("./div[@id='details']/div[@id='meta']/h3/a"));
            // final WebElement channelAnchor = thumbnail.findElement(By.xpath("./div[@id='details']/div[@id='meta']/ytd-video-meta-block/div/div/ytd-channel-name/div/div/yt-formatted-string/a"));
            final WebElement channelAnchor = thumbnail.findElement(By.tagName("ytd-channel-name")).findElement(By.xpath("./div/div/yt-formatted-string/a"));
            // final WebElement timeLengthSpan = thumbnail.findElement(By.xpath("./div[@id='thumbnail']/ytd-thumbnail/a[@id='thumbnail']/div[@id='overlays']/*[contains(name(), 'ytd')]/div[@id='time-status']/span[@id='text']"));
            final WebElement timeLengthSpan = thumbnail.findElement(By.tagName("span"));
            final WebElement badgeRendererElem = findElementSafety(thumbnail, By.xpath("./div[@id='details']/div[@id='meta']/ytd-badge-supported-renderer/div/p"));

            result.add(new YTTile(titleAnchor.getAttribute("title"),
                    channelAnchor.getAttribute("innerHTML"),
                    formatTimeLength(timeLengthSpan.getAttribute("innerHTML")),
                    badgeRendererElem != null && LIVE_FLAG.equalsIgnoreCase(badgeRendererElem.getAttribute("innerHTML")))
            );
        }

        assertEquals(12, result.size());
//        result.forEach(System.out::println);

        result.stream()
                .filter(tile -> !tile.isLive())
                .forEach(tile -> System.out.println(MessageFormat.format("tile.title: {0}; tile.length: {1}", tile.getTitle(), tile.getLength())));
    }

    private String formatTimeLength(String timeLength) {
        if (timeLength == null) return "";
        return timeLength.trim();
    }
}
