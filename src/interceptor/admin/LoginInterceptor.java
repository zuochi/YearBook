package interceptor.admin;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 取得请求相关的ActionContext实例  
        //ActionContext ctx = invocation.getInvocationContext();  
  
       // if (ctx.getSession().get("user") != null || "loginA_execute".equals(ctx.getName())) {  
            return invocation.invoke();  
        //}  
        
       // return "login";  
	}

}
