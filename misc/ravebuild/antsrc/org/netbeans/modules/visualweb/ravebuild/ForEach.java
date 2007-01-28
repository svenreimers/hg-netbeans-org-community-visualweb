// This file was copied from daning.sfbay:/cvs/darkhorse/nbbuild/antsrc and modified

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
package org.netbeans.modules.visualweb.ravebuild;

import java.io.File;
import java.util.*;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.Ant;
import org.apache.tools.ant.taskdefs.Property;

/**
 * For each specified location call ant task with specified target name.
 * If target name is not specified than name of current target is used.
 *
 * @author  Libor Kramolis
 */
public class ForEach extends Ant {
    private static final boolean DEBUG = false;
    private static final boolean ECHO  = true;
    
    private List   locations; // List<String>
    private String startdir;
    private boolean skipNonExistentDir = false;
    private Vector properties = new Vector();
    private String suffix;
    
    //
    // init
    //
    
    public ForEach () {
        locations = new Vector();
    }

    //
    // itself
    //

    /** Comma separated list of locations. */
    public void setLocations (String s) {
        if ( DEBUG ) log ("SET locations = " + s);

        StringTokenizer tok = new StringTokenizer (s, ",:");
        locations = new Vector ();
        while ( tok.hasMoreTokens() ) {
            locations.add (tok.nextToken().trim());
        }
    }

    //override setDir() to noop
    public void setDir() {}
    
    private String target;
    public String getTarget() {
        return target;
    }
    public void setTarget(String v) {
        target = v;
    }
    
    private boolean inheritAll;
    public boolean getInheritAll() {
        return inheritAll;
    }
    public void setInheritAll(boolean v) {
        inheritAll = v;
    }

   /**
    * Support nested property elements
    */
   public void addProperty (Property p) {
       properties.add(p);
   }
    
    /** Where cd first
     */
    public void setStartdir (String s) {
        if ( DEBUG ) log ("SET startdir = " + s);

        startdir = s;
    }
    
    public void setSuffix(String s) {
        suffix = s;
    }
    
    public void setSkipNonExistentDir(boolean v) {
        skipNonExistentDir = v;
    }
    public boolean getSkipNonExistentDir() {
        return skipNonExistentDir;
    }
    
    /** Execute this task. */
    public void execute () throws BuildException {        
        if (locations.isEmpty()) {
            if (skipNonExistentDir) {
                if ( DEBUG ) {
                    log ("for-each: No locations no loops!");
		}
                return;
            } else {
                throw new BuildException("You must set at least one location!", getLocation());
            }
        }

        if ( getTarget() == null ) {
            setTarget(this.getOwningTarget().getName());

            if ( DEBUG ) log ("EXECUTE owningTarget = " + this.getOwningTarget());
        }
        File baseDir;
	if ( startdir == null ) {
            baseDir = getProject().getBaseDir();
        } else {
            baseDir = new File(getProject().getBaseDir(), startdir);
        }

        Iterator it = locations.iterator();
        while ( it.hasNext() ) {
            String dirName = (String) it.next();

            if ( ECHO ) log ("Process '" + dirName + "' location with '" + target + "' target ...");
            
            File dir = new File (baseDir, dirName);
            
            if (suffix != null) {
                dir = new File(dir, suffix);
            }
            
	    File buildXml = new File(dir, "build.xml");
            if (! buildXml.exists() && skipNonExistentDir) {
                if ( DEBUG ) log ("Skipped non-existent " + dir.getAbsolutePath());
                continue;
            }

	    String location = (new File(dir, "build.xml")).getAbsolutePath();
            Ant ant = (Ant) getProject().createTask("ant");
            ant.init();
            ant.setLocation(getLocation());
	    ant.setAntfile(location);
            ant.setTarget (getTarget());
            ant.setInheritAll(getInheritAll());
	    if (getPropertyName() != null && getPropertyValue() != null) {
		org.apache.tools.ant.taskdefs.Property p = ant.createProperty();
		p.setName(getPropertyName());
		p.setValue(getPropertyValue());
	    }

            for (int i = 0; i < properties.size(); i++) {
                Property p = (Property) properties.get(i);
                Property antProp = ant.createProperty();
                antProp.setName(p.getName());
                antProp.setValue(p.getValue());
            }

            if ( DEBUG ) log ("--> next [ " + target + " ] " + dir.getAbsolutePath());
            
            ant.execute();
        }
        
    }
    public void handleErrorOutput(String output) {
	log(output);
    }
    
        private String propertyName;
        private String propertyValue;
        
        public String getPropertyName() {
            return propertyName;
        }
        public void setPropertyName(String name) {
            propertyName = name;
        }
        public String getPropertyValue() {
            return propertyValue;
        }
        public void setPropertyValue(String v) {
            propertyValue = v;
        }
    
}
