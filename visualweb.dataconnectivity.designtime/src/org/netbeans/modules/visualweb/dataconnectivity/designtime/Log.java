/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 * 
 * You can obtain a copy of the License at http://www.netbeans.org/cddl.html
 * or http://www.netbeans.org/cddl.txt.
 * 
 * When distributing Covered Code, include this CDDL Header Notice in each file
 * and include the License file at http://www.netbeans.org/cddl.txt.
 * If applicable, add the following below the CDDL Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 * 
 * The Original Software is NetBeans. The Initial Developer of the Original
 * Software is Sun Microsystems, Inc. Portions Copyright 1997-2007 Sun
 * Microsystems, Inc. All Rights Reserved.
 */
package org.netbeans.modules.visualweb.dataconnectivity.designtime;

import org.openide.ErrorManager;

/**
 * Log utility for the dataconnectivity designtime module
 *
 * @author David Van Couvering
 */
public class Log {
    public static final ErrorManager err =
	ErrorManager.getDefault().getInstance(Log.class.getPackage().getName()); // NOI18N
    public static boolean isLoggable() {
        return err.isLoggable(err.INFORMATIONAL) ;
    }
    public static boolean isLoggable(int sev) {
        return err.isLoggable(sev) ;
    }
    public static void log(int sev, String x) {
        err.log(sev, x) ;
    }
    public static void log(String x) {
        err.log(x) ;
    }
}