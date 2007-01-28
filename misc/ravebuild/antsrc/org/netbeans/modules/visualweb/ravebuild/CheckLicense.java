/*
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the License). You may not use this file except in
 * compliance with the License.
 *
 * You can obtain a copy of the License at http://www.netbeans.org/cddl.html
 * or http://www.netbeans.org/cddl.txt.

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

package org.netbeans.modules.visualweb.ravebuild;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.FileScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;

/** Check source files for a license notice.
 * @author Jesse Glick
 */
public class CheckLicense extends Task {

    private final List filesets = new ArrayList (1); // List<FileSet>
    private String fragment;
    private List fragments; // List<Fragment>
    private License license;
    private ArrayList delimiters;
    private boolean replaceonly = false;

    /** Add a file set of source files to check.
     * @param fs set of files to check licenses of
     */
    public void addFileSet (FileSet fs) {
        filesets.add (fs);
    }
    
    /** Add a file set of CVS-controlled source files to check.
     * @param fs set of files to check licenses of
     */
    public void addCvsFileSet(CvsFileSet fs) {
        filesets.add(fs);
    }

    /** Set the fragment of license notice which is expected
     * to be found in each source file.
     * @param f the fragment
     */
    public void setFragment (String f) {
        fragment = f;
    }
    
    public Convert createConvert() {
        Convert f = new Convert();
        if (fragments == null) {
            fragments = new ArrayList();
        }
        
        fragments.add(f);
        return f;
    }
    
    public void setReplaceonly(boolean b) {
        replaceonly = b;
    }
    
    public Delimiters createDelimiters() {
        Delimiters d = new Delimiters();
        if (delimiters == null) {
            delimiters = new ArrayList();
        }
        
        delimiters.add(d);
        return d;
    }
    
    public void addLicense(License license) {
        this.license = license;
    }
    

    public void execute() throws BuildException {
        if (replaceonly == false) {
            if (fragment == null) {
                throw new BuildException("You must supply a fragment", getLocation());
            }
            
            if (filesets.isEmpty()) throw new BuildException("You must supply at least one fileset", getLocation());
            
            if (license == null) {
                throw new BuildException("The 'license' subelement must be defined",getLocation());
            }
            
            if (delimiters == null) {
                throw new BuildException("You must supply at least one delimiters element");
            }
        }
        Iterator it = filesets.iterator();
        String failMsg = null;
        try {
            while (it.hasNext()) {
                FileScanner scanner = ((FileSet) it.next()).getDirectoryScanner(getProject());
                File baseDir = scanner.getBasedir();
                String[] files = scanner.getIncludedFiles();
                log("Looking for " + fragment + " in " + files.length + " files in " + baseDir.getAbsolutePath());
                for (int i = 0; i < files.length; i++) {
                    File f = new File(baseDir, files[i]);
                    
                    if (replaceonly == false) {
                        //log("Scanning " + f, Project.MSG_VERBOSE);
                        BufferedReader br = new BufferedReader(new FileReader(f));
                        String line = null;
                        try {
                            while ((line = br.readLine()) != null) {
                                if (line.indexOf(fragment) != -1) {
                                    // Found it.
                                    break;
                                }
                            }
                        } finally {
                            br.close();
                        }
                        
                        if (line == null) {
                            removeOldLicense(f);
                            prependLicense(f);
                        }else {
                            updateLicense(f);
                        }
                    }else {
                        updateLicense(f);
                    }
                }
            }
            
            if (failMsg != null) {
                throw new BuildException(failMsg);
            }
            
        } catch (IOException ioe) {
            throw new BuildException("Could not open files to check licenses", ioe, getLocation());
        }
    }
    
    private void removeOldLicense(File f) throws BuildException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            Vector lines = new Vector();
            
            while (reader.ready()) {
                lines.add(reader.readLine());
            }
            reader.close();
            
            Pattern[] patterns = new Pattern[delimiters.size()];
            for (int i = 0; i < delimiters.size(); i++) {
                String middle = ((Delimiters)delimiters.get(i)).middle;
                patterns[i] = Pattern.compile("(\\s)*" + regex(middle) + ".*[Cc][Oo][Pp][Yy][Rr][Ii][Gg][Hh][Tt].*");
            }
            
            
            boolean changed = false;
            for (int i = 0; i < lines.size() && changed == false; i++) {
                String nextLine = (String)lines.get(i);
                
                // match a line that contains (start-string)...Copyright...
                for (int j = 0; j < patterns.length; j++) {
                    if (patterns[j].matcher(nextLine).matches()) {
                        log("Removing existing copyright from " + f.getAbsolutePath(), Project.MSG_INFO);
                        log("Matched line..." + nextLine);
                        deleteCopyright(lines, i, (Delimiters)delimiters.get(j));
                        changed = true;
                        break;                        
                    }
                    
                }
            }            
            if (changed) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(f));
                
                for (int i = 0; i < lines.size(); i++) {
                    Object next = lines.get(i);
                    if (next != null) {
                        writer.write((String)next);
                        writer.newLine();
                    }
                }
                writer.close();
            }
            
        }catch (IOException ex) {
            throw new BuildException("Could not read/write files to delete old license", ex, getLocation());
        }
    }
    
    private String regex(String str) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            String hex = Integer.toHexString((int)str.charAt(i));
            buffer.append("\\x"+hex);
        }
        return buffer.toString();
    }
    
    private void deleteCopyright(Vector lines, int i, Delimiters delim) {
        String start = delim.start;
        String end = delim.end;
        
        // find the first line of the copyright comment block
        for (int j = i; j >= 0; j--) {
            String curLine = (String)lines.get(j);
            int index = (end == null) ? curLine.indexOf(start) : curLine.lastIndexOf(start);
            
            if (end == null) {
                if (index > 0) {
                    lines.set(j, curLine.substring(0,index));
                }else if (index == 0) {
                    lines.set(j, null);
                }else {
                    break;
                }
            }else {
                if (index > 0) {
                    lines.set(j, curLine.substring(0,index));
                    break;
                }else if (index == 0) {
                    lines.set(j,null);
                    break;
                }else {
                    lines.set(j,null);
                }
            }
        }
        
        
        // find the last line of the copyright comment block
        for (int j = i ; j < lines.size(); j++) {
            String curLine = (String)lines.get(j);
            
            if (curLine == null) {
                continue;
            }else if (end == null) {
                int index = curLine.indexOf(start);
                
                if (index > 0) {
                    lines.set(j, curLine.substring(0,index));
                }else if (index == 0) {
                    lines.set(j, null);
                }else {
                    break;
                }
            }else {
                int index = curLine.indexOf(end);
                int size = end.length();
                
                if (index == -1) {
                    lines.set(j, null);
                }else if (index == curLine.length() - size) {
                    lines.set(j, null);
                    break;
                }else {
                    lines.set(j, curLine.substring(index + size));
                    break;
                }
            }
        }  
    }
    
    private void prependLicense(File f) throws BuildException {
        try {
            log("Inserting license notice in " + f.getAbsolutePath(), Project.MSG_INFO);
            FileInputStream is = new FileInputStream(f);
            StringBuffer sb = new StringBuffer(1024);
            byte[] buffer = new byte[1024];
            int bufferLength;
        
            while ( (bufferLength = is.read(buffer)) != -1) {
                sb.append(new String(buffer, 0, bufferLength));
            }
            is.close();
            
            FileOutputStream os = new FileOutputStream(f);
            os.write(license.repl.getBytes());
            os.write(sb.toString().getBytes());
            os.close();
            
            
        }catch (IOException ex) {
            throw new BuildException("Could not read/write files to insert license", ex, getLocation());
        }
        
        
    }
    
    private void updateLicense(File file) throws BuildException {
        try {
            byte[] workingArray = new byte[4096];
            log("Processing " + file, Project.MSG_VERBOSE);
            
            FileInputStream is = new FileInputStream(file);
            int workingLength = is.read(workingArray);
            if (workingLength == -1) {
                log("Could not update license in " + file.getAbsoluteFile(), Project.MSG_WARN);
                return;
            }
            String workingString = new String(workingArray, 0, workingLength);
            boolean changed = false;
            String prefix = null;
            
            Iterator frags = fragments.iterator();
            while (frags.hasNext()) {
                Convert f = (Convert)frags.next();
                
                Matcher matcher = f.orig.matcher(workingString);
                
                while (matcher.find()) {
                    if (f.prefix) {
                        if (prefix != null) {
                            throw new BuildException("Only one convert element can be prefix!");
                        }
                        if (matcher.groupCount() != 1) {
                            throw new BuildException("There should be one group for the prefix element. Was: " + matcher.groupCount());
                        }
                        prefix = matcher.group(1);
                    }
                    
                    String before = workingString.substring(0, matcher.start());
                    String after = workingString.substring(matcher.end());
                    String middle = wrapWithPrefix(f.repl, prefix, before.length() == 0 || before.endsWith("\n"));
                    
                    if (!middle.equals(matcher.group(0))) {
                        workingString = before + middle + after;
                        log("Matched " + middle, Project.MSG_VERBOSE);
                        changed = true;
                    } else {
                        log("Matched, but no change: " + middle, Project.MSG_VERBOSE);
                    }
                    
                    if (!f.all) {
                        break;
                    } else {
                        matcher = f.orig.matcher(workingString);
                    }
                }
                
            }
            
            byte[] rest = null;
            if (is.available() > 0 && changed) {
                rest = new byte[is.available()];
                int read = is.read(rest);
                assert read == rest.length;
            }
            
            is.close();
            
            
            if (changed) {
                log("Rewriting " + file, Project.MSG_INFO);
                FileOutputStream os = new FileOutputStream(file);
                workingString = Pattern.compile(" +$", Pattern.MULTILINE).matcher(workingString+"X").replaceAll("");
                os.write(workingString.substring(0, workingString.length() - 1).getBytes());
                if (rest != null) {
                    os.write(rest);
                }
                os.close();
            }
        } catch (IOException ioe) {
            throw new BuildException("Could not open files to check license", ioe, getLocation());
        }
    }

    private String wrapWithPrefix(String repl, String prefix, boolean startWithPrefix) {
        if (prefix == null) {
            return repl;
        }
        
        String[] all = repl.split("\n");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < all.length; i++) {
            if (all[i].length() > 0) {
                if (startWithPrefix) {
                    sb.append(prefix);
                }
                sb.append(all[i]);
            }
            if (i < all.length - 1) {
                sb.append('\n');
            }
            startWithPrefix = true;
        }
        
        return sb.toString();
    }

    
    public static final class Convert {
        Pattern orig;
        String repl;
        boolean prefix;
        boolean all;
        
        public void setToken(String orig) {
            this.orig = Pattern.compile(orig, Pattern.DOTALL | Pattern.MULTILINE);
        }
        public void setReplace(String repl) {
            this.repl = repl.replace("\\n", "\n").replace("\\t", "\t");
        }
        public void setPrefix(boolean b) {
            prefix = b;
        }
        public void setReplaceAll(boolean b) {
            all = b;
        }
        public Line createLine() {
            return new Line();
        }
                        
        public final class Line {
            public void setText(String t) {
                if (repl == null) {
                    repl = t;
                } else {
                    repl = repl + "\n" + t;
                }
            }
        }
    }
    
    public static final class Delimiters {
        String start;
        String middle;
        String end;
        
        public void setStart(String start) {
            this.start = start;
        }
        
        public void setMiddle(String middle) {
            this.middle = middle;
        }
        
        public void setEnd(String end) {
            this.end = end;
        }
    }
    
    public static final class License {
        String repl;
        
        public Line createLine() {
            return new Line();
        }
        
        public final class Line {
            public void setText(String t) {
                if (repl == null) {
                    repl = t;
                } else {
                    repl = repl + "\n" + t;
                }
            }
        }
        
    }
}
