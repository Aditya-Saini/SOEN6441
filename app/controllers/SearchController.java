package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Http;
import play.data.Form;
import play.data.FormFactory;
import play.libs.concurrent.HttpExecutionContext;

import jakarta.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.List;
import java.util.ArrayList;

/**
 * Controller for handling news search functionality.
 * 
 * @author NotiLytics Team
 */
public class SearchController extends Controller {

    private final FormFactory formFactory;
    private final HttpExecutionContext httpExecutionContext;

    /**
     * Constructor for SearchController with dependency injection.
     * 
     * @param formFactory the form factory for handling forms
     * @param httpExecutionContext the HTTP execution context for async operations
     */
    @Inject
    public SearchController(FormFactory formFactory, 
                           HttpExecutionContext httpExecutionContext) {
        this.formFactory = formFactory;
        this.httpExecutionContext = httpExecutionContext;
    }

    /**
     * Display the search page.
     * 
     * @return a CompletionStage containing the search page
     */
    public CompletionStage<Result> index() {
        return CompletableFuture.supplyAsync(() -> {
            return ok(views.html.search.render("Search News", new ArrayList<>()));
        }, httpExecutionContext.current());
    }

    /**
     * Handle search form submission.
     * 
     * @param request the HTTP request containing search parameters
     * @return a CompletionStage containing the search results
     */
    public CompletionStage<Result> search(Http.Request request) {
        return CompletableFuture.supplyAsync(() -> {
            // Get search query from form data
            String query = request.queryString("query").length > 0 ? 
                          request.queryString("query")[0] : "";
            
            // For now, return mock results
            List<String> mockResults = new ArrayList<>();
            if (!query.isEmpty()) {
                mockResults.add("Mock article 1 for: " + query);
                mockResults.add("Mock article 2 for: " + query);
                mockResults.add("Mock article 3 for: " + query);
            }
            
            return ok(views.html.search.render("Search Results for: " + query, mockResults));
        }, httpExecutionContext.current());
    }
}
