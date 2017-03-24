package hashtagService.services;

import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;


public class InstagramOAuth2Template extends OAuth2Template {
	
	public InstagramOAuth2Template() {
		super("1f2b73b40cee49aeaf5f8234f582ef09", "5e292e7a489e4a1681ba24e6ff122d34", "https://api.instagram.com/oauth/authorize/", "https://api.instagram.com/oauth/access_token/");
	}	
	
	public InstagramOAuth2Template(String clientId, String clientSecret) {
		super(clientId, clientSecret, "https://api.instagram.com/oauth/authorize", "https://api.instagram.com/oauth/access_token");
	}

	public String getClientAuthorizeUrl() {
		return 	this.buildAuthorizeUrl(GrantType.IMPLICIT_GRANT, getParameters());
	}
	
	public String getServerAuthorizeUrl() {
		return 	this.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, getParameters());
	}
	
	private OAuth2Parameters getParameters() {		
		OAuth2Parameters parameters = new OAuth2Parameters();
		parameters.setRedirectUri("http://localhost:8181/instagramRedirect/");
		parameters.setScope("public_content");
		
		return parameters;
	}
}
