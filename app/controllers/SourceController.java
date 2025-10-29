package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.libs.concurrent.HttpExecutionContext;

import jakarta.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Controller for handling news sources functionality.
 * 
 * @author NotiLytics Team
 */
public class SourceController extends Controller {

    private final HttpExecutionContext httpExecutionContext;

    /**
     * Constructor for SourceController with dependency injection.
     * 
     * @param httpExecutionContext the HTTP execution context for async operations
     */
    @Inject
    public SourceController(HttpExecutionContext httpExecutionContext) {
        this.httpExecutionContext = httpExecutionContext;
    }

    /**
     * Display the sources page with available news sources.
     * 
     * @return a CompletionStage containing the sources page
     */
    public CompletionStage<Result> index() {
        return CompletableFuture.supplyAsync(() -> {
            // Mock news sources data
            List<Map<String, String>> sources = new ArrayList<>();
            
            Map<String, String> source1 = new HashMap<>();
            source1.put("name", "BBC News");
            source1.put("category", "general");
            source1.put("country", "gb");
            source1.put("language", "en");
            sources.add(source1);
            
            Map<String, String> source2 = new HashMap<>();
            source2.put("name", "CNN");
            source2.put("category", "general");
            source2.put("country", "us");
            source2.put("language", "en");
            sources.add(source2);
            
            Map<String, String> source3 = new HashMap<>();
            source3.put("name", "Reuters");
            source3.put("category", "general");
            source3.put("country", "us");
            source3.put("language", "en");
            sources.add(source3);
            
            Map<String, String> source4 = new HashMap<>();
            source4.put("name", "ESPN");
            source4.put("category", "sports");
            source4.put("country", "us");
            source4.put("language", "en");
            sources.add(source4);
            
            Map<String, String> source5 = new HashMap<>();
            source5.put("name", "TechCrunch");
            source5.put("category", "technology");
            source5.put("country", "us");
            source5.put("language", "en");
            sources.add(source5);
            
            return ok(views.html.sources.render("News Sources", sources));
        }, httpExecutionContext.current());
    }
}
