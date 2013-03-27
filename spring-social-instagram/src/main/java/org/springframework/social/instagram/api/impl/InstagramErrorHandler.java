package org.springframework.social.instagram.api.impl;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.instagram.api.InstagramApiException;
import org.springframework.web.client.DefaultResponseErrorHandler;

public class InstagramErrorHandler extends DefaultResponseErrorHandler {
	protected static java.util.logging.Logger log = java.util.logging.Logger.getLogger("instagramApi");
	
	@Override
	public void handleError(ClientHttpResponse response) throws IOException {				
	    Map<String, Object> errorDetails = extractErrorDetailsFromResponse(response);
	    int code = Integer.valueOf(String.valueOf(errorDetails.get("code"))).intValue();
        String errorType = String.valueOf(errorDetails.get("error_type"));
        String message = String.valueOf(errorDetails.get("error_message"));
        throw new InstagramApiException(code, errorType, message);		
	}
	
	private String convertStreamToString(java.io.InputStream is) {
	    try {
	        return new java.util.Scanner(is).useDelimiter("\\A").next();
	    } catch (java.util.NoSuchElementException e) {
	        return "";
	    }
	}	
	
	@SuppressWarnings("unchecked")
    private Map<String, Object> extractErrorDetailsFromResponse(ClientHttpResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
		String body = convertStreamToString(response.getBody());
		log.info(body);		
        Map<String, Object> responseMap = mapper.<Map<String, Object>>readValue(body, new TypeReference<Map<String, Object>>() {});
        if(responseMap.containsKey("meta")) {
            Map<String, Object> meta = (Map<String, Object>) responseMap.get("meta");
            if(Integer.valueOf(String.valueOf(meta.get("code"))).intValue() > 200) {
                return meta;
            }
        }
        return null;
    }
}
