package com.jiehfut.servletinterface;

public class ServletInterface {

    /**
     * Interface Servlet
     * abstract GenericServlet implements Servlet, ServletConfig, Serializable
     * public abstract class HttpServlet extends GenericServlet
     *
     * Servlet
     *     GenericServlet
     *         HttpServlet
     *
     * ServletRequest
     *     HttpServletRequest
     * ServletReponse
     *     HttpServletReponse
     *
     */





    /**
     1.顶级的 Servlet 类
     public interface Servlet {
         初始化方法，构造完毕后，由 Tomcat 自动调用完成初始化功能的方法
         void init(ServletConfig var1) throws ServletException;

         获得 ServletConfig 对象的方法，配置信息的保存，形成一个配置对象
         ServletConfig 是一个接口，获取配置对象
         ServletConfig getServletConfig();

         接收用户请求，用于向客户端响应的方法
         void service(ServletRequest var1, ServletResponse var2) throws ServletException, IOException;

         返回 servlet 字符串形式的描述信息的方法
         String getServletInfo();

         销毁 servlet 对象的方法，servlet 在回收前，由 Tomcat 调用的销毁方法，往往用于资源的释放工作
         void destroy();
     }











     2.抽象的 GenericServlet 类
       public abstract GenericServlet implements Servlet, ServletConfig, Serializable {
          该类侧重于除了 service 方法，其他方法的处理

          private transient ServletConfig config;

          public void destroy() {}  将抽象方法重写为普通方法，在方法内部没有任何实现，称为平庸实现，之后继承的方法就不要强制重写了

          Tomcat 在调用 init 方法的时候，会读取配置信息进入一个 ServletConfig 对象，并将该对象传入到 init 方法
          public void init(ServletConfig config) throws ServletException {
              this.config = config;
              this.init();
          }

          重载的初始化方法，供我们重写的方法，就不要我们自己处理 ServletConfig
          Tomcat 在调用 init 方法的时候会传入一个 ServletConfig 对象
          public void init() throws ServletException {} 无参 init 方法

          public ServletConfig getServletConfig() {return this.config;}

          再次抽象声明
          public abstract void service(ServletRequest var1, ServletResponse var2) throws ServletException, IOException;
     }













     3.HttpServlet
     public abstract class HttpServlet extends GenericServlet {

     public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
             参数的父转子
             HttpServletRequest request = (HttpServletRequest) req;
             HttpServletResponse response = (HttpServletResponse) res;
             调用重载的 service 方法
         this.service(request, response);
     }


     // 我们在继承了 HttpServlet 类之后，我们重写的就是这个 service 方法

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();        客户端拿请求的方式：GET POST DELETE PUT ...

        根据请求方式调用对应的方法

        if (method.equals("GET")) {
            this.doGet(req, resp);
        } else if (method.equals("HEAD")) {
            this.doHead(req, resp);
        } else if (method.equals("POST")) {
            this.doPost(req, resp);
        } else if (method.equals("PUT")) {
            this.doPut(req, resp);
        } else if (method.equals("DELETE")) {
            this.doDelete(req, resp);
        } else if (method.equals("OPTIONS")) {
            this.doOptions(req, resp);
        } else if (method.equals("TRACE")) {
            this.doTrace(req, resp);
        } else {
            resp.sendError(501, errMsg);
        }


        很显然，如果自己实现的 Servlet 类没有重写 service() 方法，访问就会报 405
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String msg = lStrings.getString("http.method_get_not_supported");
            在故意响应 405，‌HTTP状态码405表示 “Method Not Allowed”，即请求中指定的方法不被允许
            表示服务器理解请求报文中指定的URL,但拒绝使用此方法执行请求
            resp.sendError(405, msg);
        }


        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String msg = lStrings.getString("http.method_post_not_supported");
            在故意响应 405，‌HTTP状态码405表示“Method Not Allowed”，即请求中指定的方法不被允许
            表示服务器理解请求报文中指定的URL,但拒绝使用此方法执行请求
            resp.sendError(405, msg);
        }














        4.接下来是自定义的 servlet 方法
        class Servlet extends HttpServlet {
            我们自己重写的 service 方法
            protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                接收用户请求信息
                做出判断
            }
        }


        很明显可以看出来，如果自己写的 servlet 方法不去重写 service 方法，那么就会去调用父类的 service 方法
        就会直接返回 405 状态码
        如果自己重写了 service 方法，就不会调用 doXXX 方法，不会返回 405 错误

    */






}
