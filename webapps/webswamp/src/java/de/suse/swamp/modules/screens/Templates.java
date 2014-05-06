/*
 * SWAMP Workflow Administration and Management Platform
 *
 * Copyright (c) 2003 Doris Baum <dbaum@suse.de>
 * Copyright (c) 2006 Novell Inc.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of version 2 of the GNU General Public
 * License as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51 Franklin
 * St, Fifth Floor, Boston, MA 02110-1301 USA 
 *
 * In addition, as a special exception, Novell Inc. gives permission to link the
 * code of this program with the following applications:
 *
 * - All applications of the Apache Software Foundation 
 *
 * and distribute such linked combinations.
 */

package de.suse.swamp.modules.screens;

/**
 * Gets workflows from workflow manager and displays them
 *
 * @author Doris Baum &lt;dbaum@suse.de&gt;
 * @version $Id$
 *
 */

import java.util.*;

import org.apache.turbine.util.*;
import org.apache.velocity.context.*;

import de.suse.swamp.core.api.*;
import de.suse.swamp.core.workflow.*;

public class Templates extends SecureScreen {

    public void doBuildTemplate(RunData data, Context context) throws Exception {
        super.doBuildTemplate(data, context);
        
        WorkflowAPI wfapi = new WorkflowAPI();
        String uname = data.getUser().getName();
        ArrayList templatenames = wfapi.getWorkflowTemplateNames(uname);
        
        context.put("templateNames", templatenames);
        context.put("templateMap", wfapi.getWorkflowTemplates(uname));
        

        
       // provide Template for showing Description
        HashMap templates = new HashMap();
        for (Iterator it = templatenames.iterator(); it.hasNext(); ){
           String name = (String) it.next();
           WorkflowTemplate template = wfapi.getWorkflowTemplate(name, uname);
            templates.put(name, template);
        }
        context.put("templates", templates);
        context.put("standardlogo", "true");
    }
}