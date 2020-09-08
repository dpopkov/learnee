jstmlsf5 - Java Spring Tutorial Masterclass - Learn Spring Framework 5
======================================================================

New Spring 5
------------

### Logging with SLF4J & Logback

* Logging is keeping a record of specific information from a programs execution
* Logging uses multiple levels, namely TRACE, DEBUG, INFO, WARN, ERROR which controls 
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