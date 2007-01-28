/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.netbeans.org/cddl.html
 * or http://www.netbeans.org/cddl.txt.

 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://www.netbeans.org/cddl.txt.
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 */

/*
 * ForEachXsl.java
 *
 * Created on November 14, 2006, 4:56 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.netbeans.modules.visualweb.ravebuild;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Ant;

/**
 *
 * @author qn145415
 */
public class ForEachXsl extends Ant {
    private Vector locations;
    private String startdir;
    private String suffix;
    
    /** Creates a new instance of ForEachXsl */
    public ForEachXsl() {
        locations = new Vector();
    }
    
    /** Comma separated list of locations. */
    public void setLocations (String s) {
        StringTokenizer tok = new StringTokenizer (s, ",:");
        locations = new Vector ();
        while ( tok.hasMoreTokens() ) {
            locations.add (tok.nextToken().trim());
        }
    }    
    
    public void setSuffix(String s) {
        suffix = s;
    }
    
    public void setStartdir (String s) {
        startdir = s;
    }
    
    /** Execute this task. */
    public void execute () throws BuildException {        
        if (locations.isEmpty()) {
            throw new BuildException("You must set at least one location!", getLocation());
        }

        File baseDir;
	if ( startdir == null ) {
            baseDir = getProject().getBaseDir();
        } else {
            baseDir = new File(getProject().getBaseDir(), startdir);
        }

        Iterator it = locations.iterator();
        String construct = "    <import file=\"${suite.dir}/templates/common.xml\"/>";
        while ( it.hasNext() ) {
            String dirName = (String) it.next();
            
            File dir = new File (baseDir, dirName);
            
            if (suffix != null) {
                dir = new File(dir, suffix);
            }
            
	    File buildXml = new File(dir, "build.xml");
            if (! buildXml.exists()) {
                log("Build file not found - " + buildXml.getAbsolutePath(), Project.MSG_ERR);
                continue;
            }
            
            try {
                BufferedReader br = new BufferedReader(new FileReader(buildXml));
                Vector lines = new Vector();
                boolean next = false;
                boolean found = false;
                boolean dirty = true;
                while (br.ready()) {
                    String nextline = br.readLine();
                    
                    if (next == true) {
                        next = false;
                        if (nextline.indexOf(construct.trim()) == -1) {
                            lines.add(construct);
                        }
                    }
                    
                    lines.add(nextline);
                    if (found == false && nextline.indexOf("<import file=\"nbproject/build-impl.xml\"/>") != -1) {
                        next = true;
                        found = true;
                    }else if (nextline.indexOf(construct.trim()) != -1) {
                        dirty = false;
                        break;
                    }
                }
                br.close();
                if (dirty) {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(buildXml));
                    for (int i = 0; i < lines.size(); i++) {
                        writer.write((String)lines.get(i));
                        writer.newLine();
                    }
                    writer.close();
                }
            }catch (IOException ex) {
                throw new BuildException("Unable to modify build.xml files", ex);
            }
            
        }
        
    }
    
}
