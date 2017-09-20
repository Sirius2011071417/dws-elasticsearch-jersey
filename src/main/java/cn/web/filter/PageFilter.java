package cn.web.filter;

import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

public class PageFilter implements ContainerRequestFilter {

	@Override
	public ContainerRequest filter(ContainerRequest request) {
		MultivaluedMap<String, String> map = request.getQueryParameters();
		return null;
	}

}
