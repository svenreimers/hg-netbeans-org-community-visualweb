/*
 * WaitForUpdatePanel.java
 *
 * Created on June 15, 2007, 3:14 PM
 */

package org.netbeans.modules.visualweb.dataconnectivity.ui;

import java.io.IOException;
import java.util.logging.Logger;
import javax.naming.NamingException;
import org.netbeans.api.db.explorer.JDBCDriver;
import org.netbeans.api.db.explorer.JDBCDriverManager;
import org.netbeans.api.project.Project;
import org.netbeans.modules.visualweb.dataconnectivity.naming.DatabaseSettingsImporter;
import org.netbeans.modules.visualweb.dataconnectivity.project.datasource.ProjectDataSourceTracker;
import org.netbeans.modules.visualweb.dataconnectivity.sql.DesignTimeDataSourceHelper;
import org.netbeans.modules.visualweb.dataconnectivity.utils.ImportDataSource;
import org.openide.ErrorManager;

/**
 *
 * @author  jbaker
 */
public class DataSourceCreationNotSupported extends javax.swing.JPanel {
    
    /** Creates new form WaitForUpdatePanel */
    public DataSourceCreationNotSupported() {
        initComponents();             
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        message = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(30, 10));

        org.openide.awt.Mnemonics.setLocalizedText(message, org.openide.util.NbBundle.getMessage(DataSourceCreationNotSupported.class, "MSG_DataSourceNotSupported")); // NOI18N
        message.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        message.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        message.setInheritsPopupMenu(false);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(message, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 178, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(message, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        message.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(DataSourceCreationNotSupported.class, "MSG_DataSourceNotSupported")); // NOI18N
        message.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(DataSourceCreationNotSupported.class, "ACSD_WaitForUpdate")); // NOI18N

        getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(DataSourceCreationNotSupported.class, "LBL_WaitForUpdate_Title")); // NOI18N
        getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(DataSourceCreationNotSupported.class, "ACSD_WaitForUpdate")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel message;
    // End of variables declaration//GEN-END:variables
           
}