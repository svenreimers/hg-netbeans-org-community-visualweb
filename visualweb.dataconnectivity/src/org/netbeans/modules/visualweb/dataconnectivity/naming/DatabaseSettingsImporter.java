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

package org.netbeans.modules.visualweb.dataconnectivity.naming;

import org.netbeans.modules.visualweb.dataconnectivity.utils.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.jar.JarFile;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.naming.NameParser;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.netbeans.api.db.explorer.ConnectionManager;
import org.netbeans.api.db.explorer.DatabaseConnection;
import org.netbeans.api.db.explorer.DatabaseException;
import org.netbeans.api.db.explorer.JDBCDriver;
import org.netbeans.api.db.explorer.JDBCDriverManager;
import org.netbeans.modules.visualweb.dataconnectivity.datasource.DataSourceResolver;
import org.netbeans.modules.visualweb.dataconnectivity.model.DataSourceInfo;
import org.openide.ErrorManager;
import org.openide.util.Exceptions;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * DatabaseSettingsImporter imports JDBC drivers and connections from previous Creator/visualweb releases then registers them in DB Explorer
 * @author John Baker
 */
public class DatabaseSettingsImporter {
    
    private static DatabaseSettingsImporter databaseSettingsImporter;
    public static final String  ROOT_CTX_TAG = "rootContext"; // NOI18N
    public static final String  CTX_TAG      = "context"; // NOI18N
    public static final String  OBJ_TAG      = "object"; // NOI18N
    public static final String  ARG_TAG      = "arg"; // NOI18N
    public static final String  NAME_ATTR    = "name"; // NOI18N
    public static final String  CLASS_ATTR   = "class"; // NOI18N
    public static final String  VALUE_ATTR   = "value"; // NOI18N
    private static final int    TAB_WIDTH    = 4;
    private DesignTimeContext   parent;
    private String       ctxName;
    private TreeMap      map;
    private Hashtable    env;
    private NameParser   nameParser = DesignTimeNameParser.getInstance();
    private String       ctxPathName;
    private File         userCtxFile;
    private ArrayList    dataSources;
    private boolean      initMode;   /* used only by initial context ctor, signals not to
                                      * call saveContext during InitialContext construction
                                      */
    
     private static ResourceBundle rb = ResourceBundle.getBundle("org.netbeans.modules.visualweb.dataconnectivity.naming.Bundle", // NOI18N
        Locale.getDefault());
     
    /** Creates a new instance of DatabaseImporter */
    private DatabaseSettingsImporter() {
        dataSources = new ArrayList();
    }
    
    /**
     * this class is a Singleton 
     * @return 
     */
    public static DatabaseSettingsImporter getInstance() {
        if (databaseSettingsImporter == null){
            databaseSettingsImporter = new DatabaseSettingsImporter();
        }
        return databaseSettingsImporter;
    }
    
    
    /**
     * Obtain JDBC driver jars from previous release then register the drivers
     * @return 
     */
    public boolean locateAndRegisterDrivers() {
        String driversPath = locateDrivers();
        if (driversPath.equals("")) // NOI18N
            return false;
        
        registerDrivers(driversToRegister(driversPath));
        return true;
    }
    
    private String locateDrivers() {
        String driverLocation;
        
        driverLocation = System.getProperty("netbeans.user") + File.separator + File.separator + "jdbc-drivers"; // NOI18N
        File driverDir = new File(driverLocation);
        if (driverDir == null)
            return ""; // NOI18N
        if (driverDir.exists())                
           return driverLocation;
        else
           return "";
    }
    
    private File[] driversToRegister(String driversPath) {
        File driverDir = new File(driversPath);
        
        File[] drivers = driverDir.listFiles(
                new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".jar") || name.endsWith(".zip"); // NOI18N
            }
        });
        
        return drivers;        
    }
    
    private void registerDriver(File driverJar) {
        String[] drivers = (String[]) DriverListUtil.getDrivers().toArray(new String[DriverListUtil.getDrivers().size()]);
        
        try {            
            JarFile jf = new JarFile(driverJar);
            
            String drv;
            Set drvs = DriverListUtil.getDrivers();
            Iterator it = drvs.iterator();
            while (it.hasNext()) {
                drv = (String) it.next();
                if (jf.getEntry(drv.replace('.', '/') + ".class") != null) {//NOI18N
                    String driverName = DriverListUtil.findFreeName(DriverListUtil.getName(drv));
                    if (DataSourceResolver.getInstance().findMatchingDriver(driverName) == null)
                        break;
                    JDBCDriver driver = JDBCDriver.create(driverName, driverName, drv, new URL[] {driverJar.toURI().toURL()});
                    try {                        
                        JDBCDriverManager.getDefault().addDriver(driver);
                    } catch (DatabaseException e) {
                        ErrorManager.getDefault().notify(e);
                    }
                }
            }
            jf.close();
                       
        } catch (IOException ioe) {
              ErrorManager.getDefault().notify(ioe);
        } 
    }
    

    private void registerDrivers(File[] driverFiles) {
        for (File drv : driverFiles) {
            registerDriver(drv);
            
        }        
    }
    
    
     /**
      * Obtain connection info from previous release's context.xml then register connections
      * @return 
      */
     public boolean locateAndRegisterConnections() {
        File contextFile = locateContextFile();
        if (contextFile == null)
            return false;
        
        registerConnections(contextFile);
        return true;
    }
    
    private File locateContextFile() {
        String seps =  File.separator ;
        File contextReleaseRoot  = new File(System.getProperty("netbeans.user") + seps + "config" +  seps); // NOI18N
        String contextReleasePath = contextReleaseRoot.getPath();
        String creator2_1Path = contextReleasePath  +  seps + "2_1"; // NOI18N
        String nb55Path = contextReleasePath +  seps  + "5_5"; // NOI18N
        String nb551Path = contextReleasePath  +  seps + "5_5_1"; // NOI18N
        File contextReleaseDir = null;
        File[] configDir = contextReleaseRoot.listFiles();
        boolean found = false;
        File[] contextReleaseDirFiles = null;
        
        for (File releaseDir : configDir) {
            String rPath = releaseDir.getPath();
            
            if ((rPath.equals(creator2_1Path) || rPath.equals(nb55Path) || 
                    rPath.equals(nb551Path))) {
                contextReleaseDir = releaseDir;
                found = true;
                break;
            }
        }
        
        // If there are settings to migrate, list the contents
        if (found) {
            contextReleaseDirFiles = contextReleaseDir.listFiles();
        }
        
        if (contextReleaseDirFiles == null)
            return null;
        
        if (contextReleaseDirFiles[0].exists())
            return contextReleaseDirFiles[0];
        else
            return null;
        

    }
    
    private void registerConnections(File contextFile) {
        ArrayList <DataSourceInfo> dataSourcesInfo = createDataSourceInfoFromCtx(contextFile);
        
        try {           
            Iterator it = dataSourcesInfo.iterator();
            DataSourceInfo dsInfo = null;
            
            // From each Data Source, add a connection to DB Explorer
            while (it.hasNext()) {
                dsInfo = ((DataSourceInfo)it.next());
                String username = dsInfo.getUsername();
                String password = dsInfo.getPassword();
                JDBCDriver drvs = DataSourceResolver.getInstance().findMatchingDriver(dsInfo.getDriverClassName());
                DatabaseConnection dbconn = DatabaseConnection.create(drvs, dsInfo.getUrl(), username,  username.toUpperCase(), password,  true); // NOI18N
                ConnectionManager.getDefault().addConnection(dbconn);
            }
            
        } catch (DatabaseException de) {
            de.printStackTrace();
        }
    }
    
    static private SecretKey secretKey = null;

    static final private char[] secretKeyHex =
    {'D','6','0','7','5','E','2','9','8','A','4','9','6','2','5','1'};

    private static SecretKey getSecretKey() {
        if (secretKey == null) {
            byte[] encodedKey = new byte[secretKeyHex.length/2];
            for (int i = 0; i < encodedKey.length; i++) {
                encodedKey[i] = hexToByte(secretKeyHex[i*2], secretKeyHex[i*2+1]);
            }
            secretKey = new SecretKeySpec(encodedKey, "DES"); // NOI18N
        }

        return secretKey;
    }
    
    private static final String hexString = "0123456789ABCDEF"; // NOI18N

    private static byte hexToByte(char char1, char char2) {
        return (byte)((hexString.indexOf(char1) << 4) + hexString.indexOf(char2));
    }
    
    private String decryptPassword(String password) {

        if (password == null) {
            return null;
        }

        try {
            char[] hexChars = password.toCharArray();
            
            byte[] encryptedBytes = new byte[hexChars.length/2];
            for (int i = 0; i < encryptedBytes.length; i++) {
                encryptedBytes[i] = hexToByte(hexChars[i*2], hexChars[i*2+1]);
            }
            Cipher cipher = Cipher.getInstance("DES"); // NOI18N
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey());
            byte[] passwordBytes = cipher.doFinal(encryptedBytes);
            return new String(passwordBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    
    private DataSourceInfo createDataSourceInfo(String[] dataSource) {                
        String dsName = dataSource[0];
        String driverClassName = dataSource[1];
        String driverUrl = dataSource[2];
        String username = dataSource[3];
        String password = dataSource[4];        
        
        // create data source info
        password = decryptPassword(password);
        
        if (driverClassName.equals("org.apache.derby.jdbc.ClientDriver")) {
            int oldPortLoc = driverUrl.indexOf(":21527");
            if (oldPortLoc != -1) { // NOI18N
                String beginURL = driverUrl.substring(0, oldPortLoc);
                String endURL = driverUrl.substring(driverUrl.lastIndexOf(":21527")+6, driverUrl.length());
                driverUrl = beginURL + ":1527" + endURL;                           
            }
        }
        return new DataSourceInfo(dsName, driverClassName, driverUrl, "", username, password);
    }
    
    private ArrayList<DataSourceInfo> createDataSourceInfoFromCtx(File contextFile) {
        ArrayList <DataSourceInfo> dsInfo = new ArrayList();        
        
        try {
            userCtxFile = contextFile;
            parseContextFile(); 
        } catch (ParserConfigurationException ex) {
            Exceptions.printStackTrace(ex);
        } catch (SAXException ex) {
            Exceptions.printStackTrace(ex);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        
        // extract data source info from each datasource ArrayList
        Iterator itDss = dataSources.iterator();
        String[] dataSource = new String[5];
        
        while (itDss.hasNext()) {
            dataSource = (String[])itDss.next();
            dsInfo.add(createDataSourceInfo(dataSource));
        }
        
        return dsInfo;
}

    private void storeArgs(ArrayList args) {
        String[] dsItems = new String[5];
        
        Iterator it = args.iterator();
        int i = 0;
        int cnt = 0;
        while (it.hasNext()) {           
            // exclude unneeded items of data source
            if ((cnt == 1) || (cnt == 2) || (cnt == 3) || (cnt == 6)) 
                it.next();
            else
                dsItems[i++] = (String)it.next();   
            
            cnt++;
        }
            
        dataSources.add(dsItems);
    }
    
     private void parseContextFile() throws ParserConfigurationException, SAXException, IllegalArgumentException, IOException {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(true);
            factory.setValidating(false);
            SAXParser parser = factory.newSAXParser();       
            final ArrayList storArgs = new ArrayList();

            parser.parse(userCtxFile, new DefaultHandler() {
                private String objectName;
                private String className;  
                private int tagCount = 0;
                ArrayList args = new ArrayList(); 
                public void startElement(String uri, String localName, String qName, Attributes attributes)  throws SAXException {   
                                                           
                   if (qName.equals(OBJ_TAG)) {
                        objectName = attributes.getValue(NAME_ATTR);

                        if (objectName != null) {
                            if (!args.isEmpty()) {                           
                                storeArgs(args);
                            }
                            
                            args.clear();
                            args.add(objectName);                            
                        }

                        
                    } else  if (qName.equals("arg")) {
                        String valueValue = attributes.getValue(VALUE_ATTR);                                                
                        args.add(valueValue);
                    }                        
                }      
                
                public void endDocument() {
                    if (!args.isEmpty()) {
                        storeArgs(args);
                    }
                }
            });           
     }            
}
