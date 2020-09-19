Spring MVC
==========

10 steps for request processing
-------------------------------

1. The browser creates a request to a specific URL. The Dispatcher Servlet is the front controller handling all requests. 
    In other words the Dispatcher Servlet receives all requests from the browser.
2. The Dispatcher Servlet needs to identify which controller will handle the request, 
    so it uses handler mapping to find the correct controller.
3. Handler mapping returns to the specific handler method that handles the request. 
    It may be methods with @GetMapping annotations in a controller with @Controller annotation.
4. The Dispatcher Servlet calls the specific handler method. In other words a method in the controller is called.
5. The handler method returns the view name.
6. The Dispatcher Servlet now has the view name, but it has to determine the view file to use. 
    To do so, it finds the View Resolver and calls it using the logical view name.
7. The View Resolver finds the view file and returns it to the Dispatcher Servlet.
8. The Dispatcher Servlet executes the view. It makes the model available to the view.
9. The view is rendered and returns the content back to the Dispatcher Servlet.
10. The Dispatcher Servlet sends the response back to the browser.
