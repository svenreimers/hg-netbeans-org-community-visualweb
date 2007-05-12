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

package com.sun.rave.designtime.impl;

import java.awt.Image;
import com.sun.rave.designtime.DisplayAction;
import com.sun.rave.designtime.Result;

/**
 * A basic implementation of DisplayAction to use for convenience.
 *
 * @author Joe Nuxoll
 * @version 1.0
 * @see DisplayAction
 */
public class BasicDisplayAction implements DisplayAction {

    protected String displayName;
    protected String description;
    protected String helpKey;
    protected Image smallIcon;
    protected Image largeIcon;
    protected boolean enabled = true;

    public BasicDisplayAction() {}

    public BasicDisplayAction(String displayName) {
        this.displayName = displayName;
    }

    public BasicDisplayAction(String displayName, String description) {
        this(displayName);
        this.description = description;
    }

    public BasicDisplayAction(String displayName, String description, String helpKey) {
        this(displayName, description);
        this.helpKey = helpKey;
    }

    public BasicDisplayAction(String displayName, String description, String helpKey,
        Image smallIcon) {
        this(displayName, description, helpKey);
        this.smallIcon = smallIcon;
    }

    public BasicDisplayAction(String displayName, String description, String helpKey,
        Image smallIcon, Image largeIcon) {
        this(displayName, description, helpKey, smallIcon);
        this.largeIcon = largeIcon;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setSmallIcon(Image smallIcon) {
        this.smallIcon = smallIcon;
    }

    public Image getSmallIcon() {
        return smallIcon;
    }

    public void setLargeIcon(Image largeIcon) {
        this.largeIcon = largeIcon;
    }

    public Image getLargeIcon() {
        return largeIcon;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setHelpKey(String helpKey) {
        this.helpKey = helpKey;
    }

    public String getHelpKey() {
        return helpKey;
    }

    public Result invoke() {
        return Result.SUCCESS;
    }
}
