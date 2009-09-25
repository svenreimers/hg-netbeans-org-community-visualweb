/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2009 Sun Microsystems, Inc. All rights reserved.
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
package com.sun.rave.web.ui.renderer;

import com.sun.rave.web.ui.component.PanelLayout;
import com.sun.rave.web.ui.util.RenderingUtilities;
import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 * Renderer for a {@link com.sun.rave.web.ui.component.PanelLayout} component.
 *
 * @author gjmurphy
 */
public class PanelLayoutRenderer extends AbstractRenderer {
    
    protected void renderStart(FacesContext context, UIComponent component, ResponseWriter writer) 
    throws IOException {
        writer.startElement("div", component); //NOI18N
    }

    protected void renderAttributes(FacesContext context, UIComponent component, ResponseWriter writer) 
    throws IOException {
        PanelLayout panelLayout = (PanelLayout)component;
        StringBuffer buffer = new StringBuffer();
        
        // Write id attribute
        String id = component.getId();
        writer.writeAttribute("id", panelLayout.getClientId(context), "id");
        
        // Write style attribute
        if (PanelLayout.GRID_LAYOUT.equals(panelLayout.getPanelLayout()))
	    buffer.append("position: relative; -rave-layout: grid;"); //NOI18N
        String style = panelLayout.getStyle();
        if (style != null && style.length() > 0) {
            buffer.append(" ");
            buffer.append(style);
        }
        writer.writeAttribute("style", buffer.toString(), "style");
        
        // Write style class attribute
        RenderingUtilities.renderStyleClass(context, writer, component, null);
    }

    protected void renderEnd(FacesContext context, UIComponent component, ResponseWriter writer) 
    throws IOException {
        writer.endElement("div"); //NOI18N
    }

    public boolean getRendersChildren() {
        return true;
    }
    
}
