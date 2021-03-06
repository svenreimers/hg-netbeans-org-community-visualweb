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

package org.netbeans.test.deployment.tomcat;

import org.netbeans.junit.NbTestSuite;
import org.netbeans.test.deployment.generic.DeploymentAcceptanceTest;
import org.netbeans.test.deployment.generic.DeploymentTest;

public class TomcatTestSuite extends NbTestSuite {
    
    private TomcatTestSuite(String name) {
        super(name);
    }
    
    /**
     * Returns Tomcat specific instance of the TestSuite
     *
     * @return instance of the TestSuite
     */
    public static NbTestSuite suite() {
        TomcatTestSuite suite = new TomcatTestSuite("Tomcat Deployment Tests");
//        DeploymentTest.serverType = "Tomcat 5.0";
//        DeploymentAcceptanceTest.serverType = "Tomcat 5.0";
        DeploymentTest.serverType = "Tomcat 5.5";
        DeploymentAcceptanceTest.serverType = "Tomcat 5.5";
//        DeploymentTest.serverType = "Tomcat 6.0";
//        DeploymentAcceptanceTest.serverType = "Tomcat 6.0";
        suite.addTest(new DeploymentTest("testStress"));
        suite.addTest(new DeploymentAcceptanceTest("testOpenServerContextMenu"));
        suite.addTest(new DeploymentTest("testCloseServerContextMenu"));
        suite.addTest(new DeploymentTest("testCorrectnessServerContextMenu"));
        suite.addTest(new DeploymentTest("testCorrectnessServerContextMenuAfterChangeServerState"));
        suite.addTest(new DeploymentTest("testServerLog"));
        suite.addTest(new DeploymentTest("testServerProperties"));
        suite.addTest(new DeploymentTest("testOpenDeployedComponentsContextMenu"));
        suite.addTest(new DeploymentTest("testCloseDeployedComponentsContextMenu"));
        suite.addTest(new DeploymentTest("testRefreshDeployedComponents"));
        suite.addTest(new DeploymentAcceptanceTest("testDeploySimpleProject"));
        suite.addTest(new DeploymentAcceptanceTest("testRedeploySimpleProject"));
        suite.addTest(new DeploymentAcceptanceTest("testOpenApplicationContextMenu"));
        suite.addTest(new DeploymentTest("testCloseApplicationContextMenu"));
        suite.addTest(new DeploymentTest("testDisableApplication"));
        suite.addTest(new DeploymentTest("testEnableApplication"));
        suite.addTest(new DeploymentAcceptanceTest("testUndeployApplication"));
        if (DeploymentAcceptanceTest.serverType.equals("Tomcat 6.0"))
            suite.addTest(new DeploymentAcceptanceTest("testDeploySimpleJavaEE5Project"));
        suite.addTest(new DeploymentAcceptanceTest("testDeployDBProject"));
        suite.addTest(new DeploymentAcceptanceTest("testRedeployDBProject"));
        suite.addTest(new DeploymentTest("testDeployWSProject"));
        return suite;
    }
}
