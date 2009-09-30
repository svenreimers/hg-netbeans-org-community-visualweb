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

import java.io.IOException;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;
import com.sun.rave.web.ui.component.ThemeLinks;
import com.sun.rave.web.ui.theme.Theme;
import com.sun.rave.web.ui.util.MessageUtil;
import com.sun.rave.web.ui.util.RenderingUtilities;
import com.sun.rave.web.ui.util.ThemeUtilities;

/**
 * <p>Renderer for a {@link Theme} component.</p>
 */

public class ThemeLinksRenderer extends Renderer {

    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {

       return;
    }

    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        
        if(!(component instanceof ThemeLinks)) {
            Object[] params = { component.toString(),
                    this.getClass().getName(),
                    ThemeLinks.class.getName() };
                    String message = MessageUtil.getMessage
                            ("com.sun.rave.web.ui.resources.LogMessages", //NOI18N
                            "Renderer.component", params);              //NOI18N
                    throw new FacesException(message);
        }
        
        ThemeLinks themeLinks = (ThemeLinks)component;
        ResponseWriter writer = context.getResponseWriter();
        
        // Link and Scripts
        Theme theme = ThemeUtilities.getTheme(context);
        if(themeLinks.isJavaScript()) {      
            RenderingUtilities.renderJavaScript(themeLinks, theme, context, writer);
        }
        if(themeLinks.isStyleSheetInline()) {
            RenderingUtilities.renderStyleSheetInline(themeLinks, theme, context, writer);
        } else if(themeLinks.isStyleSheetLink()) {
            RenderingUtilities.renderStyleSheetLink(themeLinks, theme, context, writer);
        }
    }

public boolean getRendersChildren() {
    return true; 
}

    public void encodeChildren(FacesContext context, UIComponent component) throws IOException {
        return;
    }
}
