package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) {
		
		SavedRequest savedReq = requestCache.getRequest(req, res);
		
		if (savedReq == null) {
			clearAuthenticationAttributes(req);
		}
		
		String targetUrlParam = getTargetUrlParameter();
		if (isAlwaysUseDefaultTargetUrl()
		          || (targetUrlParam != null
		          && StringUtils.hasText(req.getParameter(targetUrlParam)))) {
		    requestCache.removeRequest(req, res);
		    clearAuthenticationAttributes(req);
		    return;
		}
		
		clearAuthenticationAttributes(req);
		return;
	}
	
	public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }
}
