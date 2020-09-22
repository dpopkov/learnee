Customer Support Project
========================

### 11 - Using Logging to Monitor Your Application
* Add dependencies for log4j2 to pom.xml
* Add configuration file log4j2.xml.
* Add LoggingFilter to the beginning of the filter chain in the Configurator
  so that all requests are fish tagged.
* Remove all uses of System.out, System.err, and printStackTrace()
  and replace with Log4j2 logging.

### 10 - Add Chat using WebSockets
* Using Encoders and Decoders to Translate Messages
* Creating the Chat Server Endpoint
* Writing the JavaScript Chat app

### 9 - Improve the application using Filters
* Simplify Authentication with a Filter

### 8 - Writing Custom Tag and Function Libraries
* Creating an EL Function to format time intervals
* Replace all Java code with custom JSTL Tags

### 7 - Using the Java Standard Tag Library (JSTL)
* Replacing Java code with JSTL (login.jsp, listTickets.jsp, viewTicket.jsp)

### 6 - Using the Expression Language in JSPs
* Replacing Java code with EL

### 5 - Maintaining State Using Sessions
* Setting Up the User Database for the simplest login capability
* Add LoginServlet and login.jsp
* Detecting changes to Sessions and maintaining a list of active Sessions

### 4 - Using JSP to Display Content
* Configuring JSP properties in the deployment descriptor
* Forwarding a request from a servlet to a JSP

### 3 - Using Servlets
* Using parameters and accepting form submissions
* Configuring a servlet for file uploads