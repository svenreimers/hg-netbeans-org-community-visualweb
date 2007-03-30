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
/*
 * Created on Mar 16, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.netbeans.modules.visualweb.text.actions;

import java.awt.event.ActionEvent;

import javax.swing.UIManager;

import org.netbeans.modules.visualweb.api.designer.HtmlDomProvider.DomPosition;
import org.netbeans.modules.visualweb.css2.ModelViewMapper;
import org.netbeans.modules.visualweb.designer.InlineEditor;
import org.netbeans.modules.visualweb.designer.WebForm;
import org.netbeans.modules.visualweb.text.DesignerPaneBase;


/*
 * Position the caret to the beginning of the line.
 * @see DefaultEditorKit#beginLineAction
 * @see DefaultEditorKit#selectBeginLineAction
 * @see DefaultEditorKit#getActions
 */
public class BeginLineAction extends TextAction {
    private boolean select;

    /**
     * Create this action with the appropriate identifier.
     * @param nm  the name of the action, Action.NAME.
     * @param select whether to extend the selection when
     *  changing the caret position.
     */
    public BeginLineAction(String nm, boolean select) {
        super(nm);
        this.select = select;
    }

    /** The operation to perform when this action is triggered. */
    public void actionPerformed(ActionEvent e) {
        DesignerPaneBase target = getTextComponent(e);

        if (target != null) {
//            Position pos = target.getCaretPosition();
            DomPosition pos = target.getCaretPosition();
//            if (pos == Position.NONE) {
            if (pos == DomPosition.NONE) {
                // #6316151 No position no move.
                return;
            }
//            WebForm webform = target.getDocument().getWebForm();
            WebForm webform = target.getWebForm();
            
//            ModelViewMapper mapper = webform.getMapper();
//            Position begPos = ModelViewMapper.getLineBegin(webform, pos);
            DomPosition begPos = ModelViewMapper.getLineBegin(webform, pos);

//            if (begPos != Position.NONE) {
            if (begPos != DomPosition.NONE) {
//                DesignerCaret caret = target.getCaret();
//                if (!caret.isWithinEditableRegion(begPos)) {
//                if (!target.isCaretWithinEditableRegion(begPos)) {
                if (!webform.isInsideEditableRegion(begPos)) {
                    InlineEditor editor = webform.getManager().getInlineEditor();

                    if (editor != null) {
                        begPos = editor.getBegin();
                    }
                }

                if (select) {
//                    target.moveCaretPosition(begPos);
                    target.moveCaretDot(begPos);
                } else {
//                    target.setCaretPosition(begPos);
                    target.setCaretDot(begPos);
                }

                return;
            }

            UIManager.getLookAndFeel().provideErrorFeedback(target);
        }
    }
}
