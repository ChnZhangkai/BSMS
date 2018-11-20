package com.bsms.web.interceptor;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import com.bsms.web.util.HttpContextHelper;

/**
 * @ClassName: WebSessionIdInterceptor.java
 * @Description: RestTemplate拦截器，在请求头中添加web服务的sessionId
 * @author: 张凯
 * @Date: 2018年7月14日 下午5:52:02
 * @parma <T>
 */
public class WebSessionIdInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		request.getHeaders().add("reqSessionId", HttpContextHelper.currentSession().getId());
		return execution.execute(request, body);
	}

}
