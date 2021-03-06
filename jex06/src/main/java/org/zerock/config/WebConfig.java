package org.zerock.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer
{

    // 스프링이 로딩될 때 SecurityConfig 클래스가 같이 로딩되도록 
    @Override
    protected Class<?>[] getRootConfigClasses()
    {
        return new Class[] {RootConfig.class, SecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses()
    {
        return new Class[] {ServletConfig.class};
    }

    @Override
    protected String[] getServletMappings()
    {
        return new String[] {"/"};
    }
    
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration)
    {
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
        
        //파일 업로드 위한 설정
        MultipartConfigElement multipartConfig 
                = new MultipartConfigElement("C:\\upload\\temp", 20971520, 41943040, 20971520);
        registration.setMultipartConfig(multipartConfig);
    }
    
    @Override
    protected Filter[] getServletFilters()
    {
        CharacterEncodingFilter characterEncodingFilter =
                new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        
        return new Filter[] {characterEncodingFilter};
    }

}
