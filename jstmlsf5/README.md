jstmlsf5 - Java Spring Tutorial Masterclass - Learn Spring Framework 5
======================================================================

[More detailed Summary](summary.md)

New Spring 5
------------

### 4 - Spring MVC
* Using Maven WAR and Maven Cargo Plugins with Tomcat.
* Setup Dispatcher Servlet.
* Using @Controller, @GetMapping, @PostMapping and @ResponseBody for a simple controller.
* Use ViewResolver for mapping a view name to a real jsp file.
* Using Model and @ModelAttribute annotation.
* Using @Service annotation.
* Using @RequestParam annotation.
* Using Spring forms.

### 3 - Using Lombok

### 2 - Using a Spring Container
* Using XML configuration to instantiate NumberGenerator.
* Constructor based Dependency Injection.
* Setter based Dependency Injection.
* Using Bean Lifecycle Callbacks
* Autowiring Beans.
* Use @Component annotations and context:component-scan.
* Using Java Annotation Configuration.
* Using bean methods.
* Application Events.
* Using Qualifiers.
* Using Properties.

### 1 - Logging with SLF4J & Logback

* Logging is keeping a record of specific information from a program's execution.
* Logging uses multiple levels, namely TRACE, DEBUG, INFO, WARN, ERROR which controls. 
the granularity, severity and verbosity of information.

#### Logback Initialization Steps

1. Logback tries o find a file called logback-test.xml in the classpath.
2. If no such file is found, logback tries to find a file called logback.groovy in the classpath.
3. If no such file is found, it checks for the file logback.xml in the classpath.
4. If no such file is found, service-provider loading facility is used to resolve the
implementation of a Configurator interface.
5. If none of the above succeeds, logback configures itself automatically using the BasicConfigurator
which will cause logging output to be directed to the console.

Old
---