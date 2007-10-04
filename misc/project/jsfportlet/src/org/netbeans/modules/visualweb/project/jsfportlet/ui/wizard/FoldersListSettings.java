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

import java.io.File;
import java.util.Map;
import javax.swing.SwingUtilities;
import org.openide.ErrorManager;

import org.openide.options.SystemOption;
import org.openide.util.NbBundle;

/**
 * Misnamed storage of information application to the new webproject wizard.
 */
public class FoldersListSettings extends SystemOption {

    static final long serialVersionUID = -4905094097265543014L;
    
    private static final String NEW_PROJECT_COUNT = "newProjectCount"; //NOI18N
    
    private static final String NEW_APP_COUNT = "newApplicationCount";  //NOI18N
    
    private static final String LAST_USED_CP_FOLDER = "lastUsedClassPathFolder";    //NOI18N

    private static final String LAST_USED_ARTIFACT_FOLDER = "lastUsedArtifactFolder"; //NOI18N

    private static final String SHOW_AGAIN_BROKEN_REF_ALERT = "showAgainBrokenRefAlert"; //NOI18N
    
    private static final String SHOW_AGAIN_BROKEN_SERVER_ALERT = "showAgainBrokenServerAlert"; //NOI18N
    
    private static final String LAST_USED_CHOOSER_LOCATIONS = "lastUsedChooserLocations"; //NOI18N
    
    private static final String AGREED_SET_JDK_14 = "agreeSetJdk14"; // NOI18N
    
    private static final String AGREED_SET_SOURCE_LEVEL_14 = "agreeSetSourceLevel14"; // NOI18N
    
    public static String defaultProjectDir = null;
 

    public static FoldersListSettings getDefault () {
        return (FoldersListSettings) SystemOption.findObject (FoldersListSettings.class, true);
    }
    
    public String displayName() {
        return NbBundle.getMessage(FoldersListSettings.class, "TXT_WebProjectFolderList");
    }
    
    public int getNewProjectCount () {
        Integer value = (Integer) getProperty (NEW_PROJECT_COUNT);
        return value == null ? 0 : value.intValue();
    }

    public void setNewProjectCount (int count) {
        this.putProperty(NEW_PROJECT_COUNT, new Integer(count),true);
    }
    
    public int getNewApplicationCount () {
        Integer value = (Integer) getProperty (NEW_APP_COUNT);
        return value == null ? 0 : value.intValue();
    }
    
    public void setNewApplicationCount (int count) {
        this.putProperty(NEW_APP_COUNT, new Integer(count),true);
    }
    
    public File getLastUsedClassPathFolder () {
        String lucpr = (String) this.getProperty (LAST_USED_CP_FOLDER);
        if (lucpr == null) {
            lucpr = System.getProperty("user.home");    //NOI18N
        }
        return new File (lucpr);
    }

    public void setLastUsedClassPathFolder (File folder) {
        assert folder != null : "ClassPath root can not be null";
        String path = folder.getAbsolutePath();
        this.putProperty(LAST_USED_CP_FOLDER, path, true);
    }

    public File getLastUsedArtifactFolder () {
        String folder = (String) this.getProperty (LAST_USED_ARTIFACT_FOLDER);
        if (folder == null) {
            folder = System.getProperty("user.home");    //NOI18N
        }
        return new File (folder);
    }

    public void setLastUsedArtifactFolder (File folder) {
        assert folder != null : "Folder can not be null";
        String path = folder.getAbsolutePath();
        this.putProperty (LAST_USED_ARTIFACT_FOLDER, path, true);
    }

    public boolean isShowAgainBrokenRefAlert() {
        Boolean b = (Boolean)getProperty(SHOW_AGAIN_BROKEN_REF_ALERT);
        return b == null ? true : b.booleanValue();
    }
    
    public void setShowAgainBrokenRefAlert(boolean again) {
        this.putProperty(SHOW_AGAIN_BROKEN_REF_ALERT, Boolean.valueOf(again), true);
    }
    
    public boolean isShowAgainBrokenServerAlert() {
        Boolean b = (Boolean)getProperty(SHOW_AGAIN_BROKEN_SERVER_ALERT);
        return b == null ? true : b.booleanValue();
    }
    
    public void setShowAgainBrokenServerAlert(boolean again) {
        this.putProperty(SHOW_AGAIN_BROKEN_SERVER_ALERT, Boolean.valueOf(again), true);
    }
    
    public Map getLastUsedChooserLocations() {
        return (Map) this.getProperty(LAST_USED_CHOOSER_LOCATIONS);
    }

    public void setLastUsedChooserLocations(Map map) {
        this.putProperty(LAST_USED_CHOOSER_LOCATIONS, map, true);
    }
    
    public boolean isAgreedSetJdk14() {
        Boolean b = (Boolean)getProperty(AGREED_SET_JDK_14);
        return b == null ? true : b.booleanValue();
    }
    
    public void setAgreedSetJdk14(boolean agreed) {
        this.putProperty(AGREED_SET_JDK_14, Boolean.valueOf(agreed), true);
    }
    
    public boolean isAgreedSetSourceLevel14() {
        Boolean b = (Boolean)getProperty(AGREED_SET_SOURCE_LEVEL_14);
        return b == null ? true : b.booleanValue();
    }
    
    public void setAgreedSetSourceLevel14(boolean agreed) {
        this.putProperty(AGREED_SET_SOURCE_LEVEL_14, Boolean.valueOf(agreed), true);
    }
}
