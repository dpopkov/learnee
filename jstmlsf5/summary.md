Multi module Spring Project
---------------------------

#### 48 Start ToDo List application
* Add dependency for spring-webmvc.

#### 43, 44, 45 Lombok Introduction, Setup, Using
* Use @Slf4j annotation and remove declaration of Logger.
* Use @Getter and @Setter annotations with classes and fields.
* Use @Getter(AccessLevel.NONE) for fields that should not have getters.

#### 41 Code Cleanup and Constructor injection
* Use constructor injection since that is the recommended way.
* Switch to component annotation instead of bean methods.

#### 40 minNumber Challenge
* Create a custom qualifier @MinNumber.
* Use game.properties to specify the value for minNumber.
* Create a bean method to create the bean minNumber.
* Inject minNumber to NumberGeneratorImpl, add a getter to NumberGenerator.
* Fix NumberGeneratorImpl to generate numbers between min and max.

#### 39 Using Properties
* How to use the @PropertySource annotation.
* How to use the @Value annotation and default values.
* How to inject values from a properties file.

#### 37 Using Qualifiers
* Have all the configuration in one place - create class GameConfig and import it into AppConfig.
* Use bean for initializing maxNumber in NumberGeneratorImpl.
* Create custom annotation Qualifiers.

#### 36 Finish Game Logic
* Implement ConsoleNumberGuess

#### 35 Application Events
* Use implementation of ApplicationListener interface
* Use @EventListener annotation and method with parameter
* Use @EventListener annotation with parameter

#### 34 Console Module Setup Challenge
* Create a new maven module in the project with the name console.
* Add the core project as a dependency.
* Move the Main class to the console subproject and run the Main class from the console subproject.

#### 32 Message Generator Challenge
* Add MessageGenerator interface and class MessageGeneratorImpl.
* Create a bean method in AppConfig creating a MessageGenerator.
* Get the MessageGenerator in main() and call its methods.

#### 31 - Using Java Annotation Configuration
* Delete beans.xml.
* Add class AppConfig with @Configuration and @ComponentScan annotations.
* Use AnnotationConfigApplicationContext instead of ClassPathXmlApplicationContext.
* Then remove @Component annotations and add bean methods with @Bean annotations to AppConfig.

#### 30 - Beans as Components
* Remove context:annotation-config from bean.xml
* Add context:component-scan to bean.xml to auto scan a package for components.
* Remove bean entries in bean.xml.
* Add @Component annotation to GameImpl and NumberGeneratorImpl.
* Use context.getBean(Class) method to get bean instance.

#### 29 - Autowiring Beans
* Add context:annotation-config to bean.xml.
* Use @Autowired at numberGenerator field.
* Remove setter method for autowired field.

#### 27 - Using Bean Lifecycle Callbacks
* Use init-method attribute to initialize Game.
* Use Annotation API, PostConstruct/PreDestroy annotations and CommonAnnotationBeanPostProcessor.
 
#### 25 - Setter based Dependency Injection
* Use property for bean in beans.xml to set dependency.

#### 24 - Constructor based Dependency Injection
* Use constructor-arg in beans.xml to initialize Game instance.

#### 23 - Implementing the Game
* Create interface Game and class GameImpl.

#### 22 - Using a Spring Container
* Create beans.xml in resources folder.
* Create the interface NumberGenerator and class NumberGeneratorImpl.
* Add a bean for NumberGeneratorImpl to beans.xml.
* Use ClassPathXmlApplicationContext to create context and get bean.
* Use "{}" for parameters in logging which is better practice than using concatenation. 

#### 21 - Project Setup
* Use dependencyManagement in root pom.xml.
* Use Maven properties for versions.
* Add spring-context dependency to pom.xml.
