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



package org.netbeans.modules.visualweb.project.jsfportlet.ui.wizard;

import java.util.Vector;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.netbeans.spi.project.support.ant.AntProjectHelper;
import org.netbeans.spi.project.support.ant.EditableProperties;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import org.netbeans.modules.j2ee.deployment.devmodules.api.Deployment;

import org.netbeans.modules.j2ee.deployment.devmodules.spi.J2eeModuleProvider;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.util.NbBundle;


public class Utils {

    // COPIED FROM TOMCAT
    private static final String javaKeywords[] = {
        "abstract", "assert", "boolean", "break", "byte", "case",
        "catch", "char", "class", "const", "continue",
        "default", "do", "double", "else", "enum", "extends",
        "final", "finally", "float", "for", "goto",
        "if", "implements", "import", "instanceof", "int",
        "interface", "long", "native", "new", "package",
        "private", "protected", "public", "return", "short",
        "static", "strictfp", "super", "switch", "synchronized",
        "this", "throws", "transient", "try", "void",
        "volatile", "while" };

    private static final String JSP_PACKAGE_NAME = "org.apache.jsp";
    
    private static final String PLATFORM_ANT_NAME = "platform.ant.name"; //NOI18N
    public static final String SPECIFICATION_J2SE = "j2se";              //NOI18N

    public static File getRoot(File f) {
        File rootF = f;
        while (rootF.getParentFile() != null) {
            rootF = rootF.getParentFile();
        }
        return rootF;
    }

    public static FileObject getValidDir(File dir) throws IOException {
        final File f = dir.getCanonicalFile();
        f.mkdirs();
        if (!f.exists()) {
            throw new IOException("No such dir on disk: " + f);
        }
        if (!f.isDirectory()) {
            throw new IOException("Not really a dir" + ": " + f);
        }
        return FileUtil.toFileObject(f);
    }

    public static FileObject getValidEmptyDir(File dir) throws IOException {
        final FileObject fo = getValidDir(dir);
        if (fo.getChildren().length != 0) {
            throw new IOException("Dir has to be empty: " + dir);
        }
        return fo;
    }

    /**
     * Updates property file at given location of ant based project
     * @param h helper of the project
     * @param path a relative URI in the project directory
     * @param ep new or updated properties
     */
    public static void updateProperties(AntProjectHelper h, String path, EditableProperties ep) {
        EditableProperties properties = h.getProperties(path);
        properties.putAll(ep);
        h.putProperties(path, properties);
    }

    /**
     * Recursively checks whether the file lies underneath or equals the folder
     * @param folder the root of folders hierarchy to search in 
     * @param file the file to search for
     * @return <code>true</code>, if <code>file</code> lies somewhere underneath or equals the <code>folder</code>,
     * <code>false</code> otherwise
     */
    public static boolean isParentOrEqual(File folder, File file) {
        if(folder != null || file != null) {
            folder = FileUtil.normalizeFile(folder);
            file = FileUtil.normalizeFile(file);
            while(file != null) {
                if(file.equals(folder)) {
                    return true;
                }
                file = file.getParentFile();
            }
        }
        return false;
    }


    // COPIED FROM TOMCAT
    /** Returns a slash-delimited resource path for the servlet generated from 
     * JSP, given a resource path of the original JSP. Uses code copied from Tomcat.
     * Note: does not handle tag files yet, only JSP files.
     */
    static String getGeneratedJavaResource(String jspUri) {
        int iSep = jspUri.lastIndexOf('/');
        String packageName = (iSep > 0) ? makeJavaPackage(jspUri.substring(0,iSep)) : ""; // NOI18N
        if (packageName.length() == 0) {
            packageName = JSP_PACKAGE_NAME;
        }
        else {
            packageName = JSP_PACKAGE_NAME + "." + packageName; // NOI18N
        }
        String className = makeJavaIdentifier(jspUri.substring(iSep + 1));
        return packageName.replace('.', '/') + "/" + className + ".java"; // NOI18N
    }
    
    // COPIED FROM TOMCAT
    /**
     * Converts the given path to a Java package or fully-qualified class name
     *
     * @param path Path to convert
     *
     * @return Java package corresponding to the given path
     */
    private static final String makeJavaPackage(String path) {
        String classNameComponents[] = split(path,"/");
        StringBuffer legalClassNames = new StringBuffer();
        for (int i = 0; i < classNameComponents.length; i++) {
            legalClassNames.append(makeJavaIdentifier(classNameComponents[i]));
            if (i < classNameComponents.length - 1) {
                legalClassNames.append('.');
            }
        }
        return legalClassNames.toString();
    }
    
    // COPIED FROM TOMCAT
    /**
     * Splits a string into it's components.
     * @param path String to split
     * @param pat Pattern to split at
     * @return the components of the path
     */
    private static final String [] split(String path, String pat) {
        Vector comps = new Vector();
        int pos = path.indexOf(pat);
        int start = 0;
        while( pos >= 0 ) {
            if(pos > start ) {
                String comp = path.substring(start,pos);
                comps.add(comp);
            }
            start = pos + pat.length();
            pos = path.indexOf(pat,start);
        }
        if( start < path.length()) {
            comps.add(path.substring(start));
        }
        String [] result = new String[comps.size()];
        for(int i=0; i < comps.size(); i++) {
            result[i] = (String)comps.elementAt(i);
        }
        return result;
    }
            
    // COPIED FROM TOMCAT
    /**
     * Converts the given identifier to a legal Java identifier
     *
     * @param identifier Identifier to convert
     *
     * @return Legal Java identifier corresponding to the given identifier
     */
    private static final String makeJavaIdentifier(String identifier) {
        StringBuffer modifiedIdentifier = 
            new StringBuffer(identifier.length());
        if (!Character.isJavaIdentifierStart(identifier.charAt(0))) {
            modifiedIdentifier.append('_');
        }
        for (int i = 0; i < identifier.length(); i++) {
            char ch = identifier.charAt(i);
            if (Character.isJavaIdentifierPart(ch) && ch != '_') {
                modifiedIdentifier.append(ch);
            } else if (ch == '.') {
                modifiedIdentifier.append('_');
            } else {
                modifiedIdentifier.append(mangleChar(ch));
            }
        }
        if (isJavaKeyword(modifiedIdentifier.toString())) {
            modifiedIdentifier.append('_');
        }
        return modifiedIdentifier.toString();
    }
    
    // COPIED FROM TOMCAT
    /**
     * Mangle the specified character to create a legal Java class name.
     */
    private static final String mangleChar(char ch) {
        char[] result = new char[5];
        result[0] = '_';
        result[1] = Character.forDigit((ch >> 12) & 0xf, 16);
        result[2] = Character.forDigit((ch >> 8) & 0xf, 16);
        result[3] = Character.forDigit((ch >> 4) & 0xf, 16);
        result[4] = Character.forDigit(ch & 0xf, 16);
        return new String(result);
    }

    // COPIED FROM TOMCAT
    /**
     * Test whether the argument is a Java keyword
     */
    private static boolean isJavaKeyword(String key) {
        int i = 0;
        int j = javaKeywords.length;
        while (i < j) {
            int k = (i+j)/2;
            int result = javaKeywords[k].compareTo(key);
            if (result == 0) {
                return true;
            }
            if (result < 0) {
                i = k+1;
            } else {
                j = k;
            }
        }
        return false;
    }

    public static Color getErrorColor() {
        // inspired by org.openide.WizardDescriptor
        Color c = UIManager.getColor("nb.errorForeground"); //NOI18N
        return c == null ? new Color(89,79,191) : c;
    }
    
    public static String toClasspathString(File[] classpathEntries) {
        if (classpathEntries == null) {
            return "";
        }
        StringBuffer classpath = new StringBuffer();
        for (int i = 0; i < classpathEntries.length; i++) {
            classpath.append(classpathEntries[i].getAbsolutePath());
            if (i + 1 < classpathEntries.length) {
                classpath.append(":"); // NOI18N
            }
        }
        return classpath.toString();
    }
}
