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

/*
 * DesignTimeDataSourceServiceImpl.java
 *
 * Created on June 2, 2006, 5:54 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.netbeans.modules.visualweb.project.jsf.services;

import org.netbeans.modules.j2ee.deployment.common.api.ConfigurationException;
import org.netbeans.modules.visualweb.api.j2ee.common.RequestedJdbcResource;
import java.util.HashSet;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.netbeans.api.db.explorer.ConnectionManager;
import org.netbeans.api.db.explorer.DatabaseConnection;

import org.netbeans.api.project.Project;
import org.netbeans.modules.j2ee.deployment.common.api.Datasource;
import org.netbeans.modules.j2ee.deployment.common.api.DatasourceAlreadyExistsException;
import org.netbeans.modules.j2ee.deployment.devmodules.spi.J2eeModuleProvider;

import org.openide.ErrorManager;
import org.openide.util.Utilities;

/**
 *
 * @author marcow
 */
@org.openide.util.lookup.ServiceProvider(service=org.netbeans.modules.visualweb.project.jsf.services.DesignTimeDataSourceService.class)
public class DesignTimeDataSourceServiceImpl implements DesignTimeDataSourceService {
    static private final String DATASOURCE_PREFIX = "java:comp/env/"; // NOI18N
    static private final String NON_DEFAULT_DATASOURCE_PREFIX = "java:/"; // NOI18N
    
    /** Creates a new instance of DesignTimeDataSourceServiceImpl */
    public DesignTimeDataSourceServiceImpl() {
    }

    public boolean updateProjectDataSource(Project project, RequestedJdbcResource req) {
        J2eeModuleProvider jmp =
                (J2eeModuleProvider)project.getLookup().lookup(J2eeModuleProvider.class);
        Set  dss = null;
        try {
            dss = jmp.getModuleDatasources();
        } catch (ConfigurationException e) {
            dss = new HashSet();
            // TODO: give some feedback to the user
        }
        boolean notFound = true;
        Iterator it = dss.iterator();

        while (it.hasNext()) {
            Datasource ds = (Datasource)it.next();

            if (req.getResourceName().equals(ds.getJndiName()) &&
                    req.getDriverClassName().equals(ds.getDriverClassName()) &&
                    (req.getUrl() != null && req.getUrl().equals(ds.getUrl()) || req.getUrl() == null && ds.getUrl() == null) &&
                    (req.getUsername() != null && req.getUsername().equals(ds.getUsername()) || req.getUsername() == null && ds.getUsername() == null) &&
                    (req.getPassword() != null && req.getPassword().equals(ds.getPassword()) || req.getPassword() == null && ds.getPassword() == null)) {
                notFound = false;
            }
        }

        if (notFound) {
            try {
                jmp.getConfigSupport().createDatasource(req.getResourceName(), req.getUrl(),
                        req.getUsername(), req.getPassword(), req.getDriverClassName());
            } catch (ConfigurationException e) {
                // TODO: give some feedback to the user
            } catch (DatasourceAlreadyExistsException e) {
                List dsList = e.getDatasources();

                if (dsList.size() != 1) {
                    ErrorManager.getDefault().log(ErrorManager.WARNING,
                            "DS - " + req.getResourceName() + ": Here should only one DS exist!");
                    ErrorManager.getDefault().notify(ErrorManager.INFORMATIONAL, e);

                    return false;
                }
                else {
                    Datasource eDs = (Datasource)dsList.get(0);

                            // Compare with the DS we wanted to create.
                    if (eDs.getJndiName().equals(req.getResourceName()) &&
                            eDs.getDriverClassName().equals(req.getDriverClassName()) &&
                            (eDs.getUrl() != null && eDs.getUrl().equals(req.getUrl()) || eDs.getUrl() == null && req.getUrl() == null) &&
                            (eDs.getUsername() != null && eDs.getUsername().equals(req.getUsername()) || eDs.getUsername() == null && req.getUsername() == null) &&
                            (eDs.getPassword() != null && eDs.getPassword().equals(req.getPassword()) || eDs.getPassword() == null && req.getPassword() == null)) {
                        ErrorManager.getDefault().log(ErrorManager.INFORMATIONAL,
                                "DS - " + req.getResourceName() + ": OK, we have the same DS on the server already. I hope there is nothing else to configure for this project.");
                    }
                    else {
                        ErrorManager.getDefault().log(ErrorManager.WARNING,
                                "DS - " + req.getResourceName() + ": There is a DS on the server with the same JNDI name but different parameters!  Trouble ahead!!");
                        ErrorManager.getDefault().notify(ErrorManager.WARNING, e);
                        
                        return false;
                    }
                }
            }
        }
            
        return true;
    }
    
    // create a JNDI name for resource reference name
    public boolean updateResourceReference(Project project, RequestedJdbcResource req) {
        J2eeModuleProvider jmp = project.getLookup().lookup(J2eeModuleProvider.class);
        Set dss = null;
        try {
            dss = jmp.getModuleDatasources();
        } catch (ConfigurationException e) {
            dss = new HashSet();
            // TODO: give some feedback to the user
        }
        
        Iterator it = dss.iterator();        

        while (it.hasNext()) {
            Datasource ds = (Datasource) it.next();
            try {
                if ((req.getUrl().equals(ds.getUrl())) && (req.getUsername().equals(ds.getUsername()))) {
                    jmp.getConfigSupport().bindDatasourceReference(req.getResourceName(), ds.getJndiName());
                }
            } catch (ConfigurationException e) {
                return false;
            }
        } 
        
        return true;
    }
    
    public Set<RequestedJdbcResource> getServerDataSources(Project p) {
        J2eeModuleProvider jmp =
                (J2eeModuleProvider)p.getLookup().lookup(J2eeModuleProvider.class);
        Set<Datasource> dss = null;
        try {
            dss = jmp.getServerDatasources();
        } catch (ConfigurationException e) {
            dss = new HashSet<Datasource>();
            // TODO: give some feedback to the user
        }
        Set<RequestedJdbcResource> reqDss = new HashSet<RequestedJdbcResource>();
        Iterator<Datasource> it = dss.iterator();
        
        // stripDATASOURCE_PREFIX is a hack for JBoss and other application servers due to differences in JNDI string format
        // for issue 101812
        while (it.hasNext()) {
            Datasource ds = it.next();
            RequestedJdbcResource r = new RequestedJdbcResource(stripDATASOURCE_PREFIX(ds.getJndiName()),ds.getDriverClassName(),
                    ds.getUrl(), ds.getUsername(), ds.getPassword());
                        
            reqDss.add(r);
        }
        
        return reqDss;
    }
    
    public Set<RequestedJdbcResource> getProjectDataSources(Project p) {
        J2eeModuleProvider jmp =
                (J2eeModuleProvider)p.getLookup().lookup(J2eeModuleProvider.class);
        
        Set<Datasource> dss = null;
        try {
            dss = jmp.getModuleDatasources();
        } catch (ConfigurationException e) {
            dss = new HashSet<Datasource>();
            // TODO: give some feedback to the user
        }
        
        Set<RequestedJdbcResource> reqDss = new HashSet<RequestedJdbcResource>();
        Iterator<Datasource> it = dss.iterator();
        
        // stripDATASOURCE_PREFIX is a hack for JBoss and other application servers due to differences in JNDI string format
        // for issue 101812
        while (it.hasNext()) {
            Datasource ds = it.next();
            RequestedJdbcResource r = new RequestedJdbcResource(stripDATASOURCE_PREFIX(ds.getJndiName()),ds.getDriverClassName(),
                    ds.getUrl(), ds.getUsername(), ds.getPassword());
            
            reqDss.add(r);
        }
        
        return reqDss;
    }
    
    private String stripDATASOURCE_PREFIX(String orig) {
        if (orig == null) {
            return orig;
        }
        if (orig.startsWith(NON_DEFAULT_DATASOURCE_PREFIX)) {
            return orig.substring(NON_DEFAULT_DATASOURCE_PREFIX.length());
        }
        if (!orig.startsWith(DATASOURCE_PREFIX)) {
            return orig;
        }
        if (orig.equals(DATASOURCE_PREFIX)) {
            return orig;
        }
        return orig.substring(DATASOURCE_PREFIX.length());
    }
    
      /**
     * Returns set of broken datasources
     *
     * @param project
     * @return Set<Datasource>  returns a set of data sources that don't have corresponding database connections
     */
    public Set<RequestedJdbcResource> getBrokenDatasources(Project project) {
        J2eeModuleProvider jmp =
                (J2eeModuleProvider)project.getLookup().lookup(J2eeModuleProvider.class);
        
        Set<Datasource> dss = null;
        try {
            dss = jmp.getModuleDatasources();
        } catch (ConfigurationException e) {
            dss = new HashSet<Datasource>();
        }
        
        Set<Datasource> brokenDatasources = new HashSet<Datasource>();
        Iterator<Datasource> it = dss.iterator();
        while (it.hasNext()) {
            Datasource ds = it.next();
            if(!isFound(ds)){
                brokenDatasources.add(ds);
            }
        }
        
        Set<RequestedJdbcResource> brokenReqDss = new HashSet<RequestedJdbcResource>();
        it = brokenDatasources.iterator();
        
        while (it.hasNext()) {
            Datasource ds = it.next();
            RequestedJdbcResource r = new RequestedJdbcResource(ds.getJndiName(),ds.getDriverClassName(),
                    ds.getUrl(), ds.getUsername(), ds.getPassword());
            
            brokenReqDss.add(r);
        }
        
        return brokenReqDss;
    }
    
    private boolean isFound(Datasource ds) {
        boolean found = false;
        String url = ds.getUrl();
        String username = ds.getUsername();
        DatabaseConnection[] dbConns = ConnectionManager.getDefault().getConnections();
        for(int i=0; i<dbConns.length; i++ ){
            DatabaseConnection dbCon = dbConns[i];
            String url1 = dbCon.getDatabaseURL();
            String username1 = dbCon.getUser();
            if (matchURL(url, url1, true) && Utilities.compareObjects(username, username1)) {
                found = true;
            }
        }
        return found;
    }
    
     private boolean matchURL(String jdbcResourceUrl, String dsInfoUrl, boolean ignoreCase) {
        if (ignoreCase){
            jdbcResourceUrl = jdbcResourceUrl.toLowerCase();
            dsInfoUrl = dsInfoUrl.toLowerCase();
        }
        if (jdbcResourceUrl.equals(dsInfoUrl)){
            return true;
        }
        
        if (jdbcResourceUrl.contains("derby")) {
            String newJdbcResourceUrl = jdbcResourceUrl.substring(0, jdbcResourceUrl.lastIndexOf(":")) + jdbcResourceUrl.substring(jdbcResourceUrl.lastIndexOf("/"));
            if (newJdbcResourceUrl.equals(dsInfoUrl)){
                return true;
            }
        }
        
        int nextIndex = 0;
        if ((jdbcResourceUrl != null) && (dsInfoUrl != null)){
            char[] jdbcResourceUrlChars = jdbcResourceUrl.toCharArray();
            char[] dsInfoUrlChars = dsInfoUrl.toCharArray();
            // determine the shortest of the two arrays
            int length = jdbcResourceUrlChars.length;
            if (length > dsInfoUrlChars.length) {
                length = dsInfoUrlChars.length;
            }
            for(int i = 0; i < length-1; i++){
                if ((jdbcResourceUrlChars[i] != dsInfoUrlChars[i]) && jdbcResourceUrlChars[i] == ':'){
                    nextIndex = 1;
                } else if (jdbcResourceUrlChars[i + nextIndex] != dsInfoUrlChars[i]){
                    return false;
                }
            }
        }
        return true;
    }
     
    public boolean isDatasourceCreationSupported(Project project) {
        J2eeModuleProvider jmp = project.getLookup().lookup(J2eeModuleProvider.class);
        return jmp.getConfigSupport().isDatasourceCreationSupported();
    }
}
