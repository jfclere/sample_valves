/*
 *  Copyright(c) 2020 Red Hat
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
 */

package org.jboss.samplevalves;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.io.IOException;

import javax.servlet.ServletException;
import org.apache.catalina.valves.ValveBase;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;

/*
 * RequestDumperValve valve.
 * Read header and dump them in catalina.out
 */

public class RequestDumperValve
    extends ValveBase {

    public void invoke(Request request, Response response)
        throws IOException, ServletException {
        Enumeration names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            System.out.println("HEADER: *" + name + "* VALUE: *" + request.getHeader(name) + "*");
        }
    	getNext().invoke(request, response);
    }
}
