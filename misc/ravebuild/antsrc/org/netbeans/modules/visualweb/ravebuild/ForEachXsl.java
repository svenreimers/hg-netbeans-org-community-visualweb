/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * Contributor(s):
 *
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
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
