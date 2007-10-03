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

package org.netbeans.modules.visualweb.ravebuild;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.tools.ant.BuildException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

/** This class represents module updates tracking
 *
 * @author  akemr
 */
class ModuleTracking {
    private static final String ELEMENT_MODULE_TRACKING = "tracking"; // NOI18N
    private static final String ELEMENT_MODULE = "module"; // NOI18N
    private static final String ATTR_MODULE_NAME = "name"; // NOI18N
    private static final String ELEMENT_FILE = "file"; // NOI18N
    private static final String ATTR_FILE_NAME = "name"; // NOI18N
    private static final String ATTR_CODE_NAME = "codename"; // NOI18N
    private static final String ATTR_MODULE_PATH = "path"; // NOI18N
    private static final String ATTR_NBM_HOME_PAGE = "nbmhomepage"; // NOI18N
    private static final String ATTR_NBM_FILE_NAME = "nbmfilename"; // NOI18N
    private static final String ATTR_NBM_NEEDS_RESTART = "nbmneedsrestart"; // NOI18N
    private static final String ATTR_NBM_IS_GLOBAL = "nbmisglobal"; // NOI18N
    private static final String ATTR_NBM_RELEASE_DATE = "nbmreleasedate"; // NOI18N
    private static final String ATTR_NBM_MODULE_AUTHOR = "nbmmoduleauthor"; // NOI18N
    
    /** Platform dependent file name separator */
    private static final String FILE_SEPARATOR = System.getProperty ("file.separator");   // NOI18N             

    /** The name of the file */
    public static final String TRACKING_FILE = "module_tracking.xml"; // NOI18N
    
    private boolean pError = false;
    
    private File trackingFile = null;
    
    private String nbPath = null;
    private Tracking tracking = null;
   
    // for generating xml in build process
    public ModuleTracking(String nbPath) {
        this.nbPath = nbPath;
        File directory = new File( nbPath );
        if (!directory.exists()) {
            directory.mkdirs();
        }
        trackingFile = new File(directory, TRACKING_FILE);
        read();
    }
    
/*    public Module addNewModule( String name ) {
         = new Tracking();
        module.setName( name );
        return module;
    }*/
    
    public void putModule(String name, String codename, String path, String nbmfilename, String nbmhomepage, String nbmneedsrestart, String nbmreleasedate, String nbmmoduleauthor, String nbmisglobal, String[] files) {
        if (name == null) {
            // #59426: external module, skip.
            return;
        }
        Module modByName = tracking.getModule(name);
	Module modByCodeName = tracking.getModuleByCodeName(codename);
	Module module = null;
	if (modByName != null) {
	    module = modByName;
	} else if (modByCodeName != null) {
	    module = modByCodeName;
	}
        if (module == null) {
            module = new Module();
            module.setName(name);
	    module.setCodeName(codename);
            module.setPath(path);
            tracking.addModule(module);
        }
        if (module.getNbmFileName().equals("")) // NOI18N
            module.setNbmFileName(nbmfilename);
        if (module.getNbmHomePage().equals("")) // NOI18N
            module.setNbmHomePage(nbmhomepage);
        if (module.getNbmModuleAuthor().equals("")) // NOI18N
            module.setNbmModuleAuthor(nbmmoduleauthor);
        if (module.getNbmNeedsRestart().equals("")) // NOI18N
            module.setNbmNeedsRestart(nbmneedsrestart);
        if (module.getNbmReleaseDate().equals("")) // NOI18N
            module.setNbmReleaseDate(nbmreleasedate);
        if (module.getNbmIsGlobal().equals ("")) { // NOI18N
            module.setNbmIsGlobal(nbmisglobal);
        }
/*	    
	System.out.println("    module        == "+name);
	System.out.println("    path          == "+path);
	System.out.println("    codename      == "+ codename);
	System.out.println("    filename      == "+module.getNbmFileName());
	System.out.println("    homepage      == "+module.getNbmHomePage());
	System.out.println("    author        == "+module.getNbmModuleAuthor());
	System.out.println("    needsrestart  == "+module.getNbmNeedsRestart());
	System.out.println("    global        == "+module.getNbmIsGlobal ());
	System.out.println("    releasedate   == "+module.getNbmReleaseDate());
*/
        module.putFiles( files );
    }
    
    public Map/*<String,Module>*/ getModules() {
        return tracking.getModules();
    }
    
    public Hashtable getModulesByCodeName() {
            return tracking.getModulesByCodeName();
    }

    public Iterator getFilesForModule(String name) {
        Module module = tracking.getModule(name);
        if (module == null) return null;
        String[] files = new String[module.getFiles().size()];
        return module.getFiles().iterator();
    }
    
    public String getModulePath(String name) {
        Module module = tracking.getModule(name);
        if (module == null) return null;
        return module.getPath();
    }
    
    void write( ) {
        Document document = XMLUtil.createDocument(ELEMENT_MODULE_TRACKING);
        Element e_module_tracking = document.getDocumentElement();
        Iterator it2 = tracking.getModules().values().iterator();
        while ( it2.hasNext() ) {
            Module mod = (Module)it2.next();
            Element e_module = document.createElement(ELEMENT_MODULE);
            assert mod.getName() != null : mod.getCodeName();
            e_module.setAttribute(ATTR_MODULE_NAME, mod.getName());
            e_module.setAttribute(ATTR_CODE_NAME, mod.getCodeName());
            e_module.setAttribute(ATTR_MODULE_PATH, mod.getPath());
            e_module.setAttribute(ATTR_NBM_FILE_NAME, mod.getNbmFileName());
            e_module.setAttribute(ATTR_NBM_HOME_PAGE, mod.getNbmHomePage());
            e_module.setAttribute(ATTR_NBM_MODULE_AUTHOR, mod.getNbmModuleAuthor());
            e_module.setAttribute(ATTR_NBM_NEEDS_RESTART, mod.getNbmNeedsRestart());
            e_module.setAttribute(ATTR_NBM_IS_GLOBAL, mod.getNbmIsGlobal ());
            e_module.setAttribute(ATTR_NBM_RELEASE_DATE, mod.getNbmReleaseDate());
            e_module_tracking.appendChild( e_module );
            Iterator it3 = mod.getFiles().iterator();
            while ( it3.hasNext() ) {
                String file = (String)it3.next();
                Element e_file = document.createElement(ELEMENT_FILE);
                e_file.setAttribute(ATTR_FILE_NAME, file.replace(File.separatorChar,'/'));
                e_module.appendChild( e_file );
            }
        }
        
        //document.getDocumentElement().normalize();
        try {
	    OutputStream os = new FileOutputStream(trackingFile);
            XMLUtil.write(document, os);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
            trackingFile.delete();
            throw new BuildException("Could not write update tracking file " + trackingFile.getAbsolutePath(), e);
        }        
    }

    /** Scan through org.w3c.dom.Document document. */
    private void read() {
        /** org.w3c.dom.Document document */
        org.w3c.dom.Document document;
        if (trackingFile.exists()) {
            InputStream is;
            try {
                is = new FileInputStream( trackingFile );
                
            InputSource xmlInputSource = new InputSource( is );
            document = XMLUtil.parse( xmlInputSource, false, false, new ErrorCatcher(), null );
            if (is != null)
                is.close();
            }
            catch ( org.xml.sax.SAXException e ) {
                System.out.println("Module tracking file " + trackingFile.getAbsolutePath() + " is not well formatted XML document" ); 
                e.printStackTrace();
                return;
            }
            catch ( java.io.IOException e ) {
                System.out.println("I/O error when accessing module tracking file " + trackingFile.getAbsolutePath() ); 
                e.printStackTrace();
                return;
            }
            
            org.w3c.dom.Element element = document.getDocumentElement();
            if ((element != null) && element.getTagName().equals(ELEMENT_MODULE_TRACKING)) {
                scanElement_module_tracking(element);
            }
        }
        if (tracking == null)
            tracking = new Tracking();
    }
    
    /** Scan through org.w3c.dom.Element named module. */
    void scanElement_module_tracking(org.w3c.dom.Element element) { // <tracking>
        tracking = new Tracking();        
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            if ( node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE ) {
                org.w3c.dom.Element nodeElement = (org.w3c.dom.Element)node;
                if (nodeElement.getTagName().equals(ELEMENT_MODULE)) {
                    scanElement_module(nodeElement, tracking);
                }
            }
        }
    }
    
    /** Scan through org.w3c.dom.Element named module. */
    void scanElement_module(org.w3c.dom.Element element, Tracking tracking) { // <module>
        Module module = new Module();        
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr)attrs.item(i);
            if (attr.getName().equals(ATTR_MODULE_NAME)) { // <module name="???">
                module.setName( attr.getValue() );
            }
            if (attr.getName().equals(ATTR_CODE_NAME)) { // <module codename="???">
                module.setCodeName( attr.getValue() );
            }
            if (attr.getName().equals(ATTR_MODULE_PATH)) { // <module path="???">
                module.setPath( attr.getValue() );
            }
            if (attr.getName().equals(ATTR_NBM_HOME_PAGE)) { // <module nbmhomepage="???">
                module.setNbmHomePage( attr.getValue() );
            }
            if (attr.getName().equals(ATTR_NBM_FILE_NAME)) { // <module nbmfilename="???">
                module.setNbmFileName( attr.getValue() );
            }
            if (attr.getName().equals(ATTR_NBM_NEEDS_RESTART)) { // <module nbmneedsrestart="???">
                module.setNbmNeedsRestart( attr.getValue() );
            }
            if (attr.getName().equals(ATTR_NBM_IS_GLOBAL)) { // <module nbmisglobal="???">
                module.setNbmIsGlobal( attr.getValue() );
            }
            if (attr.getName().equals(ATTR_NBM_RELEASE_DATE)) { // <module nbmreleasedate="???">
                module.setNbmReleaseDate( attr.getValue() );
            }
            if (attr.getName().equals(ATTR_NBM_MODULE_AUTHOR)) { // <module nbmmoduleauthor="???">
                module.setNbmModuleAuthor( attr.getValue() );
            }
       }
        org.w3c.dom.NodeList nodes = element.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            org.w3c.dom.Node node = nodes.item(i);
            if ( node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE ) {
                org.w3c.dom.Element nodeElement = (org.w3c.dom.Element)node;
                if (nodeElement.getTagName().equals(ELEMENT_FILE)) {
                    scanElement_file(nodeElement, module);
                }
            }
        }
        tracking.addModule(module);
    }
    
    /** Scan through org.w3c.dom.Element named file. */
    void scanElement_file(org.w3c.dom.Element element, Module module) { // <file>
        String file = null;
        org.w3c.dom.NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            org.w3c.dom.Attr attr = (org.w3c.dom.Attr)attrs.item(i);
            if (attr.getName().equals(ATTR_FILE_NAME)) { // <file name="???">
                file = attr.getValue();
            }
        }
        module.addFile (file.replace(File.separatorChar,'/'));
    }

    public class Tracking extends Object {        
        private Map/*<String,Module>*/ modules = new HashMap();
	private Hashtable modulesByCodeName = new Hashtable();

        Map/*<String,Module>*/ getModules() {
            return modules;
        }
        
         /** Getter for property files.
         * @return Value of property files.
         */
        Hashtable getModulesByCodeName() {
            return modulesByCodeName;
        }
        
        /** Setter for property files.
         * @param files New value of property files.
         */
        void setModulesByCodeName(Hashtable modules) {
            this.modulesByCodeName = modules;
        }
        
        public void addModule( Module module ) {
            modules.put( module.getName(), module );
	    modulesByCodeName.put( module.getCodeName(), module );
        }
        
        public Module getModule( String name ) {
            return (Module) modules.get(name);
        }
        public Module getModuleByCodeName( String codename ) {
            return (Module) modulesByCodeName.get(codename);
        }
    }

    public class Module extends Object {        
        /** Holds value of property name. */
        private String name;
        
        /** Holds value of property codename. */
        private String codename;
        
        /** Holds value of property path. */
        private String path;
        
        /** Holds value of property nbmhomepage. */
        private String nbmhomepage = ""; // NOI18N
        
        /** Holds value of property nbmfilename. */
        private String nbmfilename = ""; // NOI18N
        
        /** Holds value of property nbmneedsrestart. */
        private String nbmneedsrestart = ""; // NOI18N
        
        /** Holds value of property nbmisglobal. */
        private String nbmisglobal = ""; // NOI18N
        
        /** Holds value of property nbmreleasedate. */
        private String nbmreleasedate = ""; // NOI18N
        
        /** Holds value of property nbmmoduleauthor. */
        private String nbmmoduleauthor = ""; // NOI18N

        /** Holds value of property files. */
        private List files = new ArrayList();
        
        /** Getter for property name */
        String getName() {
            return name;
        }
        
        /** Setter for property name */
        void setName(String name) {
            this.name = name;
        }
        
        /** Getter for property codename */
        String getCodeName() {
            return codename;
        }
        
        /** Setter for property codename */
        void setCodeName(String codename) {
            this.codename = codename;
        }
        
        /** Getter for property path */
        String getPath() {
            return path;
        }
        
        /** Setter for property path */
        void setPath(String path) {
            this.path = path;
        }
        
        /** Getter for property nbmhomepage */
        String getNbmHomePage () {
            if (this.nbmhomepage == null) {
                return ""; // NOI18N
            } else {
                return this.nbmhomepage;
            }
        }
        
        /** Setter for property nbmhomepage */
        void setNbmHomePage (String nbmh) {
            if (!(nbmh == null)) 
                this.nbmhomepage = nbmh;
        }
        
        /** Getter for property nbmfilename */
        String getNbmFileName () {
            if (this.nbmfilename == null) {
                return ""; // NOI18N
            } else {
                return this.nbmfilename;
            }
        }
        
        /** Setter for property nbmfilename */
        void setNbmFileName (String nbmfn) {
            if (!(nbmfn == null))
                this.nbmfilename = nbmfn;
        }
        
        /** Getter for property nbmneedsrestart */
        String getNbmNeedsRestart () {
            if (this.nbmneedsrestart == null) {
                return ""; // NOI18N
            } else {
                return this.nbmneedsrestart;
            }
        }
        
        /** Setter for property nbmneedsrestart */
        void setNbmNeedsRestart (String nbmnr) {
            if (!(nbmnr == null))
                this.nbmneedsrestart = nbmnr;
        }
        
        /** Setter for property nbmisglobal */
        void setNbmIsGlobal (String isGlobal) {
            if (!(isGlobal == null))
                this.nbmisglobal = isGlobal;
        }
        
        /** Getter for property nbmisglobal */
        String getNbmIsGlobal () {
            return this.nbmisglobal;
        }
        
        /** Getter for property nbmreleasedate */
        String getNbmReleaseDate () {
            if (this.nbmreleasedate == null) {
                return ""; // NOI18N
            } else {
                return this.nbmreleasedate;
            }
        }
        
        /** Setter for property nbmreleasedate */
        void setNbmReleaseDate (String nbmrd) {
            if (!(nbmrd == null ))
                this.nbmreleasedate = nbmrd;
        }
        
        /** Getter for property nbmmoduleauthor */
        String getNbmModuleAuthor () {
            if (this.nbmmoduleauthor == null) {
                return ""; // NOI18N
            } else {
                return this.nbmmoduleauthor;
            }
        }
        
        /** Setter for property nbmmoduleauthor */
        void setNbmModuleAuthor (String nbmma) {
            if (!(nbmma == null))
                this.nbmmoduleauthor = nbmma;
        }
         /** Getter for property files.
         * @return Value of property files.
         */
        List getFiles() {
            return files;
        }
        
        /** Setter for property files.
         * @param files New value of property files.
         */
        void setFiles(List files) {
            this.files = files;
        }
        
        public void addFile( String filename) {
            files.add( filename.replace(File.separatorChar, '/') );
        }
        
        public void putFiles( String[] list ) {
            for (int i=0; i < list.length; i++) {
                if (!files.contains(list[i].replace(File.separatorChar,'/'))) {
                    files.add(list[i].replace(File.separatorChar,'/'));
                }
            }
        }
    }
    
    class ErrorCatcher implements org.xml.sax.ErrorHandler {
        private void message (String level, org.xml.sax.SAXParseException e) {
            pError = true;
        }

        public void error (org.xml.sax.SAXParseException e) {
            // normally a validity error
            pError = true;
        }

        public void warning (org.xml.sax.SAXParseException e) {
            //parseFailed = true;
        }

        public void fatalError (org.xml.sax.SAXParseException e) {
            pError = true;
        }
    }
    
}
