Multi module Spring Project
---------------------------

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
