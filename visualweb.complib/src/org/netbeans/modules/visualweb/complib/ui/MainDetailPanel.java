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
/*
 * CompLibDetails.java
 *
 * Created on April 18, 2005, 12:05 PM
 */

package org.netbeans.modules.visualweb.complib.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import org.netbeans.modules.visualweb.complib.Complib;

/**
 * Component library manager main detail panel
 *
 * @author jhoff
 * @author Edwin Goei
 */
public class MainDetailPanel extends javax.swing.JPanel {

    /**
     * Creates new form ComponentsDetailPanel
     *
     * @param compLib
     */
    public MainDetailPanel(Complib compLib) {
        initComponents();

        componentDetailsPanel.setLayout(new GridBagLayout());
        clGBConstraints = new GridBagConstraints();
        clGBConstraints.fill = GridBagConstraints.BOTH;
        clGBConstraints.weightx = 1.0;
        clGBConstraints.weighty = 1.0;
        componentDetailsPanel
                .add(new ComponentsPanel(compLib), clGBConstraints);

        txtCompLibDisplayName.setText(compLib.getTitle());
        txtCompLibURI.setText(compLib.getIdentifier().getNamespaceUriString());
        txtCompLibVersion.setText(compLib.getIdentifier().getVersionString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblCompLibName = new javax.swing.JLabel();
        txtCompLibDisplayName = new javax.swing.JTextField();
        txtCompLibURI = new javax.swing.JTextField();
        lblCompLibURI = new javax.swing.JLabel();
        lblCompLibVersion = new javax.swing.JLabel();
        txtCompLibVersion = new javax.swing.JTextField();
        componentDetailsPanel = new javax.swing.JPanel();

        setLayout(new java.awt.GridBagLayout());

        lblCompLibName.setLabelFor(txtCompLibDisplayName);
        org.openide.awt.Mnemonics.setLocalizedText(lblCompLibName, org.openide.util.NbBundle.getBundle("org/netbeans/modules/visualweb/complib/ui/Bundle").getString("manager.LibraryName"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 0, 10);
        add(lblCompLibName, gridBagConstraints);
        lblCompLibName.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(MainDetailPanel.class, "manager.LibraryNameA11yDescription"));

        txtCompLibDisplayName.setEditable(false);
        txtCompLibDisplayName.setText("Sun Web UI Components");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        add(txtCompLibDisplayName, gridBagConstraints);

        txtCompLibURI.setEditable(false);
        txtCompLibURI.setText("http://www.sun.com/jsf/sun_web_ui");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 10);
        add(txtCompLibURI, gridBagConstraints);

        lblCompLibURI.setLabelFor(txtCompLibURI);
        org.openide.awt.Mnemonics.setLocalizedText(lblCompLibURI, org.openide.util.NbBundle.getBundle("org/netbeans/modules/visualweb/complib/ui/Bundle").getString("manager.IdentificationURI"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 0, 10);
        add(lblCompLibURI, gridBagConstraints);
        lblCompLibURI.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(MainDetailPanel.class, "manager.IdentificationURIA11yDescription"));

        lblCompLibVersion.setLabelFor(txtCompLibVersion);
        org.openide.awt.Mnemonics.setLocalizedText(lblCompLibVersion, org.openide.util.NbBundle.getBundle("org/netbeans/modules/visualweb/complib/ui/Bundle").getString("manager.Version"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 10, 10, 10);
        add(lblCompLibVersion, gridBagConstraints);
        lblCompLibVersion.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(MainDetailPanel.class, "manager.VersionA11yDescription"));

        txtCompLibVersion.setEditable(false);
        txtCompLibVersion.setText("1.0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 10, 10);
        add(txtCompLibVersion, gridBagConstraints);

        componentDetailsPanel.setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(componentDetailsPanel, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel componentDetailsPanel;
    private javax.swing.JLabel lblCompLibName;
    private javax.swing.JLabel lblCompLibURI;
    private javax.swing.JLabel lblCompLibVersion;
    private javax.swing.JTextField txtCompLibDisplayName;
    private javax.swing.JTextField txtCompLibURI;
    private javax.swing.JTextField txtCompLibVersion;
    // End of variables declaration//GEN-END:variables
    private GridBagConstraints clGBConstraints;
}
