/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
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
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
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

package org.netbeans.performance.visualweb.footprint;

import org.netbeans.jellytools.WizardOperator;
import org.netbeans.jellytools.actions.ActionNoBlock;

import org.netbeans.jemmy.operators.ComponentOperator;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JTextComponentOperator;

/**
 *
 * @author mkhramov@netbeans.org, mmirilovic@netbeans.org
 */
public class VisualWebProjectWorkflow extends org.netbeans.modules.performance.utilities.MemoryFootprintTestCase {
    
    private String webproject;
    
    /**
     * Creates a new instance of VisualWebProjectWorkflow
     * @param testName 
     */
    public VisualWebProjectWorkflow(String testName) {
        super(testName);
        prefix = "Visual Web Project Workflow |";
    }
    
    /**
     * Creates a new instance of VisualWebProjectWorkflow
     */
    public VisualWebProjectWorkflow(String testName, String performanceDataName) {
        super(testName,performanceDataName);
        prefix = "Visual Web Project Workflow |";
    }
    
    public void prepare() {
    }
    
    public ComponentOperator open() {
        
        //webproject = FootprintUtilities.createproject("Web", "Visual Web Application", false);
        webproject = "HugeApp";
/*
        VWPFootprintUtilities.waitProjectOpenedScanFinished(System.getProperty("xtest.tmpdir")+ java.io.File.separator +webproject); // failing openProject(webproject);
        VWPFootprintUtilities.waitForPendingBackgroundTasks();
        VWPFootprintUtilities.openFile(webproject,webproject.toLowerCase(),"ApplicationBean1.java",true);
        VWPFootprintUtilities.buildproject(webproject);
*/        //TODO this takes ages / minutes ;( VWPFootprintUtilities.deployProject(webproject);
        
        return null;
    }

    @Override
    public void setUp() {
        // do nothing
    }
    
    @Override
    public void initialize() {
        super.initialize();
//        VWPFootprintUtilities.closeAllDocuments();
//        VWPFootprintUtilities.closeMemoryToolbar();
    }
    
    @Override
    public void close() {
        log("::close");
//        VWPFootprintUtilities.closeAllDocuments();
//        ProjectSupport.closeProject(webproject);
    }
    
    private void openProject(String projectName) {
        new ActionNoBlock("File|Open Project...",null).perform(); // NOI18N
        WizardOperator wizard = new WizardOperator("Open Project");  // NOI18N
        JTextComponentOperator path = new JTextComponentOperator(wizard,1);
        
        String buttonCaption = org.netbeans.jellytools.Bundle.getStringTrimmed("org.netbeans.modules.project.ui.Bundle", "BTN_PrjChooser_ApproveButtonText");
        JButtonOperator openButton = new JButtonOperator(wizard,buttonCaption);
        
        String paths = System.getProperty("xtest.tmpdir")+ java.io.File.separator +projectName;
        path.setText(paths);
        openButton.pushNoBlock();
    }
    
}
