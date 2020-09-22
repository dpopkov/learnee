Multi module Spring Project
---------------------------

#### 70 View Item Challenge
* Create a view "view_item.jsp".
* Add a link to items list view.
* Create a method viewItem() in the TodoItemController.

#### 69 Implement Edit Item Feature
* Add links to items_list.jsp for editing.
* Modify method addItem (add a new parameter for id) to reuse it for editing existing items.
* Modify method processItem to reuse it for updating of existing items.

#### 68 Delete Item Challenge
* Add method deleteItem(int id) to the TodoItemController using @RequestParameter.
* Use service to delete the item and redirect to list of items.

#### 67 Home View and Navigation
* Create a home view.
* Implement navigation between the views.
* Make WebConfig class to implement WebMvcConfigurer interface, override method addViewControllers.
* Register in WebConfig the new home view without a controller.
* Delete index.html.

#### 66 Implementing Post Redirect Get Pattern
* Add handler method with @GetMapping to controller that prepares the model 
  and returns name of the form view.
* Add a table with input fields to the form.

#### 65 Implementing Add Item Feature
* Create jsp page with form taglib (uri="http://www.springframework.org/tags/form")
* Add handler method with @PostMapping that uses service to add the item and then redirects to items list.

#### 64 Todo Item Service Challenge
* Create an interface TodoItemService with methods addItem(TodoItem), removeItem(int),
getItem(int), updateItem(TodoItem), getData():TodoData.
* Create a class TodoItemServiceImpl and annotate it with @Service.
* Create a final field TodoData and initialize it.
* In TodoController inject/autowire TodoItemService using constructor injection.
* In the controller get todoData using service method instead of using new TodoData().
* Add another dummy item to TodoData for testing purposes.

#### 63 Creating view for items and using JSTL tags
* Add JSTL dependency to pom.xml.
* Add jsp file with a table using JSTL

#### 62 Creating the Todo Item Controller
* Add TodoItemController class.
* Add Mappings utility class containing string constants for mapping.
* Add ViewNames utility class containing string constants for view names.

#### 61 ToDoData class
* Add TodoData class to represent a data source (using in-memory db).
* Add to TodoData methods for crud operations and use Lombok @NonNull annotation.

#### 60 ToDo Item class
* Use Lombok annotations for POJO class.

#### 59 ToDoList Project Requirements
* A page for viewing all the to-do items in the table with their title and deadline.
* A page for viewing a single to-do item including its details.
* A page for updating a single to-do item.
* A page for creating a new to-do item.
* Deleting functionality that removes a single to-do item from the list.
* A home page with a welcome message and a link to the page with all the items.
* For simplicity data will be stored in memory.

#### 58 Request Parameters
* Using @RequestParam annotation for getting request parameters.

#### 57 Simple Service Challenge
* Create an interface DemoService with a method "String getHelloMessage(String user)" and
"String getWelcomeMessage()".
* Create a class DemoServiceImpl and annotate it with @Service.
* In DemoController inject/autowire DemoService using constructor injection.
* In the controller methods, use DemoService to add attributes to the model 
instead of using strings.
* Remove the user model attribute in the welcome() controller method and use another
attribute calling getHelloMessage() from DemoService.

#### 56 Model and Model Attributes
* Add Model parameter to welcome() method - "String welcome(Model)".
* Add an attribute to the model and use EL notation ${attrName} in JSP page.
* Add method with @ModelAttribute annotation to add another attribute to the Model.

#### 55 Spring MVC Request Processing

#### 54 View Resolver and View
* Add WEB-INF/view/welcome.jsp.
* Add to WebConfig a bean method returning ViewResolver.
* Add to DemoController welcome() method with @GetMapping annotation.

#### 53 SimpleController
* Add DemoController, annotate it with @Controller.
* Add method hello(), annotate it with @GetMapping("/hello") and @ResponseBody.

#### 52 Setup Dispatcher Servlet
* Add WebConfig class with annotations @Configuration, @ComponentScan, @EnableWebMvc.
* Add WebAppInitializer class which implements WebApplicationInitializer.
* Use AnnotationConfigWebApplicationContext for registering WebConfig.
* Create, register and configure the dispatcher servlet.

#### 51 Setup Maven Cargo plugin using Tomcat
* Add org.codehaus.cargo to pom.xml.
* Add this configuration for cargo: containerId=tomcat9x, type=embedded.

#### 50 Setup Maven War plugin
* Add war packaging to the project.
* Use option failOnMissingWebXml=false for Maven WAR plugin config.
* Create directory main/webapp and main/webapp/WEB-INF.
* Add index.html

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
* Fix NumberGeneratorImpl to generate numbers between the min and max.

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
* Remove context:annotation-config from the bean.xml file.
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
