package com.example.componentlib;

public class ServiceFactory {
    private static final ServiceFactory ourInstance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return ourInstance;
    }

    private ServiceFactory() {
    }

    private ILoginInterface iLoginInterface;

    public ILoginInterface getiLoginInterface() {
        return iLoginInterface;
    }

    public void setiLoginInterface(ILoginInterface iLoginInterface) {
        this.iLoginInterface = iLoginInterface;
    }
}
