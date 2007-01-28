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

package org.netbeans.modules.visualweb.project.jsf.libraries.provider;

import java.awt.Color;
import java.awt.Component;
import java.beans.Customizer;
import java.io.File;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Collection;
import java.util.Arrays;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.DefaultListCellRenderer;
import javax.swing.event.*;
import org.openide.ErrorManager;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.filesystems.FileUtil;
import org.openide.filesystems.URLMapper;
import org.openide.util.NbBundle;
import org.netbeans.spi.project.libraries.LibraryImplementation;




import javax.swing.filechooser.FileFilter;

/**
 *
 * @author  tom
 */
public class ComponentVolumeCustomizer extends javax.swing.JPanel implements Customizer {

    private String volumeType;
    private LibraryImplementation impl;
    private VolumeContentModel model;

    /**
     * Creates new form ComponentVolumeCustomizer
     */
    ComponentVolumeCustomizer (String volumeType) {
        this.volumeType = volumeType;
        initComponents();
        postInitComponents ();
        this.setName (NbBundle.getMessage(ComponentVolumeCustomizer.class,"TXT_"+volumeType));
    }


    public void addNotify() {
        super.addNotify();
        this.addButton.requestFocus();
    }

    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.addButton.setEnabled(enabled);
        if (this.addURLButton != null) {
            this.addURLButton.setEnabled(enabled);
        }
        int[] indices = content.getSelectedIndices();
        this.removeButton.setEnabled(enabled && indices.length > 0);
        this.downButton.setEnabled(enabled && indices.length > 0 && indices[indices.length-1]<model.getSize()-1);
        this.upButton.setEnabled(enabled && indices.length>0 && indices[0]>0);
    }


    private void postInitComponents () {
        this.content.setCellRenderer(new ContentRenderer());
        this.upButton.setEnabled (false);
        this.downButton.setEnabled (false);
        this.removeButton.setEnabled (false);
        if (this.volumeType.equals(ComponentLibraryTypeProvider.VOLUME_TYPE_CLASSPATH)) {  //NOI18N
            this.addButton.setText (NbBundle.getMessage(ComponentVolumeCustomizer.class,"CTL_AddClassPath"));
            this.addButton.setMnemonic(NbBundle.getMessage(ComponentVolumeCustomizer.class,"MNE_AddClassPath").charAt(0));
            this.message.setText(NbBundle.getMessage(ComponentVolumeCustomizer.class,"CTL_ContentClassPath"));
            this.message.setDisplayedMnemonic(NbBundle.getMessage(ComponentVolumeCustomizer.class,"MNE_ContentClassPath").charAt(0));
            this.addButton.getAccessibleContext().setAccessibleDescription(NbBundle.getMessage(ComponentVolumeCustomizer.class,"AD_AddClassPath"));
            this.message.getAccessibleContext().setAccessibleDescription(NbBundle.getMessage(ComponentVolumeCustomizer.class,"AD_ContentClassPath"));
        }
        else if (this.volumeType.equals(ComponentLibraryTypeProvider.VOLUME_TYPE_JAVADOC)) {  //NOI18N
            this.addButton.setText(NbBundle.getMessage(ComponentVolumeCustomizer.class,"CTL_AddJavadoc"));
            this.addButton.setMnemonic(NbBundle.getMessage(ComponentVolumeCustomizer.class,"MNE_AddJavadoc").charAt(0));
            this.message.setText(NbBundle.getMessage(ComponentVolumeCustomizer.class,"CTL_ContentJavadoc"));
            this.message.setDisplayedMnemonic(NbBundle.getMessage(ComponentVolumeCustomizer.class,"MNE_ContentJavadoc").charAt(0));
            this.addButton.getAccessibleContext().setAccessibleDescription(NbBundle.getMessage(ComponentVolumeCustomizer.class,"AD_AddJavadoc"));
            this.message.getAccessibleContext().setAccessibleDescription(NbBundle.getMessage(ComponentVolumeCustomizer.class,"AD_ContentJavadoc"));
//            this.addURLButton = new JButton ();
//            this.addURLButton.setText(NbBundle.getMessage (ComponentVolumeCustomizer.class,"CTL_AddJavadocURL"));
//            this.addURLButton.setMnemonic(NbBundle.getMessage (ComponentVolumeCustomizer.class,"MNE_AddJavadocURL").charAt(0));
//            this.addURLButton.addActionListener (new ActionListener () {
//                public void actionPerformed(ActionEvent e) {
//                    addURLResource ();
//                }
//            });
//            GridBagConstraints c = new GridBagConstraints();
//            c.gridx = 1;
//            c.gridy = 2;
//            c.gridwidth = GridBagConstraints.REMAINDER;
//            c.gridheight = 1;
//            c.fill = GridBagConstraints.HORIZONTAL;
//            c.anchor = GridBagConstraints.NORTHWEST;
//            c.insets = new Insets (0,6,5,6);
//            ((GridBagLayout)this.getLayout()).setConstraints(this.addURLButton,c);
//            this.add (this.addURLButton);
        }
        else if (this.volumeType.equals(ComponentLibraryTypeProvider.VOLUME_TYPE_SRC)) {  //NOI18N
            this.addButton.setText (NbBundle.getMessage(ComponentVolumeCustomizer.class,"CTL_AddSources"));
            this.addButton.setMnemonic (NbBundle.getMessage(ComponentVolumeCustomizer.class,"MNE_AddSources").charAt(0));
            this.message.setText(NbBundle.getMessage(ComponentVolumeCustomizer.class,"CTL_ContentSources"));
            this.message.setDisplayedMnemonic(NbBundle.getMessage(ComponentVolumeCustomizer.class,"MNE_ContentSources").charAt(0));
            this.addButton.getAccessibleContext().setAccessibleDescription(NbBundle.getMessage(ComponentVolumeCustomizer.class,"AD_AddSources"));
            this.message.getAccessibleContext().setAccessibleDescription(NbBundle.getMessage(ComponentVolumeCustomizer.class,"AD_ContentSources"));
        }
        else if (this.volumeType.equals(ComponentLibraryTypeProvider.VOLUME_TYPE_DESIGNTIME)) {  //NOI18N
            this.addButton.setText (NbBundle.getMessage(ComponentVolumeCustomizer.class,"CTL_AddSources"));
            this.addButton.setMnemonic (NbBundle.getMessage(ComponentVolumeCustomizer.class,"MNE_AddSources").charAt(0));
            this.message.setText(NbBundle.getMessage(ComponentVolumeCustomizer.class,"CTL_ContentSources"));
            this.message.setDisplayedMnemonic(NbBundle.getMessage(ComponentVolumeCustomizer.class,"MNE_ContentSources").charAt(0));
            this.addButton.getAccessibleContext().setAccessibleDescription(NbBundle.getMessage(ComponentVolumeCustomizer.class,"AD_AddSources"));
            this.message.getAccessibleContext().setAccessibleDescription(NbBundle.getMessage(ComponentVolumeCustomizer.class,"AD_ContentSources"));
        }
        this.content.addListSelectionListener(new ListSelectionListener () {
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting())
                    return;
                int[] indices = content.getSelectedIndices();
                removeButton.setEnabled(indices.length > 0);
                downButton.setEnabled(indices.length > 0 && indices[indices.length-1]<model.getSize()-1);
                upButton.setEnabled(indices.length>0 && indices[0]>0);
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        message = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        content = new javax.swing.JList();
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        upButton = new javax.swing.JButton();
        downButton = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        getAccessibleContext().setAccessibleDescription(null);
        message.setLabelFor(content);
        org.openide.awt.Mnemonics.setLocalizedText(message, org.openide.util.NbBundle.getBundle(ComponentVolumeCustomizer.class).getString("CTL_ContentMessage"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 12, 2, 6);
        add(message, gridBagConstraints);

        jScrollPane1.setViewportView(content);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 6, 6);
        add(jScrollPane1, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(addButton, org.openide.util.NbBundle.getBundle(ComponentVolumeCustomizer.class).getString("CTL_AddContent"));
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addResource(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 6, 5, 6);
        add(addButton, gridBagConstraints);
        addButton.getAccessibleContext().setAccessibleDescription(null);

        org.openide.awt.Mnemonics.setLocalizedText(removeButton, org.openide.util.NbBundle.getBundle(ComponentVolumeCustomizer.class).getString("CTL_RemoveContent"));
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeResource(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 6, 6, 6);
        add(removeButton, gridBagConstraints);
        removeButton.getAccessibleContext().setAccessibleDescription(null);

        org.openide.awt.Mnemonics.setLocalizedText(upButton, org.openide.util.NbBundle.getBundle(ComponentVolumeCustomizer.class).getString("CTL_UpContent"));
        upButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upResource(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 6, 0, 6);
        add(upButton, gridBagConstraints);
        upButton.getAccessibleContext().setAccessibleDescription(null);

        org.openide.awt.Mnemonics.setLocalizedText(downButton, org.openide.util.NbBundle.getBundle(ComponentVolumeCustomizer.class).getString("CTL_DownContent"));
        downButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downResource(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 6, 6, 6);
        add(downButton, gridBagConstraints);
        downButton.getAccessibleContext().setAccessibleDescription(null);

    }
    // </editor-fold>//GEN-END:initComponents

    private void downResource(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downResource
        int[] indices = this.content.getSelectedIndices();
        if (indices.length == 0 || indices[0] < 0 || indices[indices.length-1]>=model.getSize()-1) {
            return;
        }
        this.model.moveDown(indices);
        for (int i=0; i< indices.length; i++) {
            indices[i] = indices[i] + 1;
        }
        this.content.setSelectedIndices (indices);
    }//GEN-LAST:event_downResource

    private void upResource(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upResource
        int[] indices = this.content.getSelectedIndices();
        if (indices.length == 0 || indices[0] <= 0) {
            return;
        }
        this.model.moveUp(indices);
        for (int i=0; i< indices.length; i++) {
            indices[i] = indices[i] - 1;
        }
        this.content.setSelectedIndices(indices);
    }//GEN-LAST:event_upResource

    private void removeResource(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeResource
        int[] indices =this.content.getSelectedIndices();
        if (indices.length == 0) {
            return;
        }
        this.model.removeResources(indices);
        if (indices[indices.length-1]-indices.length+1 < this.model.getSize()) {
            this.content.setSelectedIndex(indices[indices.length-1]-indices.length+1);
        }
        else if (indices[0]  >= 1) {
            this.content.setSelectedIndex (indices[0]-1);
        }
    }//GEN-LAST:event_removeResource

    private void addResource(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addResource
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        FileUtil.preventFileChooserSymlinkTraversal(chooser, null);
        chooser.setAcceptAllFileFilterUsed(false);
        if (this.volumeType.equalsIgnoreCase(ComponentLibraryTypeProvider.VOLUME_TYPE_CLASSPATH)) {        //NOI18N
            chooser.setMultiSelectionEnabled (true);
            chooser.setDialogTitle(NbBundle.getMessage(ComponentVolumeCustomizer.class,"TXT_OpenClasses"));
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.setFileFilter (new SimpleFileFilter(NbBundle.getMessage(
                    ComponentVolumeCustomizer.class,"TXT_Classpath"),new String[] {"ZIP","JAR"}));   //NOI18N
            chooser.setApproveButtonText(NbBundle.getMessage(ComponentVolumeCustomizer.class,"CTL_SelectCP"));
            chooser.setApproveButtonMnemonic(NbBundle.getMessage(ComponentVolumeCustomizer.class,"MNE_SelectCP").charAt(0));
        }
        else if (this.volumeType.equalsIgnoreCase(ComponentLibraryTypeProvider.VOLUME_TYPE_JAVADOC)) {     //NOI18N
            chooser.setDialogTitle(NbBundle.getMessage(ComponentVolumeCustomizer.class,"TXT_OpenJavadoc"));
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.setFileFilter (new SimpleFileFilter(NbBundle.getMessage(
                    ComponentVolumeCustomizer.class,"TXT_Javadoc"),new String[] {"ZIP","JAR"}));     //NOI18N
            chooser.setApproveButtonText(NbBundle.getMessage(ComponentVolumeCustomizer.class,"CTL_SelectJD"));
            chooser.setApproveButtonMnemonic(NbBundle.getMessage(ComponentVolumeCustomizer.class,"MNE_SelectJD").charAt(0));
        }
        else if (this.volumeType.equalsIgnoreCase(ComponentLibraryTypeProvider.VOLUME_TYPE_SRC)) {         //NOI18N
            chooser.setDialogTitle(NbBundle.getMessage(ComponentVolumeCustomizer.class,"TXT_OpenSources"));
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.setFileFilter (new SimpleFileFilter(NbBundle.getMessage(
                    ComponentVolumeCustomizer.class,"TXT_Sources"),new String[] {"ZIP","JAR"}));     //NOI18N
            chooser.setApproveButtonText(NbBundle.getMessage(ComponentVolumeCustomizer.class,"CTL_SelectSRC"));
            chooser.setApproveButtonMnemonic(NbBundle.getMessage(ComponentVolumeCustomizer.class,"MNE_SelectSRC").charAt(0));
        }
        else if (this.volumeType.equalsIgnoreCase(ComponentLibraryTypeProvider.VOLUME_TYPE_DESIGNTIME)) {         //NOI18N
            chooser.setDialogTitle(NbBundle.getMessage(ComponentVolumeCustomizer.class,"TXT_OpenSources"));
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.setFileFilter (new SimpleFileFilter(NbBundle.getMessage(
                    ComponentVolumeCustomizer.class,"TXT_Sources"),new String[] {"ZIP","JAR"}));     //NOI18N
            chooser.setApproveButtonText(NbBundle.getMessage(ComponentVolumeCustomizer.class,"CTL_SelectSRC"));
            chooser.setApproveButtonMnemonic(NbBundle.getMessage(ComponentVolumeCustomizer.class,"MNE_SelectSRC").charAt(0));
        }
        if (lastFolder != null) {
            chooser.setCurrentDirectory (lastFolder);
        }
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                lastFolder = chooser.getCurrentDirectory();
                if (chooser.isMultiSelectionEnabled()) {
                    addFiles (chooser.getSelectedFiles());
                }
                else {
                    addFiles (new File[] {chooser.getSelectedFile()});
                }
            } catch (MalformedURLException mue) {
                ErrorManager.getDefault().notify(mue);
            }
        }
    }//GEN-LAST:event_addResource


//    private void addURLResource () {
//        DialogDescriptor.InputLine input = new DialogDescriptor.InputLine (
//                NbBundle.getMessage(ComponentVolumeCustomizer.class,"CTL_AddJavadocURLMessage"),
//                NbBundle.getMessage(ComponentVolumeCustomizer.class,"CTL_AddJavadocURLTitle"));
//        if (DialogDisplayer.getDefault().notify(input) == DialogDescriptor.OK_OPTION) {
//            try {
//                String value = input.getInputText();
//                URL url = new URL (value);
//                this.model.addResource(url);
//                this.content.setSelectedIndex(this.model.getSize()-1);
//            } catch (MalformedURLException mue) {
//                DialogDescriptor.Message message = new DialogDescriptor.Message (
//                        NbBundle.getMessage(ComponentVolumeCustomizer.class,"CTL_InvalidURLFormat"),
//                        DialogDescriptor.ERROR_MESSAGE
//                );
//                DialogDisplayer.getDefault().notify(message);
//            }
//        }
//    }


    private void addFiles (File[] files) throws MalformedURLException {
        int firstIndex = this.model.getSize();
        for (int i = 0; i < files.length; i++) {
            File f = files[i];
            //XXX: JFileChooser workaround (JDK bug #5075580), double click on folder returns wrong file
            // E.g. for /foo/src it returns /foo/src/src
            // Try to convert it back by removing last invalid name component
            if (!f.exists()) {
                File parent = f.getParentFile();
                if (parent != null && f.getName().equals(parent.getName()) && parent.exists()) {
                    f = parent;
                }
            }
            URL url = f.toURI().toURL();
            this.model.addResource(url);
        }
        int lastIndex = this.model.getSize()-1;
        if (firstIndex<=lastIndex) {
            int[] toSelect = new int[lastIndex-firstIndex+1];
            for (int i = 0; i < toSelect.length; i++) {
                toSelect[i] = firstIndex+i;
            }
            this.content.setSelectedIndices(toSelect);
        }
    }

    public void setObject(Object bean) {
        if (bean instanceof LibraryImplementation) {
            this.impl = (LibraryImplementation) bean;
            this.model = new VolumeContentModel(this.impl,this.volumeType);
            this.content.setModel(model);
            if (this.model.getSize()>0) {
                this.content.setSelectedIndex(0);
            }
        }
        else {
            throw new IllegalArgumentException();
        }
    }


    private static class SimpleFileFilter extends FileFilter {

        private String description;
        private Collection extensions;


        public SimpleFileFilter(String description, String[] extensions) {
            this.description = description;
            this.extensions = Arrays.asList(extensions);
        }

        public boolean accept(File f) {
            if (f.isDirectory())
                return true;
            String name = f.getName();
            int index = name.lastIndexOf('.');   //NOI18N
            if (index <= 0 || index==name.length()-1)
                return false;
            String extension = name.substring(index+1).toUpperCase();
            return this.extensions.contains(extension);
        }

        public String getDescription() {
            return this.description;
        }
    }


    private static File lastFolder = null;


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JList content;
    private javax.swing.JButton downButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel message;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton upButton;
    // End of variables declaration//GEN-END:variables
    private JButton addURLButton;

    private static class ContentRenderer extends DefaultListCellRenderer {

        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            String displayName = null;
            Color color = null;
            String toolTip = null;

            if (value instanceof URL) {
                URL url = (URL) value;
                displayName = url.toExternalForm();
                if ("jar".equals(url.getProtocol())) {   //NOI18N
                    url = FileUtil.getArchiveFile (url);
                }
                if (URLMapper.findFileObject (url) == null) {
                    color = new Color (164,0,0);
                    toolTip = NbBundle.getMessage (ComponentVolumeCustomizer.class,"TXT_BrokenFile");
                }
            }
            Component c = super.getListCellRendererComponent(list, displayName, index, isSelected, cellHasFocus);
            if (c instanceof JComponent) {
                if (color != null) {
                    ((JComponent)c).setForeground (color);
                }
                if (toolTip != null) {
                    ((JComponent)c).setToolTipText (toolTip);
                }
            }
            return c;
        }

    }

}
