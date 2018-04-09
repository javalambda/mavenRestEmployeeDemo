package org.nandhu.learningmr.contextinitializer;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.glassfish.jersey.servlet.ServletContainer;
import org.nandhu.learningmr.configuration.DemoAppContext;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


//not useful as we need to autowire from the pojo class.
//used when we have intialize the webcontext for the whole web application
@Order(1)
public class AppContextIntializer implements WebApplicationInitializer {

//	@Override
//	protected WebApplicationContext createRootApplicationContext() {
//		System.out.println("Inside AppContextIntializer");
//		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//		ctx.register(DemoAppContext.class);
//		
//		
//		return ctx;
//	}

	public void onStartup(ServletContext servletContext) throws ServletException {
		System.out.println("######### printing servlet context ############");
//		WebApplicationContext context = getContext();
//        servletContext.addListener(new ContextLoaderListener(context));
//        System.out.println("########after Context loader listener");
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("JerseyServlet", new ServletContainer());
//        System.out.println("After Servlet registration");
//        
//        dispatcher.setInitParameter("contextConfigLocation", "<NONE>");
//        
//        dispatcher.setInitParameter("jersey.config.server.provider.packages", "org.nandhu.learningmr.webservices");
//        Map<String,String> map = dispatcher.getInitParameters();
//        
//        System.out.println("########## Printing servlet init parameters");
//        for(String key : map.keySet())
//        {
//        	System.out.println(" key - "+key + " Value - "+map.get(key));
//        }
//        
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/*");

		
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(DemoAppContext.class);
		ctx.setServletContext(servletContext);
		servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, ctx);
		
		ServletRegistration.Dynamic servlet = servletContext.addServlet("jersey-servlet", new DispatcherServlet());
		servlet.setInitParameter("jersey.config.server.provider.packages", "org.nandhu.learningmr.webservices");
		
		servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
		System.out.println("####### end of on startup ");
	}
	
	private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        //context.setConfigLocation("org.nandhu.learningmr");
        context.register(DemoAppContext.class);
        return context;
    }
	
}
