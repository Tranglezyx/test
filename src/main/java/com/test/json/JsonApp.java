package com.test.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author trangle
 */
public class JsonApp {

    public static void main(String[] args) throws IOException {
        JsonNode jsonNode = new ObjectMapper().readTree("{\"failed\":true,\"code\":\"error.error\",\"message\":\"程序出现错误，请联系管理员\",\"type\":\"warn\",\"trace\":[\"org.hzero.srm.supplier.app.service.impl.LifeCycleServiceImpl.rejectStageChange(LifeCycleServiceImpl.java:170)\",\"org.hzero.srm.supplier.app.service.impl.LifeCycleServiceImpl$$FastClassBySpringCGLIB$$976a1a39.invoke(<generated>)\",\"org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\",\"org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:738)\",\"org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157)\",\"org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:99)\",\"org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:282)\",\"org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:96)\",\"org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\",\"org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:673)\",\"org.hzero.srm.supplier.app.service.impl.LifeCycleServiceImpl$$EnhancerBySpringCGLIB$$a570f493.rejectStageChange(<generated>)\",\"org.hzero.srm.supplier.app.service.impl.RecommendHeaderServiceImpl.rejectedRecommendForm(RecommendHeaderServiceImpl.java:385)\",\"org.hzero.srm.supplier.app.service.impl.RecommendHeaderServiceImpl$$FastClassBySpringCGLIB$$23a58b3a.invoke(<generated>)\",\"org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\",\"org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:738)\",\"org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157)\",\"org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:99)\",\"org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:282)\",\"org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:96)\",\"org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\",\"org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:673)\",\"org.hzero.srm.supplier.app.service.impl.RecommendHeaderServiceImpl$$EnhancerBySpringCGLIB$$1ed7036.rejectedRecommendForm(<generated>)\",\"org.hzero.srm.supplier.api.controller.v1.RecommendHeaderController.rejectedRecommendForm(RecommendHeaderController.java:85)\",\"org.hzero.srm.supplier.api.controller.v1.RecommendHeaderController$$FastClassBySpringCGLIB$$2d2bb296.invoke(<generated>)\",\"org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:204)\",\"org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:738)\",\"org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:157)\",\"org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:92)\",\"org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:179)\",\"org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:673)\",\"org.hzero.srm.supplier.api.controller.v1.RecommendHeaderController$$EnhancerBySpringCGLIB$$68ecbae0.rejectedRecommendForm(<generated>)\",\"sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\",\"sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\",\"sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\",\"java.lang.reflect.Method.invoke(Method.java:498)\",\"org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205)\",\"org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:133)\",\"org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:97)\",\"org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:827)\",\"org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:738)\",\"org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:85)\",\"org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:963)\",\"org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:897)\",\"org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:970)\",\"org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:872)\",\"javax.servlet.http.HttpServlet.service(HttpServlet.java:707)\",\"org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:846)\",\"javax.servlet.http.HttpServlet.service(HttpServlet.java:790)\",\"io.undertow.servlet.handlers.ServletHandler.handleRequest(ServletHandler.java:85)\",\"io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:129)\",\"org.springframework.boot.web.filter.ApplicationContextHeaderFilter.doFilterInternal(ApplicationContextHeaderFilter.java:55)\",\"org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\",\"io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\",\"io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\",\"io.choerodon.resource.filter.JwtTokenFilter.doFilter(JwtTokenFilter.java:90)\",\"io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\",\"io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\",\"org.springframework.boot.actuate.trace.WebRequestTraceFilter.doFilterInternal(WebRequestTraceFilter.java:110)\",\"org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\",\"io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\",\"io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\",\"org.springframework.security.web.FilterChainProxy.doFilterInternal(FilterChainProxy.java:208)\",\"org.springframework.security.web.FilterChainProxy.doFilter(FilterChainProxy.java:177)\",\"org.springframework.web.filter.DelegatingFilterProxy.invokeDelegate(DelegatingFilterProxy.java:346)\",\"org.springframework.web.filter.DelegatingFilterProxy.doFilter(DelegatingFilterProxy.java:262)\",\"io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\",\"io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\",\"org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:99)\",\"org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\",\"io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\",\"io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\",\"org.springframework.web.filter.HttpPutFormContentFilter.doFilterInternal(HttpPutFormContentFilter.java:105)\",\"org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\",\"io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\",\"io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\",\"org.springframework.web.filter.HiddenHttpMethodFilter.doFilterInternal(HiddenHttpMethodFilter.java:81)\",\"org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\",\"io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\",\"io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\",\"org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:197)\",\"org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\",\"io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\",\"io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\",\"org.springframework.boot.actuate.autoconfigure.MetricsFilter.doFilterInternal(MetricsFilter.java:106)\",\"org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\",\"io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\",\"io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\",\"io.micrometer.spring.web.servlet.WebMvcMetricsFilter.doFilterInternal(WebMvcMetricsFilter.java:106)\",\"org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:107)\",\"io.undertow.servlet.core.ManagedFilter.doFilter(ManagedFilter.java:61)\",\"io.undertow.servlet.handlers.FilterHandler$FilterChainImpl.doFilter(FilterHandler.java:131)\",\"io.undertow.servlet.handlers.FilterHandler.handleRequest(FilterHandler.java:84)\",\"io.undertow.servlet.handlers.security.ServletSecurityRoleHandler.handleRequest(ServletSecurityRoleHandler.java:62)\",\"io.undertow.servlet.handlers.ServletDispatchingHandler.handleRequest(ServletDispatchingHandler.java:36)\",\"io.undertow.servlet.handlers.security.SSLInformationAssociationHandler.handleRequest(SSLInformationAssociationHandler.java:131)\",\"io.undertow.servlet.handlers.security.ServletAuthenticationCallHandler.handleRequest(ServletAuthenticationCallHandler.java:57)\",\"io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)\",\"io.undertow.security.handlers.AbstractConfidentialityHandler.handleRequest(AbstractConfidentialityHandler.java:46)\",\"io.undertow.servlet.handlers.security.ServletConfidentialityConstraintHandler.handleRequest(ServletConfidentialityConstraintHandler.java:64)\",\"io.undertow.security.handlers.AuthenticationMechanismsHandler.handleRequest(AuthenticationMechanismsHandler.java:60)\",\"io.undertow.servlet.handlers.security.CachedAuthenticatedSessionHandler.handleRequest(CachedAuthenticatedSessionHandler.java:77)\",\"io.undertow.security.handlers.AbstractSecurityContextAssociationHandler.handleRequest(AbstractSecurityContextAssociationHandler.java:43)\",\"io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)\",\"io.undertow.server.handlers.PredicateHandler.handleRequest(PredicateHandler.java:43)\",\"io.undertow.servlet.handlers.ServletInitialHandler.handleFirstRequest(ServletInitialHandler.java:292)\",\"io.undertow.servlet.handlers.ServletInitialHandler.access$100(ServletInitialHandler.java:81)\",\"io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:138)\",\"io.undertow.servlet.handlers.ServletInitialHandler$2.call(ServletInitialHandler.java:135)\",\"io.undertow.servlet.core.ServletRequestContextThreadSetupAction$1.call(ServletRequestContextThreadSetupAction.java:48)\",\"io.undertow.servlet.core.ContextClassLoaderSetupAction$1.call(ContextClassLoaderSetupAction.java:43)\",\"io.undertow.servlet.handlers.ServletInitialHandler.dispatchRequest(ServletInitialHandler.java:272)\",\"io.undertow.servlet.handlers.ServletInitialHandler.access$000(ServletInitialHandler.java:81)\",\"io.undertow.servlet.handlers.ServletInitialHandler$1.handleRequest(ServletInitialHandler.java:104)\",\"io.undertow.server.Connectors.executeRootHandler(Connectors.java:211)\",\"io.undertow.server.HttpServerExchange$1.run(HttpServerExchange.java:809)\",\"java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)\",\"java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)\",\"java.lang.Thread.run(Thread.java:748)\"]}");
        System.out.println(jsonNode);
        System.out.println(jsonNode.get("failed").asBoolean());
        System.out.println(jsonNode.get("trace"));
    }
}
