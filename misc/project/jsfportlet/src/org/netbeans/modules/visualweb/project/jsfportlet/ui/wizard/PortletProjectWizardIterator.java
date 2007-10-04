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

import org.netbeans.modules.visualweb.project.jsf.api.JsfProjectConstants;
import org.netbeans.modules.visualweb.project.jsf.api.ProjectTemplate;
import org.netbeans.modules.visualweb.project.jsf.api.JsfProjectUtils;
import com.sun.rave.api.portlet.dd.InitParamType;
import com.sun.rave.api.portlet.dd.JsfPortletDDProperties;
import com.sun.rave.api.portlet.dd.PortletApp;
import com.sun.rave.api.portlet.dd.PortletInfoType;
import com.sun.rave.api.portlet.dd.PortletType;
import org.netbeans.modules.visualweb.project.jsfportlet.JsfPortletProjectTemplate;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.event.ChangeListener;
import javax.xml.parsers.ParserConfigurationException;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectManager;
import org.netbeans.spi.project.support.ant.AntProjectHelper;
import org.netbeans.spi.project.ui.support.ProjectChooser;
import org.openide.ErrorManager;
import org.openide.WizardDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbBundle;
import org.xml.sax.SAXException;

/**
 * This class is a modification of com.sun.rave.project.jsf.ui.wizards.NewJsfProjectWizardIterator.
 * The "initiate" method should be almost IDENTICAL to NewJsfProjectWizardIterator's.
 * @author David Botterill
 */
//TODO refactor NewJsfProjectWizardIterator so it can be extended
public class PortletProjectWizardIterator implements WizardDescriptor.InstantiatingIterator {
    
    static final String PROP_NAME_INDEX = "nameIndex"; //NOI18N
    static File defaultProjectFolder = null;
    private transient int index;
    private transient WizardDescriptor.Panel[] panels;
    private transient WizardDescriptor wiz;
    PanelConfigureProject panelConfigureProject;
    PortletProjectDeploymentDescriptor portletProjectDeploymentDescriptor;
    
    
    /** Creates a new instance of PortletProjectWizardIterator */
    public PortletProjectWizardIterator() {
    }
    
    protected WizardDescriptor.Panel[] createPanels(WizardDescriptor inWiz) {
        panelConfigureProject = new PanelConfigureProject(inWiz);
        portletProjectDeploymentDescriptor = new PortletProjectDeploymentDescriptor(inWiz);
        
        return new WizardDescriptor.Panel [] {panelConfigureProject,portletProjectDeploymentDescriptor};
    }
    
    protected String[] createSteps() {
        return new String[] {
            NbBundle.getMessage(PortletProjectWizardIterator.class, "LBL_Portlet_Step1"), //NOI18N
                    NbBundle.getMessage(PortletProjectWizardIterator.class, "LBL_Portlet_Step2")
                    
        };
    }
    
    public Set instantiate() throws IOException {
        Set resultSet = new HashSet();
        File dirF = (File) wiz.getProperty(WizardProperties.PROJECT_DIR);
        String name = (String) wiz.getProperty(WizardProperties.NAME);
        String servInstID = (String) wiz.getProperty(WizardProperties.SERVER_INSTANCE_ID);
        String sourceStructure = (String)wiz.getProperty(WizardProperties.SOURCE_STRUCTURE);
        String j2eeLevel = (String) wiz.getProperty(WizardProperties.J2EE_LEVEL);
        String contextPath = (String) wiz.getProperty(WizardProperties.CONTEXT_PATH);
        String platformName = (String)wiz.getProperty(WizardProperties.JAVA_PLATFORM);
        String sourceLevel = (String) wiz.getProperty(WizardProperties.SOURCE_LEVEL);
        
        // AntProjectHelper h = WebProjectGenerator.createProject(dirF, name, servInstID, sourceStructure, j2eeLevel, contextPath);
        // XXX Should move this to project/jsfproject
        // AntProjectHelper h = JsfProjectUtils.createWebProject(dirF, name, servInstID, sourceStructure, j2eeLevel, contextPath, platformName, sourceLevel, getTemplate());
        AntProjectHelper h = null;
        FileObject dir = FileUtil.toFileObject(dirF);
        Project project = ProjectManager.getDefault().findProject(dir);
        try {
            FileObject webRoot = project.getProjectDirectory().getFileObject("web");//NOI18N
            // <RAVE> substitute index.jsp with PortletPage1.jsp
            // FileObject indexJSPFo = getIndexJSPFFO(webRoot, "index"); //NOI18N
            // assert indexJSPFo != null : "webRoot: " + webRoot + ", defaultJSP: index";//NOI18N
            FileObject indexJSPFo = getIndexJSPFFO(webRoot, "PortletPage1"); //NOI18N
            JsfProjectUtils.putProjectProperty(project,
                    JsfProjectConstants.PROJECT_SUPPORTS_PORTLETS, "true"); // NOI18N
            Object wizardPortletNameObj = wiz.getProperty(PortletWizardProperties.PORTLET_NAME);
            /**
             * Add name and context path to the project properties.
             */
            if(null != wizardPortletNameObj && wizardPortletNameObj instanceof String) {
                JsfProjectUtils.putProjectProperty(project,
                        JsfProjectConstants.PORTLET_NAME,(String)wizardPortletNameObj); // NOI18N
            }

            if(null != contextPath) {
                JsfProjectUtils.putProjectProperty(project,
                        JsfProjectConstants.PORTLET_CONTEXTPATH,contextPath); // NOI18N
            }
           
            assert indexJSPFo != null : "webRoot: " + webRoot + ", defaultJSP: PortletPage1";//NOI18N
            // </RAVE>
            // Returning FileObject of main class, will be called its preferred action
            resultSet.add(indexJSPFo);
        } catch (Exception e) {
            ErrorManager.getDefault().notify(e);
        }
        
        Integer index = (Integer) wiz.getProperty(PROP_NAME_INDEX);
        if(index != null) {
            FoldersListSettings.getDefault().setNewProjectCount(index.intValue());
        }
        
        setPortletDDValues(project,wiz);
        
        
        wiz.putProperty(WizardProperties.NAME, null); // reset project name
        
//        Project earProject = (Project) wiz.getProperty(WizardProperties.EAR_APPLICATION);
//        WebProject createdWebProject = (WebProject) ProjectManager.getDefault().findProject(dir);
//        if (earProject != null && createdWebProject != null) {
//            Ear ear = Ear.getEar(earProject.getProjectDirectory());
//            if (ear != null) {
//                ear.addWebModule(createdWebProject.getAPIWebModule());
//            }
//        }
        
        // save last project location
        dirF = (dirF != null) ? dirF.getParentFile() : null;
        if (dirF != null && dirF.exists()) {
            ProjectChooser.setProjectsFolder(dirF);
        }
        
        resultSet.add(dir);
        
        // Returning set of FileObject of project diretory.
        // Project will be open and set as main
        return resultSet;
    }
    
    private void setPortletDDValues(Project inProject,WizardDescriptor inWiz) {
        PortletApp portletApp = null;
        
        /**
         * First get the portlet.xml file from the project directory
         */
        FileObject webRootFO = inProject.getProjectDirectory().getFileObject("web/WEB-INF");//NOI18N
        if(null == webRootFO) {
            // TODO write some log message
            return;
        }
        FileObject portletXMLFO = webRootFO.getFileObject("portlet.xml"); //NOI18N
        File portletXMLFile = FileUtil.toFile(portletXMLFO);
        
        try {
            /**
             * Create the portlet application
             */
            portletApp = PortletApp.read(portletXMLFile);
            
            if(null!=portletApp) {
                /**
                 * Now get the Portlets in the portlet application.
                 * NOTE- we only expect there to be one portlet per project so
                 * we will only deal with the first portlet.
                 */
                PortletType [] portletType = portletApp.getPortlet();
                if(null != portletType) {
                    /**
                     * NAME
                     *
                     * Set the portlet Name
                     */
                    
                    Object wizardPortletNameObj = inWiz.getProperty(PortletWizardProperties.PORTLET_NAME);
                    if(null != wizardPortletNameObj && wizardPortletNameObj instanceof String) {
                        portletType[0].setPortletName((String)wizardPortletNameObj);
                    }
                  
                    /**
                     * DISPLAYNAME
                     *
                     * Set the portlet display name. This is two parts. The DD
                     * can have multiple locale specific display names each with a
                     * display name and a locale.
                     *
                     * Note-IF and WHEN we support multiple locales to be input in Creator,
                     * we'll need to change this code to handle all the locales.
                     *
                     * First we remove all the display names then add our own.
                     */
                    //TODO support multiple locales
                    
                    String [] displayNames = portletType[0].getDisplayName();
                    for(int ii=0; null != displayNames && ii < displayNames.length;ii++) {
                        portletType[0].removeDisplayName(displayNames[ii]);
                    }
                    
                    Object wizardPortletDisplayNameObj = inWiz.getProperty(PortletWizardProperties.PORTLET_DISPLAYNAME);
                    if(null != wizardPortletDisplayNameObj && wizardPortletDisplayNameObj instanceof String) {
                        portletType[0].addDisplayName((String)wizardPortletDisplayNameObj);
                    }
                    
                    /**
                     * DESCRIPTION
                     *
                     * We need to do the same with descriptions as with the display name.
                     */
                    //TODO support multiple locales
                    String [] descriptions = portletType[0].getDescription();
                    for(int ii=0; null != descriptions && ii < descriptions.length;ii++) {
                        portletType[0].removeDescription(descriptions[ii]);
                    }
                    
                    Object wizardPortletDescObj = inWiz.getProperty(PortletWizardProperties.PORTLET_DESC);
                    if(null != wizardPortletDescObj && wizardPortletDescObj instanceof String) {
                        portletType[0].addDescription((String)wizardPortletDescObj);
                    }
                    
                    /**
                     * TITLE
                     * SHORTTITLE
                     *
                     * remove the PortletInfo and add a new one.
                     *
                     */
                    
                    PortletInfoType portletInfoType = portletType[0].getPortletInfo();
                    if(null != portletInfoType) {
                        Object wizardPortletTitleObj = inWiz.getProperty(PortletWizardProperties.PORTLET_TITLE);
                        if(null != wizardPortletTitleObj && wizardPortletTitleObj instanceof String) {
                            portletInfoType.setTitle((String)wizardPortletTitleObj);
                        }
                        Object wizardPortletShortTitleObj = inWiz.getProperty(PortletWizardProperties.PORTLET_SHORTTITLE);
                        if(null != wizardPortletShortTitleObj && wizardPortletShortTitleObj instanceof String) {
                            portletInfoType.setShortTitle((String)wizardPortletShortTitleObj);
                        }
                        
                    }
                    
                    
                    /**
                     * INITIAL_VIEW
                     *
                     * Set the initial view page for the JSF-Portlet bridge.  We can have
                     * multiple "init_param"s so remove all of them and then add ours.
                     *
                     */
                    //TODO handle user init_params
                    InitParamType [] initParams = portletType[0].getInitParam();
                    for(int ii=0; null != initParams && ii < initParams.length;ii++) {
                        portletType[0].removeInitParam(initParams[ii]);
                    }
                    
                    InitParamType initialViewInitParam = new InitParamType(JsfPortletDDProperties.INIT_VIEW,"/PortletPage1.jsp"); //NOI18N
                    //TODO There should be a better way to get the name of the default first page, "PortletPage1.jsp".
                    portletType[0].addInitParam(initialViewInitParam);
                    
                    
                    
                    
                }
                
                portletApp.write(portletXMLFile);
                
                
            }
            
        } catch(IOException ioe) {
            ErrorManager.getDefault().notify(ioe);
        } catch(ParserConfigurationException pce) {
            ErrorManager.getDefault().notify(pce);
        } catch(SAXException se) {
            ErrorManager.getDefault().notify(se);
        }
    }
    
    
    public void initialize(WizardDescriptor wiz) {
        this.wiz = wiz;
        index = 0;
        panels = createPanels(wiz);
        // Make sure list of steps is accurate.
        String[] steps = createSteps();
        wiz.putProperty("WizardPanel_contentData", steps); // NOI18N
        
        for (int i = 0; i < panels.length; i++) {
            Component c = panels[i].getComponent();
            if (steps[i] == null) {
                // Default step name to component name of panel.
                // Mainly useful for getting the name of the target
                // chooser to appear in the list of steps.
                steps[i] = c.getName();
            }
            if (c instanceof JComponent) { // assume Swing components
                JComponent jc = (JComponent)c;
                // Step #.
                jc.putClientProperty("WizardPanel_contentSelectedIndex", new Integer(i)); // NOI18N
                // Step name (actually the whole list for reference).
                jc.putClientProperty("WizardPanel_contentData", steps); // NOI18N
            }
        }
    }
    public void uninitialize(WizardDescriptor wiz) {
        this.wiz.putProperty(WizardProperties.PROJECT_DIR,null);
        this.wiz.putProperty(WizardProperties.NAME,null);
        this.wiz = null;
        panels = null;
    }
    
    public String name() {
        return MessageFormat.format(NbBundle.getMessage(PortletProjectWizardIterator.class, "LBL_WizardStepsCount"), new String[] {(new Integer(index + 1)).toString(), (new Integer(panels.length)).toString()}); //NOI18N
    }
    
    public boolean hasNext() {
        return index < panels.length - 1;
    }
    public boolean hasPrevious() {
        return index > 0;
    }
    public void nextPanel() {
        if (!hasNext()) throw new NoSuchElementException();
        index++;
        panelConfigureProject.readSettings(wiz);
        portletProjectDeploymentDescriptor.readSettings(wiz);
        
    }
    public void previousPanel() {
        if (!hasPrevious()) throw new NoSuchElementException();
        index--;
    }
    public WizardDescriptor.Panel current() {
        return panels[index];
    }
    
    // If nothing unusual changes in the middle of the wizard, simply:
    public final void addChangeListener(ChangeListener l) {}
    public final void removeChangeListener(ChangeListener l) {}
    
    // helper methods, finds indexJSPF's FileObject
    private FileObject getIndexJSPFFO(FileObject webRoot, String indexJSP) {
        // replace '.' with '/'
        indexJSP = indexJSP.replace('.', '/'); // NOI18N
        
        // ignore unvalid mainClass ???
        
        return webRoot.getFileObject(indexJSP, "jsp"); // NOI18N
    }
    
    /**
     * Provides the project template to be used to instantiate the project contents
     * May be overridden by subclasses to produce project variants
     * @return The project template class that will perform the instantiation of project contents
     */
    protected ProjectTemplate getTemplate() {
        return new JsfPortletProjectTemplate();
    }
    
}
