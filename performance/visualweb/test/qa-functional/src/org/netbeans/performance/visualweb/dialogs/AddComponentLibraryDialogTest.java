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

package org.netbeans.performance.visualweb.dialogs;

import org.netbeans.jellytools.Bundle;
import org.netbeans.jellytools.NbDialogOperator;
import org.netbeans.jellytools.ProjectsTabOperator;
import org.netbeans.jellytools.nodes.Node;
import org.netbeans.jemmy.JemmyProperties;
import org.netbeans.jemmy.operators.ComponentOperator;
import org.netbeans.junit.NbTestSuite;
import org.netbeans.junit.NbModuleSuite;

import org.netbeans.modules.performance.utilities.PerformanceTestCase;
import org.netbeans.performance.visualweb.setup.VisualWebSetup;
/**
 *
 * @author mkhramov@netbeans.org, mmirilovic@netbeans.org
 */
public class AddComponentLibraryDialogTest extends PerformanceTestCase {
    
    private Node openNode;

    /** Creates a new instance of AddComponentLibraryDialogTest */
    public AddComponentLibraryDialogTest(String testName) {
        super(testName);
        expectedTime = WINDOW_OPEN;
    }
    
    public AddComponentLibraryDialogTest(String testName, String performanceDataName) {
        super(testName,performanceDataName);
        expectedTime = WINDOW_OPEN;
    }

    public static NbTestSuite suite() {
        NbTestSuite suite = new NbTestSuite();
        suite.addTest(NbModuleSuite.create(NbModuleSuite.createConfiguration(VisualWebSetup.class)
             .addTest(AddComponentLibraryDialogTest.class)
             .enableModules(".*").clusters(".*")));
        return suite;
    }
    
    public void testAddComponentLibraryDialog() {
        doMeasurement();
    }
    
    @Override
    protected void initialize() {
        log("::initialize");
        Node projectRoot = null;
        long oldTimeOut = JemmyProperties.getCurrentTimeout("ComponentOperator.WaitStateTimeout");
        JemmyProperties.setCurrentTimeout("ComponentOperator.WaitStateTimeout", 120000);
        try {
            projectRoot = new ProjectsTabOperator().getProjectRootNode("UltraLargeWA");
            projectRoot.select();
            
        } catch (org.netbeans.jemmy.TimeoutExpiredException ex) {
            fail("Cannot find and select project root node");
        }
        String nodePath = Bundle.getString("org.netbeans.modules.visualweb.complib.ui.Bundle", "ComplibsRootNode.displayName");
        log("Node path = "+nodePath);
        openNode = new Node(projectRoot,nodePath);
        JemmyProperties.setCurrentTimeout("ComponentOperator.WaitStateTimeout", oldTimeOut);
    }
    
    public void prepare() {
    }

    public ComponentOperator open() {
        openNode.callPopup().pushMenu(Bundle.getString("org.netbeans.modules.visualweb.complib.ui.Bundle", "ComplibsRootNode.addComplibAction"));
        return new NbDialogOperator(Bundle.getString("org.netbeans.modules.visualweb.complib.ui.Bundle", "ComplibsRootNode.addComplibTitle"));
    }

    @Override
    public void close() {
        super.close();
    }
}
