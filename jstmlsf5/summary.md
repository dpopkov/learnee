Multi module Spring Project
---------------------------

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
