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
 * PortletProjectDeployDescriptorVisual.java
 *
 * Created on April 27, 2005, 11:51 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package org.netbeans.modules.visualweb.project.jsfportlet.ui.wizard;
import org.openide.WizardDescriptor;
import org.openide.util.NbBundle;


/**
 * This class acts as one of the panels shown in the WizardIterator to create
 * a portlet project.  This panel represents the portlet specific options that a
 * user can specify.
 *
 * @author  David Botterill
 */
public class PortletProjectDeployDescriptorVisual extends javax.swing.JPanel {
    
    private WizardDescriptor wizardDescriptor;
    
    /**
     * Creates new form PortletProjectOptionsVisual
     */
    public PortletProjectDeployDescriptorVisual(WizardDescriptor inWizardDescriptor) {
        wizardDescriptor = inWizardDescriptor;
        initComponents();
        /**
         * Set the inital portlet name based on the project name.
         */
        Object projectName = wizardDescriptor.getProperty(WizardProperties.NAME);
        if(null != projectName && projectName instanceof String) {
            this.txtPortletName.setText((String)projectName);
        }
        

    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblPortletName = new javax.swing.JLabel();
        txtPortletName = new javax.swing.JTextField();
        lblPortletDescription = new javax.swing.JLabel();
        txtPortletDescription = new javax.swing.JTextField();
        lblPortletDisplayName = new javax.swing.JLabel();
        txtPortletDisplayName = new javax.swing.JTextField();
        lblPortletDescription1 = new javax.swing.JLabel();
        txtPortletTitle = new javax.swing.JTextField();
        lblPortletDescription2 = new javax.swing.JLabel();
        txtPortletShortTitle = new javax.swing.JTextField();

        setLayout(new java.awt.GridBagLayout());

        lblPortletName.setDisplayedMnemonic(java.util.ResourceBundle.getBundle("org/netbeans/modules/visualweb/project/jsfportlet/ui/wizard/Bundle").getString("LBL_PPOV_PORTLETNAMEMnemonic").charAt(0));
        lblPortletName.setLabelFor(txtPortletName);
        lblPortletName.setText(org.openide.util.NbBundle.getMessage(PortletProjectDeployDescriptorVisual.class, "LBL_PPOV_PORTLETNAME"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 12, 0, 0);
        add(lblPortletName, gridBagConstraints);
        lblPortletName.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(PortletProjectDeployDescriptorVisual.class, "ACS_LBL_PPOV_PORTLETNAME_A11YName"));
        lblPortletName.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(PortletProjectDeployDescriptorVisual.class, "ACS_LBL_PPOV_PORTLETNAME_A11YDesc"));

        txtPortletName.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 12);
        add(txtPortletName, gridBagConstraints);
        txtPortletName.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(PortletProjectDeployDescriptorVisual.class, "ACS_TXT_PPOV_PORTLETNAME_A11YName"));
        txtPortletName.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(PortletProjectDeployDescriptorVisual.class, "ACS_TXT_PPOV_PORTLETNAME_A11YDesc"));

        lblPortletDescription.setDisplayedMnemonic(java.util.ResourceBundle.getBundle("org/netbeans/modules/visualweb/project/jsfportlet/ui/wizard/Bundle").getString("LBL_PPOV_PORTLETDESCMnemonic").charAt(0));
        lblPortletDescription.setLabelFor(txtPortletDescription);
        lblPortletDescription.setText(org.openide.util.NbBundle.getMessage(PortletProjectDeployDescriptorVisual.class, "LBL_PPOV_PORTLETDESC"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 12, 0, 0);
        add(lblPortletDescription, gridBagConstraints);
        lblPortletDescription.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(PortletProjectDeployDescriptorVisual.class, "ACS_LBL_PPOV_PORTLETDESC_A11YName"));
        lblPortletDescription.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(PortletProjectDeployDescriptorVisual.class, "ACS_LBL_PPOV_PORTLETDESC_A11YDesc"));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 12);
        add(txtPortletDescription, gridBagConstraints);
        txtPortletDescription.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(PortletProjectDeployDescriptorVisual.class, "ACS_TXT_PPOV_PORTLETDESC_A11YName"));
        txtPortletDescription.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(PortletProjectDeployDescriptorVisual.class, "ACS_TXT_PPOV_PORTLETDESC_A11YDesc"));

        lblPortletDisplayName.setDisplayedMnemonic(java.util.ResourceBundle.getBundle("org/netbeans/modules/visualweb/project/jsfportlet/ui/wizard/Bundle").getString("LBL_PPOV_PORTLETDISPLAYNAMEMnemonic").charAt(0));
        lblPortletDisplayName.setLabelFor(txtPortletDisplayName);
        lblPortletDisplayName.setText(org.openide.util.NbBundle.getMessage(PortletProjectDeployDescriptorVisual.class, "LBL_PPOV_PORTLETDISPLAYNAME"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 12, 0, 0);
        add(lblPortletDisplayName, gridBagConstraints);
        lblPortletDisplayName.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(PortletProjectDeployDescriptorVisual.class, "ACS_TXT_PPOV_PORTLETDISPLAYNAME_A11YName"));
        lblPortletDisplayName.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(PortletProjectDeployDescriptorVisual.class, "ACS_TXT_PPOV_PORTLETDISPLAYNAME_A11YDesc"));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 12);
        add(txtPortletDisplayName, gridBagConstraints);
        txtPortletDisplayName.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(PortletProjectDeployDescriptorVisual.class, "ACS_TXT_PPOV_PORTLETDISPLAYNAME_A11YName"));
        txtPortletDisplayName.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(PortletProjectDeployDescriptorVisual.class, "ACS_TXT_PPOV_PORTLETDISPLAYNAME_A11YDesc"));

        lblPortletDescription1.setDisplayedMnemonic(java.util.ResourceBundle.getBundle("org/netbeans/modules/visualweb/project/jsfportlet/ui/wizard/Bundle").getString("LBL_PPOV_PORTLETTITLEMnemonic").charAt(0));
        lblPortletDescription1.setLabelFor(txtPortletTitle);
        lblPortletDescription1.setText(org.openide.util.NbBundle.getMessage(PortletProjectDeployDescriptorVisual.class, "LBL_PPOV_PORTLETTITLE"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 12, 0, 0);
        add(lblPortletDescription1, gridBagConstraints);
        lblPortletDescription1.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(PortletProjectDeployDescriptorVisual.class, "ACS_LBL_PPOV_PORTLETTITLE_A11YName"));
        lblPortletDescription1.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(PortletProjectDeployDescriptorVisual.class, "ACS_LBL_PPOV_PORTLETTITLE_A11YDesc"));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 12);
        add(txtPortletTitle, gridBagConstraints);
        txtPortletTitle.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(PortletProjectDeployDescriptorVisual.class, "ACS_TXT_PPOV_PORTLETTITLE_A11YName"));
        txtPortletTitle.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(PortletProjectDeployDescriptorVisual.class, "ACS_TXT_PPOV_PORTLETTITLE_A11YDesc"));

        lblPortletDescription2.setDisplayedMnemonic(java.util.ResourceBundle.getBundle("org/netbeans/modules/visualweb/project/jsfportlet/ui/wizard/Bundle").getString("LBL_PPOV_PORTLETSHORTTITLEMnemonic").charAt(0));
        lblPortletDescription2.setLabelFor(txtPortletShortTitle);
        lblPortletDescription2.setText(org.openide.util.NbBundle.getMessage(PortletProjectDeployDescriptorVisual.class, "LBL_PPOV_PORTLETSHORTTITLE"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 12, 0, 0);
        add(lblPortletDescription2, gridBagConstraints);
        lblPortletDescription2.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(PortletProjectDeployDescriptorVisual.class, "ACS_LBL_PPOV_PORTLETSHORTTITLE_A11YName"));
        lblPortletDescription2.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(PortletProjectDeployDescriptorVisual.class, "ACS_LBL_PPOV_PORTLETSHORTTITLE_A11YDesc"));

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 12);
        add(txtPortletShortTitle, gridBagConstraints);
        txtPortletShortTitle.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(PortletProjectDeployDescriptorVisual.class, "ACS_TXT_PPOV_PORTLETSHORTTITLE_A11YName"));
        txtPortletShortTitle.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(PortletProjectDeployDescriptorVisual.class, "ACS_TXT_PPOV_PORTLETSHORTTITLE_A11YDesc"));

    }
    // </editor-fold>//GEN-END:initComponents
        
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblPortletDescription;
    private javax.swing.JLabel lblPortletDescription1;
    private javax.swing.JLabel lblPortletDescription2;
    private javax.swing.JLabel lblPortletDisplayName;
    private javax.swing.JLabel lblPortletName;
    private javax.swing.JTextField txtPortletDescription;
    private javax.swing.JTextField txtPortletDisplayName;
    private javax.swing.JTextField txtPortletName;
    private javax.swing.JTextField txtPortletShortTitle;
    private javax.swing.JTextField txtPortletTitle;
    // End of variables declaration//GEN-END:variables
    
    boolean valid(WizardDescriptor wizardDescriptor) {
        return true;
    }
    
    void store(WizardDescriptor inDescriptor) {
        inDescriptor.putProperty(PortletWizardProperties.PORTLET_NAME,this.txtPortletName.getText());
        inDescriptor.putProperty(PortletWizardProperties.PORTLET_DISPLAYNAME,this.txtPortletDisplayName.getText());
        inDescriptor.putProperty(PortletWizardProperties.PORTLET_DESC,this.txtPortletDescription.getText());
        inDescriptor.putProperty(PortletWizardProperties.PORTLET_TITLE,this.txtPortletTitle.getText());
        inDescriptor.putProperty(PortletWizardProperties.PORTLET_SHORTTITLE,this.txtPortletShortTitle.getText());
    }
    
    void read(WizardDescriptor inDescriptor) {
        this.txtPortletName.setText((String)inDescriptor.getProperty(WizardProperties.NAME));
        this.txtPortletDisplayName.setText((String)inDescriptor.getProperty(WizardProperties.NAME) + " " + 
                NbBundle.getMessage(PortletProjectWizardIterator.class, "TXT_PORTLET_DIPSLAYNAME_SUFFIX_DEFAULT"));
        this.txtPortletDescription.setText(NbBundle.getMessage(PortletProjectWizardIterator.class, "TXT_PORTLET_DESC_DEFAULT"));
        this.txtPortletTitle.setText((String)inDescriptor.getProperty(WizardProperties.NAME));
        this.txtPortletShortTitle.setText((String)inDescriptor.getProperty(WizardProperties.NAME));
       
        
    }
    
    
}
