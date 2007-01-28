/*
 * {START_JAVA_COPYRIGHT_NOTICE
 * Copyright 2004 Sun Microsystems, Inc. All rights reserved.
 * Use is subject to license terms.
 * END_COPYRIGHT_NOTICE}
 */

package org.netbeans.modules.visualweb.project.jsfportlet;

import org.netbeans.modules.visualweb.project.jsf.api.JsfProjectConstants;
import org.netbeans.modules.visualweb.project.jsf.api.ProjectTemplate;
import org.netbeans.modules.visualweb.project.jsf.api.JsfProjectClassPathExtender;
import org.netbeans.modules.visualweb.project.jsf.api.JsfProjectUtils;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.libraries.Library;
import org.netbeans.api.project.libraries.LibraryManager;
import org.netbeans.modules.j2ee.deployment.devmodules.api.J2eeModule;
import org.openide.ErrorManager;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 *
 * @author David Botterill
 */
public class JsfPortletProjectTemplate extends ProjectTemplate {
    private static final String DEFAULT_THEME = "theme-default";

    /** Creates a new instance of JsfProjectTemplate */
    public JsfPortletProjectTemplate() {
    }
    
    public void addLibrary(Project project) throws IOException {
        // Add the Creator libraries to the project
        LibraryManager libMgr = LibraryManager.getDefault();
        Library[] designLibs = {
            libMgr.getLibrary("jsfsupport-designtime"),
            libMgr.getLibrary("webui-designtime"),
            libMgr.getLibrary("jdbcsupport-designtime"),
            libMgr.getLibrary(DEFAULT_THEME),
        };
        
        Library[] runLibs = {
            libMgr.getLibrary("jsfsupport-runtime"),
            libMgr.getLibrary("portlet-runtime"),
            libMgr.getLibrary("jsf-portlet-runtime"),
            libMgr.getLibrary("jdbcsupport-runtime"),
            libMgr.getLibrary("exceptionhandler-runtime"),
            libMgr.getLibrary(DEFAULT_THEME),
        };

        if (JsfProjectUtils.isJsfProject(project)) { // XXX Thresher project
            JsfProjectUtils.addLibraryReferences(project, designLibs, JsfProjectClassPathExtender.LIBRARY_ROLE_DESIGN);
            JsfProjectUtils.addLibraryReferences(project, runLibs, JsfProjectClassPathExtender.LIBRARY_ROLE_DEPLOY);
            JsfProjectUtils.addLocalizedThemeArchive(project, DEFAULT_THEME);
	} else {
            JsfProjectUtils.addLibraryReferences(project, designLibs, JsfProjectClassPathExtender.LIBRARY_ROLE_DESIGN);
            JsfProjectUtils.addLibraryReferences(project, runLibs, JsfProjectClassPathExtender.LIBRARY_ROLE_DEPLOY);
            JsfProjectUtils.addLocalizedThemeArchive(project, DEFAULT_THEME);
        }
        JsfProjectUtils.putProjectProperty(project, JsfProjectConstants.PROP_CURRENT_THEME, DEFAULT_THEME); // NOI18N
    }

    public void create(Project project, String j2eeLevel) throws IOException {
        // Extract the project template file for this project type
        // TODO: support BluePrints source structure as well...
        InputStream is = JsfPortletProjectTemplate.class.getResourceAsStream("/org/netbeans/modules/visualweb/project/jsfportlet/JsfPortletProjectTemplateJakarta.xml"); // NOI18N
        Document document = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Node node;
        
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(is);
            FileObject projDir = project.getProjectDirectory();
            instantiateFile(project, document.getDocumentElement(), project.getProjectDirectory(), j2eeLevel);
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            ErrorManager.getDefault().notify(e);
        }
    }

    public void instantiateFile(Project project, Node node, FileObject folder, String j2eeLevel) throws IOException {
        if (folder.equals(project.getProjectDirectory())) {
            JsfProjectUtils.putProjectProperty(project, JsfProjectConstants.PROP_JSF_PROJECT_LIBRARIES_DIR, JsfProjectConstants.PATH_LIBRARIES);
        }
        
        if (j2eeLevel == null) {
            j2eeLevel = J2eeModule.JAVA_EE_5;
        }
                
        String nodeName = node.getNodeName();
        String fileName = getAttr(node, "name"); // NOI18N
        String templateJ2eeLevel = getAttr(node, "j2eeLevel"); // NOI18N
        
        if (templateJ2eeLevel.length() != 0 && !j2eeLevel.equals(templateJ2eeLevel)) {
                                    // Wrong Java EE level
            return;
        }
        
        if (nodeName.equals("file") && folder.getFileObject(fileName) == null) {
            instantiateFileTemplate(folder, fileName, getAttr(node, "template"));  // NOI18N
        } else {
            FileObject newFolder = null;
            if (nodeName.equals("folder")) {  // NOI18N
                if (fileName.equals("${" + JsfProjectConstants.PROP_JSF_PAGEBEAN_PACKAGE + "}")) {
                    fileName = getBeanPackage();
                    if (fileName == null) {
                        fileName = deriveSafeName(project.getProjectDirectory().getName());
                    }
                    JsfProjectUtils.putProjectProperty(project, JsfProjectConstants.PROP_JSF_PAGEBEAN_PACKAGE, fileName);
                }
                fileName = fileName.replace('.',  '/');
                if ((newFolder = folder.getFileObject(fileName)) == null) {
                    // <RAVE> folder.createFolder does NOT deal with nested folders being created
                    newFolder = FileUtil.createFolder(folder, fileName);
//                    newFolder = folder.createFolder(fileName);  // NOI18N
                    // </RAVE>
                }
            } else {
                newFolder = folder;
            }
            NodeList nodes = node.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                instantiateFile(project, nodes.item(i), newFolder, j2eeLevel);
            }
        }
    }
}
