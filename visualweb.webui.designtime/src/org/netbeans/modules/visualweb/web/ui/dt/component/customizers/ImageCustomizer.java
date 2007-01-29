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
package org.netbeans.modules.visualweb.web.ui.dt.component.customizers;

import com.sun.rave.designtime.DesignBean;
import com.sun.rave.designtime.Result;
import com.sun.rave.designtime.impl.BasicCustomizer2;
import org.netbeans.modules.visualweb.web.ui.dt.component.util.DesignMessageUtil;
//import javax.faces.component.*;
import java.awt.Component;


public class ImageCustomizer extends BasicCustomizer2 {

    public ImageCustomizer() {
        super(ImageCustomizerPanel.class, DesignMessageUtil.getMessage(ImageCustomizer.class, "imageFormat"), null, "the-help-key"); // NOI18N
        setApplyCapable(true);
    }

    protected ImageCustomizerPanel icp = null;

    public Component getCustomizerPanel(DesignBean designBean) {
        this.setDisplayName(DesignMessageUtil.getMessage(ImageCustomizer.class, "imageFormat") + " - " + designBean.getInstanceName());
        icp = new ImageCustomizerPanel(designBean);
        return icp;
    }

    public boolean isModified() {
        if (icp != null) {
            return icp.isModified();
        }
        return false;
    }


    public Result applyChanges() {
        if (icp != null) {
            return icp.customizerApply();
        }
        return Result.SUCCESS;
    }

}
