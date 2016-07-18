package ro.teamnet.zth;


import org.codehaus.jackson.map.ObjectMapper;
import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyCreateParam;
import ro.teamnet.zth.api.annotations.MyRequestMethod;

import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.controller.EmployeeController;
import ro.teamnet.zth.fmk.AnnotationScanUtils;
import ro.teamnet.zth.fmk.MethodAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

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
        //System.out.println(req.getReader().readLine());
        //System.out.println(req.getReader().readLine());
        //System.out.println(req.getParameter("id"));
        //System.out.println(req.getContentType());

        //req.getReader().
        dispatchReply("POST", req, resp);

    }

    public void dispatchReply(String method, HttpServletRequest req, HttpServletResponse resp) {
        Object r = null;
        try {
            r = dispatch(method, resp, req);
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
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doDelete(req, resp);
        //employeeController.deleteOneEmployee(Long.valueOf(req.getParameter("id")));
        dispatchReply("DELETE", req, resp);

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
                            methodAttributes.setParameterTypes(controllerMethod.getParameterTypes());
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

    private Object dispatch(String httpMethod, HttpServletResponse resp, HttpServletRequest req) throws ServletException, IOException {
        //resp.getWriter().write("A mers");
        String path = req.getPathInfo();

        if (allowedMethods.containsKey(path)) {
            MethodAttributes methAtt = allowedMethods.get(path);

            String controllerName = methAtt.getControllerClass();
            try {
                Class<?> controllerClass = Class.forName(controllerName);
                Object controllerInstance = controllerClass.newInstance();

                Method method = controllerClass.getMethod(methAtt.getMethodName(), methAtt.getParameterTypes());
                if (method.getAnnotation(MyRequestMethod.class).methodType().equals(httpMethod)) {
                    Parameter[] parameters = method.getParameters();
                    List<Object> parameterValues = new ArrayList<>();
                    for (Parameter param : parameters) {
                        if (param.isAnnotationPresent(MyRequestParam.class)) {
                            MyRequestParam annotation = param.getAnnotation(MyRequestParam.class);
                            String name = annotation.name();
                            String requestParamValue = req.getParameter(name);
                            Class<?> type = param.getType();
                            Object requestParamObject = requestParamValue;
                            if (!type.equals(String.class))
                                requestParamObject = new ObjectMapper().readValue(requestParamValue, type);
                            parameterValues.add(requestParamObject);
                        }
                        if (param.isAnnotationPresent(MyCreateParam.class)) {
                            //System.out.println(method.getName());
                            BufferedReader buffReader = req.getReader();
                            ObjectMapper mapper = new ObjectMapper();
                            Class<?> type = param.getType();
                            Object obj;
                            obj = mapper.readValue(buffReader, type);
                            parameterValues.add(obj);
                        }
                    }


                    return method.invoke(controllerInstance, parameterValues.toArray());
                }
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

        ObjectMapper objectMapper = new ObjectMapper();
        String valuesString = objectMapper.writeValueAsString(r);

        PrintWriter out = resp.getWriter();
        out.printf(valuesString);
        //out.printf(allowedMethods.toString());
    }

    private void sendExceptionError(Exception e, HttpServletRequest req, HttpServletResponse resp) {

    }
}
