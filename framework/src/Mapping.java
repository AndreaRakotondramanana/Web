package etu1831.framework;
public class Mapping {
    String className;
    String method;
    Class[] arguments;
    public Mapping(String className, String method, Class[] arguments) {
        this.className = className;
        this.method = method;
        this.arguments = arguments;
    }
    public Mapping(String className, String method) {
        this.className = className;
        this.method = method;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public Class[] getArguments() {
        return arguments;
    }
    public void setArguments(Class[] arguments) {
        this.arguments = arguments;
    } 
}
