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

package org.netbeans.modules.visualweb.project.jsf.framework;

// <RAVE>
import org.netbeans.modules.visualweb.project.jsf.api.JsfProjectConstants;
import org.openide.util.Utilities;
// </RAVE>

import javax.swing.event.DocumentListener;
import org.openide.WizardDescriptor;
import org.openide.WizardValidationException;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;

/**
 *
 * @author Po-Ting Wu
 */
public class JSFConfigurationPanelVisual extends javax.swing.JPanel implements HelpCtx.Provider, DocumentListener  {

    private JSFConfigurationPanel panel;

    // <RAVE> Default Bean Package
    private boolean beanPackageModified = false;
    // </RAVE>

    /** Creates new form JSFConfigurationPanelVisual */
    public JSFConfigurationPanelVisual(JSFConfigurationPanel panel, boolean customizer) {
        initComponents();
        this.panel = panel;
        tURLPattern.getDocument().addDocumentListener(this);
        cbPackageJars.setVisible(false);
        if (customizer){
            enableComponents(false);
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

        beanPackageLabel = new javax.swing.JLabel();
        beanPackageTextField = new javax.swing.JTextField();
        lServletName = new javax.swing.JLabel();
        tServletName = new javax.swing.JTextField();
        lURLPattern = new javax.swing.JLabel();
        tURLPattern = new javax.swing.JTextField();
        cbValidate = new javax.swing.JCheckBox();
        cbVerify = new javax.swing.JCheckBox();
        cbPackageJars = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();

        setLayout(new java.awt.GridBagLayout());

        beanPackageLabel.setDisplayedMnemonic(java.util.ResourceBundle.getBundle("org/netbeans/modules/visualweb/project/jsf/framework/Bundle").getString("MNE_DefaultBackingPackageLabelMnemonic").charAt(0));
        beanPackageLabel.setLabelFor(beanPackageTextField);
        beanPackageLabel.setText(NbBundle.getMessage(JSFConfigurationPanelVisual.class, "LBL_DefaultBackingPackageLabel"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(beanPackageLabel, gridBagConstraints);

        beanPackageTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                beanPackageTextFieldKeyReleased(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        add(beanPackageTextField, gridBagConstraints);
        beanPackageTextField.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(JSFConfigurationPanelVisual.class, "ACSD_DefaultBackingPackageDesc"));

        lServletName.setDisplayedMnemonic(java.util.ResourceBundle.getBundle("org/netbeans/modules/visualweb/project/jsf/framework/Bundle").getString("MNE_lServletName").charAt(0));
        lServletName.setLabelFor(tServletName);
        lServletName.setText(org.openide.util.NbBundle.getMessage(JSFConfigurationPanelVisual.class, "LBL_Servlet_Name"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(lServletName, gridBagConstraints);

        tServletName.setEditable(false);
        tServletName.setText("Faces Servlet");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        add(tServletName, gridBagConstraints);
        tServletName.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(JSFConfigurationPanelVisual.class, "ACSD_ServletName"));

        lURLPattern.setDisplayedMnemonic(java.util.ResourceBundle.getBundle("org/netbeans/modules/visualweb/project/jsf/framework/Bundle").getString("MNE_lURLPattern").charAt(0));
        lURLPattern.setLabelFor(tURLPattern);
        lURLPattern.setText(org.openide.util.NbBundle.getMessage(JSFConfigurationPanelVisual.class, "LBL_URL_Pattern"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        add(lURLPattern, gridBagConstraints);

        tURLPattern.setText("/faces/*");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 5, 0);
        add(tURLPattern, gridBagConstraints);
        tURLPattern.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(JSFConfigurationPanelVisual.class, "ACSD_Mapping"));

        cbValidate.setMnemonic(java.util.ResourceBundle.getBundle("org/netbeans/modules/visualweb/project/jsf/framework/Bundle").getString("MNE_cbValidate").charAt(0));
        cbValidate.setSelected(true);
        cbValidate.setText(org.openide.util.NbBundle.getMessage(JSFConfigurationPanelVisual.class, "CB_Validate_XML"));
        cbValidate.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cbValidate.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 5, 0);
        add(cbValidate, gridBagConstraints);
        cbValidate.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(JSFConfigurationPanelVisual.class, "ACSD_ValidateXML"));

        cbVerify.setMnemonic(java.util.ResourceBundle.getBundle("org/netbeans/modules/visualweb/project/jsf/framework/Bundle").getString("MNE_cbVerify").charAt(0));
        cbVerify.setText(org.openide.util.NbBundle.getMessage(JSFConfigurationPanelVisual.class, "CB_Verify_Objects"));
        cbVerify.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cbVerify.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 5, 5, 0);
        add(cbVerify, gridBagConstraints);
        cbVerify.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(JSFConfigurationPanelVisual.class, "ACSD_VerifyObjects"));

        cbPackageJars.setSelected(true);
        cbPackageJars.setText(org.openide.util.NbBundle.getMessage(JSFConfigurationPanelVisual.class, "CB_Package_JARs"));
        cbPackageJars.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cbPackageJars.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        add(cbPackageJars, gridBagConstraints);
        cbPackageJars.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(JSFConfigurationPanelVisual.class, "ACSD_PackageJarToWar"));

        jPanel1.setEnabled(false);
        jPanel1.setFocusable(false);
        jPanel1.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 11, 0);
        add(jPanel1, gridBagConstraints);

    }// </editor-fold>//GEN-END:initComponents

    private void beanPackageTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_beanPackageTextFieldKeyReleased
        if (beanPackageTextField.getText().length() == 0)
            beanPackageModified = false;
        else
            beanPackageModified = true;
        panel.fireChangeEvent();
    }//GEN-LAST:event_beanPackageTextFieldKeyReleased
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel beanPackageLabel;
    protected javax.swing.JTextField beanPackageTextField;
    private javax.swing.JCheckBox cbPackageJars;
    private javax.swing.JCheckBox cbValidate;
    private javax.swing.JCheckBox cbVerify;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lServletName;
    private javax.swing.JLabel lURLPattern;
    private javax.swing.JTextField tServletName;
    private javax.swing.JTextField tURLPattern;
    // End of variables declaration//GEN-END:variables
 
    boolean valid(WizardDescriptor wizardDescriptor) {
        // <RAVE> Checking default Bean Package
        String beanPkg = beanPackageTextField.getText();
        if (!isValidPkg(beanPkg)) {
            String errMsg = NbBundle.getMessage(JSFConfigurationPanelVisual.class, "MSG_InvalidPackage");
            wizardDescriptor.putProperty( "WizardPanel_errorMessage", errMsg); // NOI18N
            return false;
        }
        // </RAVE>

        String urlPattern = tURLPattern.getText();
        if (urlPattern == null || urlPattern.trim().equals("")){
          wizardDescriptor.putProperty("WizardPanel_errorMessage",                                  // NOI18N
                NbBundle.getMessage(JSFConfigurationPanelVisual.class, "MSG_URLPatternIsEmpty"));
          return false;
        }
        if (!isPatternValid(urlPattern)){
          wizardDescriptor.putProperty("WizardPanel_errorMessage",                                  // NOI18N
                NbBundle.getMessage(JSFConfigurationPanelVisual.class, "MSG_URLPatternIsNotValid"));
          return false;
        }
        if(wizardDescriptor!=null)
            wizardDescriptor.putProperty("WizardPanel_errorMessage", null);                             // NOI18N
        return true;
    }
    
    // <RAVE> Default Bean Package
    protected boolean isBeanPackageModified() {
         return beanPackageModified;
    }

    boolean isValidPkg(String pkgName) {
        if (pkgName == null)
            return false;
        String[] pkg = pkgName.split("\\.");
        for (int i = 0; i < pkg.length; i++) {
            if (!Utilities.isJavaIdentifier(pkg[i])) {
                return false;
            }
        }
        return true;
    }
    // </RAVE>

    private boolean isPatternValid(String pattern){
        if (pattern.startsWith("*.")){
            String p = pattern.substring(2);
            if (p.indexOf('.') == -1 && p.indexOf('*') == -1
                    && p.indexOf('/') == -1 && !p.trim().equals(""))
                return true;
        }
        if (pattern.endsWith("/*") && pattern.startsWith("/"))
            return true;
        return false;
    }
    
    void validate (WizardDescriptor d) throws WizardValidationException {
//        projectLocationPanel.validate (d);
    }
    
    void read (WizardDescriptor d) {
//        projectLocationPanel.read(d);
//        optionsPanel.read(d);
    }

    void store(WizardDescriptor d) {
//        projectLocationPanel.store(d);
//        optionsPanel.store(d);
    }
    
    /** Help context where to find more about the paste type action.
     * @return the help context for this action
     */
    public HelpCtx getHelpCtx() {
        return new HelpCtx(JSFConfigurationPanelVisual.class);
    }
    
    public void removeUpdate(javax.swing.event.DocumentEvent e) {
        panel.fireChangeEvent();
    }

    public void insertUpdate(javax.swing.event.DocumentEvent e) {
        panel.fireChangeEvent();
    }

    public void changedUpdate(javax.swing.event.DocumentEvent e) {
        panel.fireChangeEvent();
    }

    // <RAVE> Default Bean Package
    public String getBeanPackage(){
        return beanPackageTextField.getText();
    }
    
    public void setBeanPackage(String pkg_name){
        beanPackageTextField.setText(pkg_name);
    }
    // </RAVE>
    
    public String getServletName(){
        return tServletName.getText();
    }
    
    public void setServletName(String name){
        tServletName.setText(name);
    }
    
    public String getURLPattern(){
        return tURLPattern.getText();
    }
    
    public void setURLPattern(String pattern){
        tURLPattern.setText(pattern);
    }
    
    public boolean validateXML(){
        return cbValidate.isSelected();
    }
    
    public void setValidateXML(boolean ver){
        cbValidate.setSelected(ver);
    }
    
    public boolean verifyObjects(){
        return cbVerify.isSelected();
    }
    
    public void setVerifyObjects(boolean val){
        cbVerify.setSelected(val);
    }
    
    public boolean packageJars(){
        return cbPackageJars.isSelected();
    }
    
    public void enableComponents(boolean enable){
        cbPackageJars.setEnabled(enable);
        cbValidate.setEnabled(enable);
        cbVerify.setEnabled(enable);
        beanPackageLabel.setEnabled(enable);
        beanPackageTextField.setEnabled(enable);
        lServletName.setEnabled(enable);
        lURLPattern.setEnabled(enable);
        tServletName.setEnabled(enable);
        tURLPattern.setEnabled(enable);
    }
}
