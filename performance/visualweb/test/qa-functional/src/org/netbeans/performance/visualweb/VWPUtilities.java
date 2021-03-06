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

package org.netbeans.performance.visualweb;

import org.netbeans.performance.visualweb.WebFormDesignerOperator;

import org.netbeans.jellytools.MainWindowOperator;
import org.netbeans.jellytools.NbDialogOperator;
import org.netbeans.jellytools.ProjectsTabOperator;
import org.netbeans.jellytools.actions.OpenAction;
import org.netbeans.jellytools.nodes.Node;
import org.netbeans.jellytools.nodes.ProjectRootNode;
import org.netbeans.jemmy.JemmyException;
import org.netbeans.jemmy.TimeoutExpiredException;
import org.netbeans.jemmy.operators.JListOperator;
import org.netbeans.jemmy.operators.JMenuBarOperator;
import org.netbeans.jemmy.operators.JMenuItemOperator;
import org.netbeans.modules.performance.utilities.CommonUtilities;

/**
 * Utilities for Performance tests, workarrounds, often used methods, ...
 *
 * @author  mmirilovic@netbeans.org
 */
public class VWPUtilities extends CommonUtilities {

    private static final String menuItemName = org.netbeans.jellytools.Bundle.getString("org.netbeans.modules.web.project.ui.Bundle", "LBL_Fix_Missing_Server_Action");
    private static final String dialogName = org.netbeans.jellytools.Bundle.getString("org.netbeans.modules.j2ee.common.ui.Bundle", "LBL_Resolve_Missing_Server_Title");
    public static final String WEB_PAGES = org.netbeans.jellytools.Bundle.getStringTrimmed("org.netbeans.modules.web.project.ui.Bundle", "LBL_Node_DocBase");

    /**
     * open jsp file from project and return WebFormDesigner
     * @param projectName name of the project
     * @param jspFileName name of the file
     * @return Visual Web Designer
     */
    public static WebFormDesignerOperator openedWebDesignerForJspFile(String projectName, String jspFileName){
        Node openFile = new Node(new ProjectsTabOperator().getProjectRootNode(projectName),WEB_PAGES + "|" +jspFileName + ".jsp");
        new OpenAction().performPopup(openFile);
        
        WebFormDesignerOperator surface = WebFormDesignerOperator.findWebFormDesignerOperator(jspFileName);
        surface.switchToDesignView();

        return surface;
    }
    
    public static void verifyAndResolveMissingWebServer(String projectName, String serverName) {
        ProjectRootNode projectNode = new ProjectsTabOperator().getProjectRootNode(projectName);
             
        if(!isServerMissingMenuAvaialable(projectName)) {
            return;
        }
        
        projectNode.performPopupActionNoBlock(menuItemName);
        
        NbDialogOperator missingServerDialog = new NbDialogOperator(dialogName);
        JListOperator serversList = new JListOperator(missingServerDialog);
        //serversList.selectItem(serverName);
        serversList.selectItem(0);
        missingServerDialog.ok();
        
    }
    private static boolean isServerMissingMenuAvaialable(String projectName) {
        ProjectRootNode projectNode = new ProjectsTabOperator().getProjectRootNode(projectName);
        try {
            projectNode.verifyPopup(menuItemName);
        } catch(JemmyException jex) {
            return false;
        }
        return true;
    }
    // Usage: VWPUtilities.invokePTO();
    public static ProjectsTabOperator invokePTO() {
        ProjectsTabOperator testOp = null;
        try {
            testOp = new ProjectsTabOperator();
        } catch (TimeoutExpiredException tex) {
            MainWindowOperator mv = MainWindowOperator.getDefault();
            JMenuBarOperator menuBar = mv.menuBar();
            //menuBar.pushMenu("Window|Projects");
            JMenuItemOperator item = menuBar.showMenuItem("Window|Projects");
            item.clickMouse();
            testOp = new ProjectsTabOperator();
        }       
        return testOp;
    }    
}
