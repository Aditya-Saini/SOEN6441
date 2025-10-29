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

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 * 
 * @author NotiLytics Team
 */
public class HomeController extends Controller {

    private final FormFactory formFactory;
    private final HttpExecutionContext httpExecutionContext;

    /**
     * Constructor for HomeController with dependency injection.
     * 
     * @param formFactory the form factory for handling forms
     * @param httpExecutionContext the HTTP execution context for async operations
     */
    @Inject
    public HomeController(FormFactory formFactory, 
                         HttpExecutionContext httpExecutionContext) {
        this.formFactory = formFactory;
        this.httpExecutionContext = httpExecutionContext;
    }

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     * 
     * @return a CompletionStage containing the result
     */
    public CompletionStage<Result> index() {
        return CompletableFuture.supplyAsync(() -> {
            return ok(views.html.index.render("Welcome to NotiLytics!"));
        }, httpExecutionContext.current());
    }

    /**
     * Health check endpoint for monitoring the application status.
     * 
     * @return a CompletionStage containing the health status
     */
    public CompletionStage<Result> health() {
        return CompletableFuture.supplyAsync(() -> {
            return ok("NotiLytics is running!");
        }, httpExecutionContext.current());
    }
}
