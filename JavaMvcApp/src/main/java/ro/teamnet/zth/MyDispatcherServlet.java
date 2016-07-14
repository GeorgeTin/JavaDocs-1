package ro.teamnet.zth;

import oracle.jdbc.proxy.annotation.Methods;
import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.appl.controller.DepartmentController;
import ro.teamnet.zth.appl.controller.EmployeeController;
import ro.teamnet.zth.fmk.AnnotationScanUtils;
import ro.teamnet.zth.fmk.MethodAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by Aimandis on 7/14/2016.
 */
public class MyDispatcherServlet extends HttpServlet {

    //key: registru
    //val: informatii despre metoda care va procesa acest url
    HashMap<String, MethodAttributes> allowedMethods = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        //instructiuni de delegare
        dispatchReply("GET", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        //instructiuni de delegare
    }

    public void dispatchReply(String method, HttpServletRequest req, HttpServletResponse resp) {
        Object r = null;
        try {
            r = dispatch(resp, req);
        } catch (Exception e) {
            sendExceptionError(e, req, resp);
        }
        try {
            reply(r, resp, req);
        } catch (IOException e) {
            sendExceptionError(e, req, resp);
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws ServletException {
        //super.init();
        try {
            Iterable<Class> controllers = AnnotationScanUtils.getClasses("ro.teamnet.zth.appl.controller");
            for (Class controller : controllers) {
                if (controller.isAnnotationPresent(MyController.class)) {
                    MyController myCntrlAnnotation =
                            (MyController) controller.getAnnotation(MyController.class);
                    String controllerUrlPath = myCntrlAnnotation.urlPath();
                    Method[] controllerMethods = controller.getMethods();
                    for (Method controllerMethod : controllerMethods) {
                        if (controllerMethod.isAnnotationPresent(MyRequestMethod.class)) {
                            MyRequestMethod myRequestMethod =
                                    controllerMethod.getAnnotation(MyRequestMethod.class);
                            String methodUrlPath = myRequestMethod.urlPath();
                            //construiesc cheia pentru hashmap
                            String urlPath = controllerUrlPath + methodUrlPath;

                            MethodAttributes methodAttributes = new MethodAttributes();
                            methodAttributes.setControllerClass(controller.getName());
                            methodAttributes.setMethodName(controllerMethod.getName());
                            methodAttributes.setMethodType(myRequestMethod.methodType());

                            allowedMethods.put(urlPath, methodAttributes);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object dispatch(HttpServletResponse resp, HttpServletRequest req) throws ServletException, IOException {
        //resp.getWriter().write("A mers");
        String path = req.getPathInfo();

        if (allowedMethods.containsKey(path)) {
            MethodAttributes methAtt = allowedMethods.get(path);
            String controllerName = methAtt.getControllerClass();
            try {
                Class<?> controllerClass = Class.forName(controllerName);
                Object controllerInstance = controllerClass.newInstance();
                Method method = controllerClass.getMethod(methAtt.getMethodName(), null);
                return method.invoke(controllerInstance);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }


        /*if (path.startsWith("/employees")) {
            EmployeeController empController = new EmployeeController();
            return empController.getAllEmployees();
        }
        else{
            if(path.startsWith("/departments")){
                DepartmentController depController = new DepartmentController();
                return depController.getAllDepartments();
            }
        }*/
        return "High on this!";
    }

    private void reply(Object r, HttpServletResponse resp, HttpServletRequest req) throws IOException {
        PrintWriter out = resp.getWriter();
        out.printf(r.toString());
        //out.printf(allowedMethods.toString());
    }

    private void sendExceptionError(Exception e, HttpServletRequest req, HttpServletResponse resp) {

    }
}
