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
package com.sun.rave.web.ui.component.util.factories;

import com.sun.rave.web.ui.component.util.descriptors.LayoutComponent;
import com.sun.rave.web.ui.component.ImageComponent;
import com.sun.rave.web.ui.theme.Theme;
import com.sun.rave.web.ui.util.ThemeUtilities;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.UIGraphic;
import javax.faces.context.FacesContext;


/**
 *  <P>	This factory is responsible for creating a {@link ImageComponent}
 *	UIComponent.</P>
 *
 *  @author Rick Ratta
 *  @author Ken Paulsen	(ken.paulsen@sun.com)
 */
public class ImageComponentFactory extends ComponentFactoryBase {

    /**
     *	<P> This is the factory method responsible for creating the
     *	    {@link ImageComponent} UIComponent.</P>
     *
     *	@param	context	    The FacesContext
     *
     *	@param	descriptor  The {@link LayoutComponent} descriptor that is
     *			    associated with the requested {@link ImageComponent}.
     *
     *	@param	parent	    The parent UIComponent
     *
     *	@return	The newly created {@link ImageComponent}.
     */
    public UIComponent create(FacesContext context, LayoutComponent descriptor, UIComponent parent) {
	// Create the UIComponent
	UIGraphic image = new ImageComponent();

	// This needs to be done here (before setOptions) so that $...{...}
	// expressions can be resolved...
	if (parent != null) {
//	    parent.getChildren().add(image);
	    addChild(context, descriptor, parent, image);
	}

	// Set all the attributes / properties (allow these to override theme)
	setOptions(context, descriptor, image);

	// Return the value
	return image;
    }
}
