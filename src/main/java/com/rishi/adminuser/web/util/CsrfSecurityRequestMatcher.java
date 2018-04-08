/*---------------------------------------------------------------------------- Copyright (c) 2013
 * Aricent Group. All rights reserved.
 * ---------------------------------------------------------------------------- FILE :
 * 
 *----------------------------------------------------------------------------
 * 
 * 
 * CREATION DATE : 18 Jan 2016 AUTHOR : Aricent PROJECT : ACE
 * 
 *---------------------------------------------------------------------------- HISTORY: <Date>
 * <Name> <Comment> Base version
 * 
 *----------------------------------------------------------------------------
 */
package com.rishi.adminuser.web.util;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

/**
 * 
 *
 */
@Component
public class CsrfSecurityRequestMatcher implements RequestMatcher {
	
    private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
    
    private RegexRequestMatcher unprotectedMatcher = new RegexRequestMatcher("/api/register", null);

    @Override
    public boolean matches(HttpServletRequest request) {          
        if(allowedMethods.matcher(request.getMethod()).matches()){
            return false;
        }
        return !unprotectedMatcher.matches(request);
    }
}

