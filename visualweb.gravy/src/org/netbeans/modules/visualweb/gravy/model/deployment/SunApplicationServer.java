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

package org.netbeans.modules.visualweb.gravy.model.deployment;

/**
 * Class representing Sun Application Servers.
 */

public class SunApplicationServer extends ApplicationServer {
    
    public final static String SERVER_START_PROFILE = "Start in Profile Mode";
    public final static String RESOURCE_DELETE = "Delete Resource";
    public final static String applications_node = "Applications";
    public final static String web_applications_node = "Web Applications";
    public final static String resources_node = "Resources";
    public final static String jdbc_node = "JDBC";
    public final static String jdbc_resources_node = "JDBC Resources";
    
    {
        this.SERVER_VIEW_LOG = "View Server Log";
        this.APPLICATION_DISABLE = "Disable";
        this.APPLICATION_ENABLE = "Enable";
        this.server_popup = new String[][] {{SERVER_START, "false", "true"}, {SERVER_START_DEBUG, "false", "true"},
                                            {SERVER_START_PROFILE, "false", "true"}, {SERVER_RESTART, "true", "false"},
                                            {SERVER_STOP, "true", "false"}, {REFRESH, "true", "true"},
                                            {SERVER_REMOVE, "true", "true"},/* {SERVER_CUSTOMIZE, "true", "true"},
                                            {PROPERTIES, "true", "true"},*/ {SERVER_VIEW_CONSOLE, "true", "true"},
                                            {SERVER_VIEW_LOG, "true", "true"}, {PROPERTIES, "true", "true"}};
        this.application_popup = new String[] {APPLICATION_UNDEPLOY, APPLICATION_DISABLE, PROPERTIES};
        this.app_pref = "";
    }

    /**
     * Creates SunApplicationServer with specified descriptor.
     * @param DTDescriptor Descriptor of Sun Application Server.
     */
    public SunApplicationServer(DeploymentTargetDescriptor DTDescriptor) {
        setDescriptor(DTDescriptor);
        this.web_applications_path = getName() + "|" + applications_node + "|" + web_applications_node;
        this.jdbc_resources_path = getName() + "|" + resources_node + "|" + jdbc_node + "|" + jdbc_resources_node;
        String serverType = DTDescriptor.getProperty(DTDescriptor.SERVER_TYPE_KEY);
        this.requestPrefix = DTDescriptor.getProperty(DTDescriptor.REQUEST_PREFIX_KEY + serverType);
    }
}
