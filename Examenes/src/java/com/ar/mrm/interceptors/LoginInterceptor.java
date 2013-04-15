/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ar.mrm.interceptors;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.interceptor.Interceptor;
import java.util.Map;
import org.apache.struts2.StrutsStatics;

/**
 *
 * @author Santi
 */
public class LoginInterceptor extends AbstractInterceptor implements Interceptor, StrutsStatics {

    public String intercept(ActionInvocation invocation) throws Exception {
        //MyAction action = (MyAction)invocation.getAction();
        //action.setDate(new Date());

        Map<String, Object> session = invocation.getInvocationContext().getSession();

        Object user = session.get("user");
        if (user == null) {
            return "login";
            // redirect to the 'login' action here
        } else {
            return invocation.invoke();
        }



      //  return invocation.invoke();
    }
}
