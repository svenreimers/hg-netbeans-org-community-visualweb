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
package com.sun.jsfcl.std.reference;

import java.util.List;

/**
 * @author eric
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class FrameTargetsReferenceDataDefiner extends ReferenceDataDefiner {

    public void addBaseItems(List list) {

        list.add(newItem(
            "", // NOI18N
            null,
            true,
            false));
        list.add(newItem(
            "_blank", // NOI18N
            "_blank", // NOI18N
            false,
            false));
        list.add(newItem(
            "_self", // NOI18N
            "_self", // NOI18N
            false,
            false));
        list.add(newItem(
            "_parent", // NOI18N
            "_parent", // NOI18N
            false,
            false));
        list.add(newItem(
            "_top", // NOI18N
            "_top", // NOI18N
            false,
            false));
    }

    public boolean canAddRemoveItems() {

        return true;
    }

    public boolean isValueAString() {

        return true;
    }

}
