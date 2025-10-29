package controllers;

import org.junit.Test;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

import static org.junit.Assert.*;
import static play.test.Helpers.*;

/**
 * Unit tests for HomeController.
 * 
 * @author NotiLytics Team
 */
public class HomeControllerTest extends WithApplication {

    /**
     * Test the index action returns a successful result.
     */
    @Test
    public void testIndex() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/");

        Result result = route(app, request);
        assertEquals(OK, result.status());
        assertTrue(contentAsString(result).contains("Welcome to NotiLytics!"));
    }

    /**
     * Test the health check endpoint returns a successful result.
     */
    @Test
    public void testHealth() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/health");

        Result result = route(app, request);
        assertEquals(OK, result.status());
        assertTrue(contentAsString(result).contains("NotiLytics is running!"));
    }

    /**
     * Test that the index page contains expected content.
     */
    @Test
    public void testIndexContent() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/");

        Result result = route(app, request);
        String content = contentAsString(result);
        
        assertTrue(content.contains("NotiLytics"));
        assertTrue(content.contains("News Analytics"));
        assertTrue(content.contains("Get Started"));
    }

    /**
     * Test that the health endpoint is accessible.
     */
    @Test
    public void testHealthEndpoint() {
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri("/health");

        Result result = route(app, request);
        assertEquals(OK, result.status());
        assertEquals("text/plain", result.contentType().orElse(""));
    }
}
