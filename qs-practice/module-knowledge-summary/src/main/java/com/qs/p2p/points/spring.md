ApplicationContext和BeanFactory区别

BeanFactory：BeanFactory是spring中底层接口，提供简单的容器功能，采用延时加载策略加载bean，习惯称BeanFactory为Ioc容器

ApplicationContext:为Spring容器，BeanFactory的子接口包含BeanFactory的所有功能，提供国际化，资源访问，多个上下文，消息发送响应，AOP等面向企业级的功能。容器启动时，所有注入的bean会随容器实例化

Spring的Ioc容器：BeanFactory，ApplicationContext，WebApplicationContext

bean的作用域

Spring中bean的作用域默认单例的（基于spring容器而言，非JVM）

Singleton：单例（默认），IoC容器中只会存在一个共享的bean实例，并且所有对bean的请求，只要id与该bean定义相匹配，则只会返回bean的同一实例

Prototype：在每次对该bean请求（将其注入到另一个bean中，或者以程序的方式调用容器的getBean()方法）时都会创建一个新的bean实例

Request：每个HTTP请求都会有各自的bean实例，每个HTTP请求都会有各自的bean实例，基于web的Spring ApplicationContext情形下有效

Session：在一个HTTP Session中，一个bean定义对应一个实例，基于web的Spring ApplicationContext情形下有效

GlobalSession：在一个全局的HTTP Session中，一个bean定义对应一个实例 仅在基于web的Spring ApplicationContext情形下有效


Spring的IoC

将对象的创建，描述对象之间的依赖关系的信息写在一个文件中，具体的实现动作转移给容器来维护（接口具体实现类的选择控制权从调用类中移除，转交给第三方进行决定，即由Spring容器通过Bean配置来进行控制，这样的话应用程序本身就不用负责依赖对象的创建和维护，而由Spring容器进行管理）

通过配置文件、注解、Java类等方式描述Bean与Bean之间的依赖关系，利用Java的反射功能实例化Bean并建立Bean与Bean之间的依赖关系

https://www.cnblogs.com/ITtangtang/p/3978349.html


Web容器初始化：

实例化web.xml中每个<listener>标签修饰的监听器实例，调用contextInitialized()方法实例化，

实例化web.xml中由<filter>修饰的过滤器，并调用每个过滤器的init()方法，

实例化web.xml中由<servlet>修饰的每个Servlet按照<load-on-startup>元素定义的顺序，实例化并调用每个servlet的init()方法


ServletContextListener（观察者模式）:监听ServletContext对象的生命周期，Servlet和Filter初始化之前和销毁之后，触发ServletContextEvent事件，给实现了servletContextListener接口的监听器发出通知

ContextLoaderListener（代理模式）：代理ContextLoader，启动Web容器时，自动装配ApplicationContext的配置信息，创建Spring的ApplicationContext，并将ApplicationContext注册到ServletContext中

DispatchServlet（模板方法模式，策略模式）：HTTP请求的中央调度处理器，extends FrameworkServlet,整合MVC容器环境，专注于自定义策略的初始化，注册到ServletContext中。

HttpServletBean（模板方法模式）：提供了HttpServlet的抽象模板，让HttpServlet初始化更方便

FrameworkServlet：整合SpringWEB Bean到Spring容器中，管理WebApplicationContext,约定Spring配置文件加载策略，提供SpringMVC容器的条件


SpringMVC处理请求流程：

用户发送请求被Spring的DispatcherServlet捕获

DispatcherServlet对请求的URL解析，得到资源标识符URI，根据URI调用HandlerMapping获取该Handler配置的所有相关对象，最后以HandlerExecutionChain对象的形式返回

DispatcherServlet 根据获得的Handler选择一个合适的HandlerAdapter，提取Request中的模型数据，填充Handler入参，开始执行Handler

Handler执行完成后，向DispatcherServlet 返回一个ModelAndView对象

根据返回的ModelAndView，选择一个适合的ViewResolver（必须是已经注册到Spring容器中的ViewResolver)返回给DispatcherServlet 

ViewResolver 结合Model和View，来渲染视图将渲染结果返回给客户端


填充入参时：

HttpMessageConverter： 将请求消息（如Json、xml等数据）转换成一个对象，将对象转换为指定的响应信息

数据转换：对请求消息进行数据转换。如String转换成Integer、Double等

数据根式化：对请求消息进行数据格式化。 如将字符串转换成格式化数字或格式化日期等

数据验证： 验证数据的有效性（长度、格式等），验证结果存储到BindingResult或Error中


Spring只使用一个Servlet(DispatcherServlet)来处理所有请求----详细见J2EE设计模式-前端控制模式

Spring结合使用HandlerMapping以及HandlerAdapter来处理Handler----符合面向对象中的单一职责原则，代码架构清晰，便于维护，最重要的是代码可复用性高。如HandlerAdapter可能会被用于处理多种Handler。


动态代理：程序运行期间，在内存中动态生成代理类的字节码，并实例化代理对象

JDK动态代理：目标类必须实现的某个接口，如果某个类没有实现接口则不能生成代理对象

CGlib动态代理：针对目标类生成一个子类，覆盖其中的所有方法，所以目标类和方法不能声明为final类型

执行效率：Cglib动态代理效率较高


Spring AOP

Spring AOP依赖IOC容器，代理的生成，管理及其依赖关系都是由IOC容器负责，Spring默认使用JDK动态代理，在需要代理类而不是代理接口的时候，Spring会自动切换为使用CGLIB代理


Spring 事务

声明式事务：有助于用户将操作与事务规则进行解耦，使用AOP支持声明式事务，根据事务属性，在方法调用之前决定是否开启一个事务，在方法执行之后决定是否提交事务或者回滚事务

编程式事务：允许用户在代码中精确定义事务的边界

编程式和声明式事务的区别：编程式事务侵入到了业务代码里面，但是提供了更加详细的事务管理；而声明式事务由于基于AOP，所以既能起到事务管理的作用，又可以不影响业务代码的具体实现

事务传播机制：

PROPAGATION_REQUIRED（默认，要求的）：如果存在一个事务，则支持当前事务。如果没有事务则开启一个新的事务

PROPAGATION_SUPPORTS（支持）：如果存在一个事务，支持当前事务。如果没有事务，则非事务的执行

PROPAGATION_MANDATORY（强制）：如果已经存在一个事务，支持当前事务。如果没有一个活动的事务，则抛出异常

PROPAGATION_REQUIRED_NEW（要求new）: PROPAGATION_REQUIRES_NEW 启动一个新的, 不依赖于环境的 “内部” 事务. 这个事务将被完全 commited 或 rolled back 而不依赖于外部事务, 它拥有自己的隔离范围, 自己的锁, 等等. 当内部事务开始执行时, 外部事务将被挂起, 内务事务结束时, 外部事务将继续执行

PROPAGATION_NOT_SUPPORTED（可运行不支持）：总是非事务地执行，并挂起任何存在的事务

PROPAGATION_NEVER（永远禁止）：总是非事务地执行，如果存在一个活动事务，则抛出异常

PROPAGATION_NESTED（嵌套）:开始一个 “嵌套的” 事务, 它是已经存在事务的一个真正的子事务. 潜套事务开始执行时, 它将取得一个 savepoint. 如果嵌套事务失败, 则将回滚到savepoint. 嵌套事务是外部事务的一部分, 只有外部事务结束后它才会被提交，外层事务的回滚可以引起内层事务的回滚。而内层事务的异常并不会导致外层事务的回滚，是一个真正的嵌套事务

回滚规则：默认情况下，事务只有遇到运行期异常时才会回滚，而在遇到检查型异常时不会回滚

编程式事务：TransactionTemplate（线程安全的）中使用TransactionCallback类的doTransaction()做回调，把应用程序从处理取得和释放资源中解脱出来

声明式事务：xml配置


Spring 的JDK动态代理问题：只有代理对象proxy直接调用的那个方法才是真正的走代理


Spring中使用到的设计模式：




