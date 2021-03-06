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

package org.netbeans.modules.visualweb.j2ee15classloaderprovider;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.netbeans.modules.visualweb.classloaderprovider.CommonClassloaderProvider;
import org.openide.ErrorManager;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.filesystems.URLMapper;

/**
 * This Common ClassLaoder provider simply return its ClassLoader which happens
 * to be the module ClassLoader. This module declares dependencies on the
 * modules that wrap the J2EE 1.5 platform libraries that are shared between the
 * IDE implementation and the user project. This provider is available through
 * the lookup.
 * <p>
 * The user project's meta data is be used to determine which J2EE platform it is using.
 *
 * @author Sandip V. Chitale (Sandip.Chitale@Sun.Com)
 */
@org.openide.util.lookup.ServiceProvider(service=org.netbeans.modules.visualweb.classloaderprovider.CommonClassloaderProvider.class)
public class J2EE15CommonClassloaderProvider implements CommonClassloaderProvider {
	private String[] designtimeJars = {
        // XXX Need to add these jars here so that META-INF/faces-config.xml files can be
        // discovered and loaded by the JSF1.2 RI. Ideally thin jars containing
        // the META-INF/faces-config.xml should be used. The classes should be loded
        // from the modules themselves.
        "jar:nbinst:///modules/org-netbeans-modules-visualweb-jsfsupport-designtime.jar!/",
        "jar:nbinst:///modules/org-netbeans-modules-visualweb-jsfsupport-designtime_1_2.jar!/",
        "jar:nbinst:///modules/ext/webui-jsf-dt.jar!/",
    };

	private URLClassLoader urlClassLoader;

    public J2EE15CommonClassloaderProvider() {
    }

    public ClassLoader getClassLoader() {
        synchronized  (this) {
            if (urlClassLoader == null) {
                List normalizedUrls = new ArrayList();

                for (int i = 0; i < designtimeJars.length; i++) {
                    try {
                        URL url = new URL(designtimeJars[i]);
                        FileObject fileObject = URLMapper.findFileObject(url);

                        //file inside library is broken
                        if (fileObject == null)
                            continue;

                        if ("jar".equals(url.getProtocol())) {  //NOI18N
                            fileObject = FileUtil.getArchiveFile(fileObject);
                        }
                        File f = FileUtil.toFile(fileObject);
                        if (f != null) {

                            URL entry = f.toURI().toURL();
                            if (FileUtil.isArchiveFile(entry)) {
                                entry = FileUtil.getArchiveRoot(entry);
                            } else if (!f.exists()) {
                                // if file does not exist (e.g. build/classes folder
                                // was not created yet) then corresponding File will
                                // not be ended with slash. Fix that.
                                assert !entry.toExternalForm().endsWith("/") : f; // NOI18N
                                entry = new URL(entry.toExternalForm() + "/"); // NOI18N
                            }
                            normalizedUrls.add(entry);

                        }
                    } catch (MalformedURLException mue) {
                        ErrorManager.getDefault().notify(ErrorManager.INFORMATIONAL, mue);
                    }
                }
                urlClassLoader = new URLClassLoader((URL[]) normalizedUrls.toArray(new URL[0]), getClass().getClassLoader());
            }
        }
        return urlClassLoader;
    }	

    public boolean isCapableOf(Properties capabilities) {
        if (JAVA_EE_5.equals(capabilities.getProperty(J2EE_PLATFORM))) {
            return true;
        }
        return false;
    }
}
