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
package org.netbeans.modules.visualweb.dataconnectivity.customizers;

import org.netbeans.modules.visualweb.dataconnectivity.Log;
import org.netbeans.modules.db.sql.visualeditor.querybuilder.QueryBuilder;
import org.netbeans.modules.db.sql.visualeditor.querybuilder.SqlStatement;
import org.netbeans.modules.visualweb.dataconnectivity.ui.QueryTopComponent;
import com.sun.rave.designtime.DesignBean;
import com.sun.rave.designtime.impl.BasicCustomizer2;
import com.sun.sql.rowset.CachedRowSetX;
import com.sun.sql.rowset.JdbcRowSetX;
import java.awt.Component;
import java.awt.Container;
import java.util.Iterator;
import java.util.Set;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.windows.Mode;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;


/**
 *  steps:  this customizer is instatiated with the name of the class
 *    it'll handle:  JdbcRowsetX or cacheRowSetX
 *    the instance is then registered with insync
 *  When a customizer is needed, getCustomizerPanel() is called.
 *  Which calls
 */
public class SqlCommandCustomizer extends BasicCustomizer2 {

    public String customerizerClassName = "" ; // NOI18N
    public SqlCommandCustomizer(String customerizerClassName )  {
        super(null, NbBundle.getMessage(SqlCommandCustomizer.class, "EDIT_QUERY"));        // NOI18N
        this.customerizerClassName = customerizerClassName ; // e.g., com.sun.sql.rowset.JdbcRowSetXImpl.class.getName()
        Log.err.log("Customizer for "+customerizerClassName) ;
    }
    private static boolean useViewData = false ;

    public Component getCustomizerPanel(DesignBean srcBean ) {

        Log.err.log("Customizer panel requested for " + srcBean.getInstanceName() ) ;

        Component retVal ;

        /* see if there's already a TopComponent with the same name **/
        retVal = findCurrent(srcBean) ;
        if ( retVal != null) {
            if ( retVal instanceof TopComponent) {
                ((TopComponent)retVal).requestActive() ;
            }
            return retVal ;
        }

        /****
         * get the dataSourceName
         */
        // Object o = designBean.getInstance();
        String dsName = null ;
        dsName = (String)srcBean.getProperty("dataSourceName").getValue() ;

        SqlStatement sqlStatement = null ;
        try {
            sqlStatement = new SqlStatementImpl( dsName, srcBean ) ;
        } catch (javax.naming.NamingException ne) {
            org.openide.ErrorManager.getDefault().notify(ne);
            sqlStatement = null ;
        }
        if ( sqlStatement == null ) return null ;


        if ( ! useViewData) {
            retVal = QueryBuilder.openCustomizerPanel( sqlStatement ) ;
        } else {
            retVal = QueryTopComponent.openQueryFrame(sqlStatement, dsName) ;
        }
        return retVal ;
    }

    public HelpCtx getHelpCtx() {
        return new HelpCtx( "projrave_ui_elements_editors_about_query_editor" );        // NOI18N
    }
    public static void setUseViewData(boolean use) {
        useViewData = use ;
    }
    public static boolean getUseViewData() {
        return useViewData ;
    }
    static {
        if ( System.getProperty("useViewData")!=null ) {
            useViewData = true ;
        } else {
            useViewData = false ;
        }
    }
    /**
     * Attempt to locate an existing QB for the given
     * designBean.
     */
    public static QueryBuilder findCurrent(DesignBean dBean ) {

        QueryBuilder qbForm = null;

        // Search through workspaces, then modes, then topcomponents
        Set modes = WindowManager.getDefault().getModes();
        Iterator it2 = modes.iterator();

        while (it2.hasNext()) {
            Mode m = (Mode)it2.next();
            TopComponent[] tcs = m.getTopComponents();

            if (tcs != null) {
                for (int j = 0; j < tcs.length; j++) { // for each topcomponents 
                    TopComponent tc = (TopComponent)tcs[j] ;

                    if ( tcs[j] instanceof QueryBuilder) {
                        SqlStatement sss = ((QueryBuilder)tcs[j]).getSqlStatement() ;
                        if ( sss instanceof SqlStatementImpl) {
                            if ( dBean == ((SqlStatementImpl)sss).designBean ) {
                                qbForm = (QueryBuilder)tcs[j] ;
                                break ;
                            }
                        }
                    }
                } // for each topcomponents 
            }

            if (qbForm != null ) {
                break ;
            }
        }

        return qbForm;
    }
    
}
