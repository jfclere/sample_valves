/*
 *  Copyright(c) 2013 Red Hat Middleware, LLC,
 *  and individual contributors as indicated by the @authors tag.
 *  See the copyright.txt in the distribution for a
 *  full listing of individual contributors.
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library in the file COPYING.LIB;
 *  if not, write to the Free Software Foundation, Inc.,
 *  59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
 *
 * @author Jean-Frederic Clere
 * @version $Revision: 420067 $, $Date: 2006-07-08 09:16:58 +0200 (sub, 08 srp 2006) $
 */

package org.jboss.samplevalves;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import org.apache.catalina.valves.ValveBase;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;

/*
 * restrictedUserAgents valve.
 * restrictedUserAgents="^.*MS Web Services Client Protocol.*$" for example.
 */

public class restrictedUserAgentsValve
    extends ValveBase {

    private String restrictedUserAgents = null;

    public void setRestricteduseragents(String mystring) {
        this.restrictedUserAgents = mystring;
    }

    public void invoke(Request request, Response response)
        throws IOException, ServletException {
    	String agent = request.getHeader("User-Agent");
        System.out.println("user-agent: " + agent + " : " + restrictedUserAgents);
        if (Pattern.matches(restrictedUserAgents, agent)) {
        	System.out.println("user-agent: " + agent + " matches: " + restrictedUserAgents);
            response.addHeader("Connection", "close");
        }
    	getNext().invoke(request, response);
    }
}
