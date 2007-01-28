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
 * ComponentLibDetailPanel.java
 *
 * Created on April 15, 2005, 1:35 PM
 */

package org.netbeans.modules.visualweb.complib.ui;

import java.io.File;
import java.util.List;

import org.openide.util.NbBundle;

import org.netbeans.modules.visualweb.complib.IdeUtil;

/**
 * Component library manager general purpose detail panel to show paths
 *
 * @author jhoff
 * @author Edwin Goei
 */
public class PathDetailPanel extends javax.swing.JPanel {

    /**
     * Parameters should be resource bundle keys.
     *
     * @param path
     *            File[] of path elements
     * @param labelKey
     * @param descriptionKey
     * @param mnemonicKey
     */
    public PathDetailPanel(List<File> path, String labelKey,
            String descriptionKey, String mnemonicKey) {
        initComponents();

        try {
            String str = NbBundle.getMessage(PathDetailPanel.class, labelKey);
            lblLibDesignTime.setText(str);

            str = NbBundle.getMessage(PathDetailPanel.class, descriptionKey);
            tpnlDescription.setText(str);

            str = NbBundle.getMessage(PathDetailPanel.class, mnemonicKey);
            lblLibDesignTime.setDisplayedMnemonic(str.charAt(0));
        } catch (Exception e) {
            IdeUtil.logWarning(e);
        }

        PathListModel pathListModel = new PathListModel(path);
        lstDesignTime.setModel(pathListModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblLibDesignTime = new javax.swing.JLabel();
        scrollDesignTime = new javax.swing.JScrollPane();
        lstDesignTime = new javax.swing.JList();
        tpnlDescription = new javax.swing.JTextPane();

        setLayout(new java.awt.GridBagLayout());

        lblLibDesignTime.setLabelFor(lstDesignTime);
        lblLibDesignTime.setText("Design Time Libraries:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 0);
        add(lblLibDesignTime, gridBagConstraints);

        scrollDesignTime.setViewportView(lstDesignTime);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 10, 11);
        add(scrollDesignTime, gridBagConstraints);

        tpnlDescription.setBackground(javax.swing.UIManager.getDefaults()
                .getColor("Button.background"));
        tpnlDescription.setEditable(false);
        tpnlDescription
                .setText("JAR files used for design-time support of the components in the IDE.  They are not deployed to the application server.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 10);
        add(tpnlDescription, gridBagConstraints);

    }

    // </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblLibDesignTime;

    private javax.swing.JList lstDesignTime;

    private javax.swing.JScrollPane scrollDesignTime;

    private javax.swing.JTextPane tpnlDescription;
    // End of variables declaration//GEN-END:variables

}
