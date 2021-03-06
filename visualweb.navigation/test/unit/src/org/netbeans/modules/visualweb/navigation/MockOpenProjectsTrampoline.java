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
 * Portions Copyrighted 2007 Sun Microsystems, Inc.
 */
package org.netbeans.modules.visualweb.navigation;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Future;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.modules.project.uiapi.OpenProjectsTrampoline;

/**
 *
 * @author joelle
 */
@org.openide.util.lookup.ServiceProvider(service=org.netbeans.modules.project.uiapi.OpenProjectsTrampoline.class)
public class MockOpenProjectsTrampoline implements OpenProjectsTrampoline {

    private Collection<Project> openProjects = new ArrayList<Project>();

    public MockOpenProjectsTrampoline() {
    }



    public Project[] getOpenProjectsAPI() {
        Project[] projects = new Project[openProjects.size()];
        openProjects.toArray(projects);
        return projects;
    }

    public void openAPI(Project[] projects, boolean openRequiredProjects) {
        for (Project project : projects) {
            openProjects.add(project);
            mainProject = project;
        }
    }

    public void closeAPI(Project[] projects) {
        for (Project project : projects) {
            openProjects.remove(project);
            listeners.remove(project);
        }
    }

    Map<Object, PropertyChangeListener> listeners = new HashMap<Object, PropertyChangeListener>();

    public void addPropertyChangeListenerAPI(PropertyChangeListener listener, Object source) {
        listeners.put(source, listener);
    }

    public void removePropertyChangeListenerAPI(PropertyChangeListener listener) {
        if (listeners.containsValue(listener)) {
            Set<Entry<Object, PropertyChangeListener>> entries = listeners.entrySet();
            for (Entry<Object, PropertyChangeListener> entry : entries) {
                if (entry.getValue().equals(listener)) {
                    Object object = entry.getKey();
                    listeners.remove(object);
                }
            }
        }
    }

    private Project mainProject;

    public Project getMainProject() {
        return mainProject;
    }

    public void setMainProject(Project project) {
        if (project != null && !openProjects.contains(project)) {
            throw new IllegalArgumentException("Project " + ProjectUtils.getInformation(mainProject).getDisplayName() + " is not open and cannot be set as main.");
        }
        this.mainProject = project;
    }

    public Future<Project[]> openProjectsAPI() {
        return null;
    }
    @Override
    public void openAPI(Project[] prjcts, boolean bln, boolean bln1) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
