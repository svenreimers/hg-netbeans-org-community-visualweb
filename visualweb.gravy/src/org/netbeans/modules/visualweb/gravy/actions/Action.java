/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2010 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
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
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
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

package org.netbeans.modules.visualweb.gravy.actions;

import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.tree.TreePath;

import org.netbeans.jellytools.JellyVersion;
import org.netbeans.modules.visualweb.gravy.MainWindowOperator;
import org.netbeans.modules.visualweb.gravy.Util;
// mdk import org.netbeans.jellytools.nodes.Node;
import org.netbeans.modules.visualweb.gravy.nodes.Node;
import org.netbeans.jemmy.EventTool;
import org.netbeans.jemmy.JemmyException;
import org.netbeans.jemmy.JemmyProperties;
import org.netbeans.jemmy.Timeouts;
import org.netbeans.jemmy.drivers.input.KeyRobotDriver;
import org.netbeans.jemmy.operators.ComponentOperator;
import org.netbeans.jemmy.operators.JPopupMenuOperator;
import org.netbeans.jemmy.operators.Operator;
import org.netbeans.jemmy.operators.Operator.ComponentVisualizer;
import org.netbeans.jemmy.operators.Operator.DefaultStringComparator;
import org.netbeans.jemmy.operators.Operator.StringComparator;
import org.netbeans.jemmy.util.EmptyVisualizer;
import org.openide.util.actions.SystemAction;


/** Ancestor class for all blocking actions.<p>
 * It handles performing action through main menu (MENU_MODE), popup menu
 * (POPUP_MODE), IDE SystemAction API call (API_MODE) or through keyboard shortcut
 * (SHORTCUT_MODE).<p>
 * Action can be performed in exact mode by calling performMenu(...),
 * performPopup(...), performAPI(...) or performShortcut(...).<p>
 * If exact mode is not supported by the action it throws
 * UnsupportedOperationException.<p>
 * Current implementation supports MENU_MODE when menuPath is defined, POPUP_MODE
 * when popupPath is defined, API_MODE when systemActionClass is defined and
 * SHORTCUT_MODE when shorcut is defined (see Action constructors).<p>
 * Action also can be performed using runtime default mode by calling perform(...).<p>
 * When default mode is not support by the action other modes are tried till
 * supported mode found and action is performed.
 *
 * <BR>Timeouts used: <BR>
 * Action.WaitAfterShortcutTimeout - time to sleep between shortcuts in sequence (default 0) <BR>
 *
 * @author <a href="mailto:adam.sotona@sun.com">Adam Sotona</a> */
public class Action {

    /** through menu action performing mode */
    public static final int MENU_MODE = 0;
    /** through popup menu action performing mode */
    public static final int POPUP_MODE = 1;
    /** through API action performing mode */
    public static final int API_MODE = 2;
    /** through shortcut action performing mode */
    public static final int SHORTCUT_MODE = 3;

    /** sleep time between nodes selection and action execution */
    protected static final long SELECTION_WAIT_TIME = 300;
    /** sleep time after action execution */
    protected static final long AFTER_ACTION_WAIT_TIME = 0;
    /** sleep time between sequence of shortcuts */
    protected static final long WAIT_AFTER_SHORTCUT_TIMEOUT = 0;

    private static final int sequence[][] = {{MENU_MODE, POPUP_MODE, SHORTCUT_MODE, API_MODE},
                                             {POPUP_MODE, MENU_MODE, SHORTCUT_MODE, API_MODE},
                                             {API_MODE, POPUP_MODE, MENU_MODE, SHORTCUT_MODE},
                                             {SHORTCUT_MODE, POPUP_MODE, MENU_MODE, API_MODE}};

    /** menu path of current action or null when MENU_MODE is not supported */
    protected String menuPath;
    /** popup menu path of current action or null when POPUP_MODE is not supported */
    protected String popupPath;
    /** SystemAction class of current action or null when API_MODE is not supported */
    protected Class systemActionClass;
    /** array of shortcuts of current action or null when SHORTCUT_MODE is not supported */
    protected Shortcut[] shortcuts;

    /** Comparator used as default for all Action instances. It is set in static clause. */
    private static StringComparator defaultComparator;
    /** Comparator used for this action instance. */
    private StringComparator comparator;

    /** creates new Action instance without API_MODE and SHORTCUT_MODE support
     * @param menuPath action path in main menu (use null value if menu mode is not supported)
     * @param popupPath action path in popup menu (use null value if popup mode shell is not supported) */
    public Action(String menuPath, String popupPath) {
        this(menuPath, popupPath, null, (Shortcut[])null);
    }

    /** creates new Action instance without SHORTCUT_MODE support
     * @param menuPath action path in main menu (use null value if menu mode is not supported)
     * @param popupPath action path in popup menu (use null value if popup mode is not supported)
     * @param systemActionClass String class name of SystemAction (use null value if API mode is not supported) */
    public Action(String menuPath, String popupPath, String systemActionClass) {
        this(menuPath, popupPath, systemActionClass, (Shortcut[])null);
    }

    /** creates new Action instance without API_MODE support
     * @param shortcuts array of Shortcut instances (use null value if shorcut mode is not supported)
     * @param menuPath action path in main menu (use null value if menu mode is not supported)
     * @param popupPath action path in popup menu (use null value if popup mode shell is not supported) */
    public Action(String menuPath, String popupPath, Shortcut[] shortcuts) {
        this(menuPath, popupPath, null, shortcuts);
    }

    /** creates new Action instance without API_MODE support
     * @param shortcut Shortcut (use null value if menu mode is not supported)
     * @param menuPath action path in main menu (use null value if menu mode is not supported)
     * @param popupPath action path in popup menu (use null value if popup mode shell is not supported) */
    public Action(String menuPath, String popupPath, Shortcut shortcut) {
        this(menuPath, popupPath, null, new Shortcut[] {shortcut});
    }

    /** creates new Action instance
     * @param shortcuts array of Shortcut instances (use null value if shortcut mode is not supported)
     * @param menuPath action path in main menu (use null value if menu mode is not supported)
     * @param popupPath action path in popup menu (use null value if popup mode is not supported)
     * @param systemActionClass String class name of SystemAction (use null value if API mode is not supported) */
    public Action(String menuPath, String popupPath, String systemActionClass, Shortcut[] shortcuts) {
        this.menuPath = menuPath;
        this.popupPath = popupPath;
        if (systemActionClass==null) {
            this.systemActionClass = null;
        } else try {
            this.systemActionClass = Class.forName(systemActionClass);
        } catch (ClassNotFoundException e) {
            this.systemActionClass = null;
        }
        this.shortcuts = shortcuts;
    }

    /** creates new Action instance
     * @param shortcut Shortcut String (use null value if menu mode is not supported)
     * @param menuPath action path in main menu (use null value if menu mode is not supported)
     * @param popupPath action path in popup menu (use null value if popup mode is not supported)
     * @param systemActionClass String class name of SystemAction (use null value if API mode is not supported) */
    public Action(String menuPath, String popupPath, String systemActionClass, Shortcut shortcut) {
        this(menuPath, popupPath, systemActionClass, new Shortcut[] {shortcut});
    }

    static {
        // Checks if you run on correct jemmy version. Writes message to jemmy log if not.
        JellyVersion.checkJemmyVersion();

        if (JemmyProperties.getCurrentProperty("Action.DefaultMode")==null)
            JemmyProperties.setCurrentProperty("Action.DefaultMode", new Integer(POPUP_MODE));
        Timeouts.initDefault("Action.WaitAfterShortcutTimeout", WAIT_AFTER_SHORTCUT_TIMEOUT);
        // Set case sensitive comparator as default because of
        // very often clash between Cut and Execute menu items.
        // Substring criterion is set according to default string comparator
        boolean compareExactly = !Operator.getDefaultStringComparator().equals("abc", "a"); // NOI18N
        defaultComparator = new DefaultStringComparator(compareExactly, true);
    }

    private void perform(int mode) {
        switch (mode) {
            case POPUP_MODE: performPopup(); break;
            case MENU_MODE: performMenu(); break;
            case API_MODE: performAPI(); break;
            case SHORTCUT_MODE: performShortcut(); break;
            default: throw new IllegalArgumentException("Wrong Action.MODE");
        }
    }

    private void perform(Node node, int mode) {
        switch (mode) {
            case POPUP_MODE: performPopup(node); break;
            case MENU_MODE: performMenu(node); break;
            case API_MODE: performAPI(node); break;
            case SHORTCUT_MODE: performShortcut(node); break;
            default: throw new IllegalArgumentException("Wrong Action.MODE");
        }
    }

    private void perform(Node[] nodes, int mode) {
        switch (mode) {
            case POPUP_MODE: performPopup(nodes); break;
            case MENU_MODE: performMenu(nodes); break;
            case API_MODE: performAPI(nodes); break;
            case SHORTCUT_MODE: performShortcut(nodes); break;
            default: throw new IllegalArgumentException("Wrong Action.MODE");
        }
    }

    private void perform(ComponentOperator component, int mode) {
        switch (mode) {
            case POPUP_MODE: performPopup(component); break;
            case MENU_MODE: performMenu(component); break;
            case API_MODE: performAPI(component); break;
            case SHORTCUT_MODE: performShortcut(component); break;
            default: throw new IllegalArgumentException("Wrong Action.MODE");
        }
    }


    /** performs action depending on default mode,<br>
     * calls performPopup(), performMenu() or performAPI(),<br>
     * when default mode is not supported, others are tried */
    public void perform() {
        int modes[] = sequence[getDefaultMode()];
        for (int i=0; i<modes.length; i++) try {
            perform(modes[i]);
            return;
        } catch (UnsupportedOperationException e) {}
    }

    /** performs action depending on default mode,<br>
     * calls performPopup(), performMenu() or performAPI(),<br>
     * when default mode is not supported, others are tried
     * @param node node to be action performed on */
    public void perform(Node node) {
        int modes[] = sequence[getDefaultMode()];
        for (int i=0; i<modes.length; i++) try {
            perform(node, modes[i]);
            return;
        } catch (UnsupportedOperationException e) {}
    }

    /** performs action depending on default mode,<br>
     * calls performPopup(), performMenu() or performAPI(),<br>
     * when default mode is not supported, others are tried
     * @param nodes nodes to be action performed on */
    public void perform(Node[] nodes) {
        int modes[] = sequence[getDefaultMode()];
        for (int i=0; i<modes.length; i++) try {
            perform(nodes, modes[i]);
            return;
        } catch (UnsupportedOperationException e) {}
    }

    /** performs action depending on default mode,<br>
     * calls performPopup(), performMenu() or performAPI(),<br>
     * when default mode is not supported, others are tried
     * @param component component to be action performed on */
    public void perform(ComponentOperator component) {
        int modes[] = sequence[getDefaultMode()];
        for (int i=0; i<modes.length; i++) try {
            perform(component, modes[i]);
            return;
        } catch (UnsupportedOperationException e) {}
    }

    /** performs action through main menu
     * @throws UnsupportedOperationException when action does not support menu mode */
    public void performMenu() {
        if (menuPath==null) {
            throw new UnsupportedOperationException(getClass().toString()+" does not define menu path");
        }
        // Need to wait here to be more reliable.
        // TBD - It can be removed after issue 23663 is solved.
        new EventTool().waitNoEvent(500);
        Util.getMainMenu().pushMenu(menuPath);
        try {
            Thread.sleep(AFTER_ACTION_WAIT_TIME);
        } catch (Exception e) {
            throw new JemmyException("Sleeping interrupted", e);
        }
    }

    /** performs action through main menu
     * @param node node to be action performed on
     * @throws UnsupportedOperationException when action does not support menu mode */
    public void performMenu(Node node) {
        performMenu(new Node[]{node});
    }

    /** performs action through main menu
     * @param nodes nodes to be action performed on
     * @throws UnsupportedOperationException when action does not support menu mode */
    public void performMenu(Node[] nodes) {
        if (menuPath==null)
            throw new UnsupportedOperationException(getClass().toString()+" does not define menu path");
        testNodes(nodes);
        nodes[0].select();
        for (int i=1; i<nodes.length; i++)
            nodes[i].addSelectionPath();
        try {
            Thread.sleep(SELECTION_WAIT_TIME);
        } catch (Exception e) {
            throw new JemmyException("Sleeping interrupted", e);
        }
        performMenu();
    }

    /** performs action through main menu
     * @param component component to be action performed on
     * @throws UnsupportedOperationException when action does not support menu mode */
    public void performMenu(ComponentOperator component) {
        component.getFocus();
        try {
            Thread.sleep(SELECTION_WAIT_TIME);
        } catch (Exception e) {
            throw new JemmyException("Sleeping interrupted", e);
        }
        performMenu();
    }

    /** performs action through popup menu
     * @throws UnsupportedOperationException */
    public void performPopup() {
        throw new UnsupportedOperationException(getClass().toString()+" does not implement performPopup()");
    }

    /** performs action through popup menu
     * @param node node to be action performed on
     * @throws UnsupportedOperationException when action does not support popup mode */
    public void performPopup(Node node) {
        performPopup(new Node[]{node});
    }

    /** performs action through popup menu
     * @param nodes nodes to be action performed on
     * @throws UnsupportedOperationException when action does not support popup mode */
    public void performPopup(Node[] nodes) {
        if (popupPath==null) {
            throw new UnsupportedOperationException(getClass().toString()+" does not define popup path");
        }
        testNodes(nodes);
        TreePath paths[]=new TreePath[nodes.length];
        for (int i=0; i<nodes.length; i++) {
            paths[i]=nodes[i].getTreePath();
        }
        ComponentVisualizer treeVisualizer = nodes[0].tree().getVisualizer();
        ComponentVisualizer oldVisualizer = null;
        // If visualizer of JTreeOperator is EmptyVisualizer, we need
        // to avoid making tree component visible in callPopup method.
        // So far only known case is tree from TreeTableOperator.
        if(treeVisualizer instanceof EmptyVisualizer) {
            oldVisualizer = Operator.getDefaultComponentVisualizer();
            Operator.setDefaultComponentVisualizer(treeVisualizer);
        }
        // Need to wait here to be more reliable.
        // TBD - It can be removed after issue 23663 is solved.
        new EventTool().waitNoEvent(500);
        JPopupMenuOperator popup=new JPopupMenuOperator(nodes[0].tree().callPopupOnPaths(paths));
        // restore previously used default visualizer
        if(oldVisualizer != null) {
            Operator.setDefaultComponentVisualizer(oldVisualizer);
        }
        popup.pushMenu(popupPath, "|", getComparator());
        try {
            Thread.sleep(AFTER_ACTION_WAIT_TIME);
        } catch (Exception e) {
            throw new JemmyException("Sleeping interrupted", e);
        }
    }

    /** performs action through popup menu
     * @param component component to be action performed on
     * @throws UnsupportedOperationException when action does not support popup mode */
    public void performPopup(ComponentOperator component) {
        if (popupPath==null) {
            throw new UnsupportedOperationException(getClass().toString()+" does not define popup path");
        }
        // Need to wait here to be more reliable.
        // TBD - It can be removed after issue 23663 is solved.
        new EventTool().waitNoEvent(500);
        component.clickForPopup();
        new JPopupMenuOperator(component).pushMenu(popupPath, "|", getComparator());
        try {
            Thread.sleep(AFTER_ACTION_WAIT_TIME);
        } catch (Exception e) {
            throw new JemmyException("Sleeping interrupted", e);
        }
    }

    /** performs action through API
     * @throws UnsupportedOperationException when action does not support API mode */
    public void performAPI() {
        if (systemActionClass==null) {
            throw new UnsupportedOperationException(getClass().toString()+" does not support API call.");
        }
        try {
            // actions has to be invoked in dispatch thread (see http://www.netbeans.org/issues/show_bug.cgi?id=35755)
            EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    if(SystemAction.class.isAssignableFrom(systemActionClass)) {
                        // SystemAction used in IDE
                        SystemAction.get(systemActionClass).actionPerformed(
                                                new ActionEvent(new Container(), 0, null));
                    } else {
                        // action implements javax.swing.Action
                        try {
                            ((javax.swing.Action)systemActionClass.newInstance()).actionPerformed(null);
                        } catch (Exception e) {
                            throw new JemmyException("Exception when trying to create instance of action \""+systemActionClass.getName()+"\".", e);
                        }
                    }
                }
            });
            Thread.sleep(AFTER_ACTION_WAIT_TIME);
        } catch (Exception e) {
            throw new JemmyException("Interrupted", e);
        }
    }

    /** performs action through API
     * @param node node to be action performed on
     * @throws UnsupportedOperationException when action does not support API mode */
    public void performAPI(Node node) {
        performAPI(new Node[]{node});
    }

    /** performs action through API
     * @param nodes nodes to be action performed on
     * @throws UnsupportedOperationException when action does not support API mode */
    public void performAPI(Node[] nodes) {
        if (systemActionClass==null)
            throw new UnsupportedOperationException(getClass().toString()+" does not define SystemAction");
        testNodes(nodes);
        nodes[0].select();
        for (int i=1; i<nodes.length; i++)
            nodes[i].addSelectionPath();
        try {
            Thread.sleep(SELECTION_WAIT_TIME);
        } catch (Exception e) {
            throw new JemmyException("Sleeping interrupted", e);
        }
        performAPI();
    }

    /** performs action through API
     * @param component component to be action performed on
     * @throws UnsupportedOperationException when action does not support API mode */
    public void performAPI(ComponentOperator component) {
        component.getFocus();
        try {
            Thread.sleep(SELECTION_WAIT_TIME);
        } catch (Exception e) {
            throw new JemmyException("Sleeping interrupted", e);
        }
        performAPI();
    }

    /** performs action through shortcut
     * @throws UnsupportedOperationException if no shortcut is defined */
    public void performShortcut() {
        if (shortcuts == null) {
            throw new UnsupportedOperationException(getClass().toString()+" does not define shortcut");
        }
        for(int i=0; i<shortcuts.length; i++) {
            new KeyRobotDriver(null).pushKey(null, shortcuts[i].getKeyCode(), shortcuts[i].getKeyModifiers(), JemmyProperties.getCurrentTimeouts().create("Timeouts.DeltaTimeout"));
            JemmyProperties.getProperties().getTimeouts().sleep("Action.WaitAfterShortcutTimeout");
        }
        try {
            Thread.sleep(AFTER_ACTION_WAIT_TIME);
        } catch (Exception e) {
            throw new JemmyException("Sleeping interrupted", e);
        }
    }

    /** performs action through shortcut
     * @param node node to be action performed on
     * @throws UnsupportedOperationException when action does not support shortcut mode */
    public void performShortcut(Node node) {
        performShortcut(new Node[]{node});
    }

    /** performs action through shortcut
     * @param nodes nodes to be action performed on
     * @throws UnsupportedOperationException when action does not support shortcut mode */
    public void performShortcut(Node[] nodes) {
        if (shortcuts == null) {
            throw new UnsupportedOperationException(getClass().toString()+" does not define shortcut");
        }
        testNodes(nodes);
        nodes[0].select();
        for (int i=1; i<nodes.length; i++)
            nodes[i].addSelectionPath();
        try {
            Thread.sleep(SELECTION_WAIT_TIME);
        } catch (Exception e) {
            throw new JemmyException("Sleeping interrupted", e);
        }
        performShortcut();
    }

    /** performs action through shortcut
     * @param component component to be action performed on
     * @throws UnsupportedOperationException when action does not support shortcut mode */
    public void performShortcut(ComponentOperator component) {
        component.getFocus();
        try {
            Thread.sleep(SELECTION_WAIT_TIME);
        } catch (Exception e) {
            throw new JemmyException("Sleeping interrupted", e);
        }
        performShortcut();
    }

    /** tests if nodes are all from the same tree
     * @param nodes nodes
     * @throws IllegalArgumentException when given nodes does not pass */
    protected void testNodes(Node[] nodes) {
        if ((nodes==null)||(nodes.length==0))
            throw new IllegalArgumentException("argument nodes is null or empty");
        Class myClass = getClass();
        Component nodesTree=nodes[0].tree().getSource();
        for (int i=0; i<nodes.length; i++) {
//            if (!nodes[i].hasAction(myClass))
//                throw new IllegalArgumentException(this.toString()+" could not be performed on "+nodes[i].toString());
            if (nodes[i]==null)
                throw new IllegalArgumentException("argument nodes contains null value");
            if (!nodesTree.equals(nodes[i].tree().getSource()))
                throw new IllegalArgumentException(nodes[i].toString()+" is from different tree");
        }
    }

    /** Returns default mode in which actions are performed.
     * @return default mode in which actions are performed
     * @see #POPUP_MODE
     * @see #MENU_MODE
     * @see #API_MODE
     * @see #SHORTCUT_MODE
     */
    public int getDefaultMode() {
        int mode=(((Integer)JemmyProperties.getCurrentProperty("Action.DefaultMode")).intValue());
        if (mode<0 || mode>3)
            return POPUP_MODE;
        return mode;
    }

    /** Sets default mode in which actions are performed. If given mode value
     * is not valid, it sets {@link #POPUP_MODE} as default.
     * @param mode mode to be set
     * @return previous value
     * @see #POPUP_MODE
     * @see #MENU_MODE
     * @see #API_MODE
     * @see #SHORTCUT_MODE
     */
    public int setDefaultMode(int mode) {
        int oldMode = (((Integer)JemmyProperties.getCurrentProperty("Action.DefaultMode")).intValue());
        if (mode<0 || mode>3) {
            mode = POPUP_MODE;
        }
        JemmyProperties.setCurrentProperty("Action.DefaultMode", new Integer(mode));
        return oldMode;
    }


    /** Sets comparator fot this action. Comparator is used for all actions
     * after this method is called.
     * @param comparator new comparator to be set (e.g.
     *                   new Operator.DefaultStringComparator(true, true);
     *                   to search string item exactly and case sensitive)
     */
    public void setComparator(StringComparator comparator) {
        this.comparator = comparator;
    }

    /** Gets comparator set for this action instance.
     * @return comparator set for this action instance.
     */
    public StringComparator getComparator() {
        if(comparator == null) {
            comparator = defaultComparator;
        }
        return comparator;
    }

    /** getter for popup menu path
     * @return String popup menu path (or null if not suported)
     */
    public String getPopupPath() {
        return popupPath;
    }

    /** getter for main menu path
     * @return String main menu path (or null if not suported)
     */
    public String getMenuPath() {
        return menuPath;
    }

    /** getter for system action class
     * @return Class of system action (or null if not suported)
     */
    public Class getSystemActionClass() {
        return systemActionClass;
    }

    /** getter for array of shortcuts
     * @return Shortcut[] (or null if not suported)
     */
    public Shortcut[] getShortcuts() {
        return shortcuts;
    }

    /** Checks whether this action is enabled. If IDE system action class
     * is defined, it calls its isEnabled() method else if main menu path is
     * defined, it checks menu item is enabled. Otherwise it throws
     * UnsupportedOperationException.
     * @return true if this action is enabled; false otherwise
     */
    public boolean isEnabled() {
        if(systemActionClass != null) {
            return SystemAction.get(systemActionClass).isEnabled();
        } else if(menuPath != null) {
            return Util.getMainMenu().showMenuItem(
                                    menuPath, "|", getComparator()).isEnabled();
        } else {
            throw new UnsupportedOperationException("Cannot detect if "+getClass().getName()+" is enabled.");
        }
    }

    /** Checks whether this action on given nodes is enabled. If IDE system action class
     * is defined, it calls its isEnabled() method. Nodes are selected first.
     * Else if popup menu path is defined, it checks menu item is enabled.
     * Otherwise it throws UnsupportedOperationException.
     * @param nodes array of nodes to be selected before a check
     * @return true if this action is enabled; false otherwise
     */
    public boolean isEnabled(Node[] nodes) {
        testNodes(nodes);
        if(systemActionClass != null) {
            nodes[0].select();
            for (int i=1; i<nodes.length; i++)
                nodes[i].addSelectionPath();
            try {
                Thread.sleep(SELECTION_WAIT_TIME);
            } catch (Exception e) {
                throw new JemmyException("Sleeping interrupted", e);
            }
            return SystemAction.get(systemActionClass).isEnabled();
        } else if(popupPath != null) {
            TreePath paths[]=new TreePath[nodes.length];
            for (int i=0; i<nodes.length; i++) {
                paths[i]=nodes[i].getTreePath();
            }
            ComponentVisualizer treeVisualizer = nodes[0].tree().getVisualizer();
            ComponentVisualizer oldVisualizer = null;
            // If visualizer of JTreeOperator is EmptyVisualizer, we need
            // to avoid making tree component visible in callPopup method.
            // So far only known case is tree from TreeTableOperator.
            if(treeVisualizer instanceof EmptyVisualizer) {
                oldVisualizer = Operator.getDefaultComponentVisualizer();
                Operator.setDefaultComponentVisualizer(treeVisualizer);
            }
            // Need to wait here to be more reliable.
            // TBD - It can be removed after issue 23663 is solved.
            new EventTool().waitNoEvent(500);
            JPopupMenuOperator popup=new JPopupMenuOperator(nodes[0].tree().callPopupOnPaths(paths));
            // restore previously used default visualizer
            if(oldVisualizer != null) {
                Operator.setDefaultComponentVisualizer(oldVisualizer);
            }
            return popup.showMenuItem(popupPath, "|", getComparator()).isEnabled();
        } else {
            throw new UnsupportedOperationException("Cannot detect if "+getClass().getName()+" is enabled.");
        }
    }

    /** Checks whether this action on given node is enabled. If IDE system action class
     * is defined, it calls its isEnabled() method. Node is selected first.
     * Else if popup menu path is defined, it checks menu item is enabled.
     * Otherwise it throws UnsupportedOperationException.
     * @param node node to be selected before a check
     * @return true if this action is enabled; false otherwise
     */
    public boolean isEnabled(Node node) {
        return isEnabled(new Node[] {node});
    }

    /** Checks whether this action is enabled for given ComponentOperator.
     * First it makes component visible and focused.
     * If IDE system action class is defined, it calls its isEnabled() method.
     * Else if main menu path is defined, it checks menu item is enabled.
     * Otherwise it throws UnsupportedOperationException.
     * @param componentOperator instance of ComponentOperator
     * @return true if this action is enabled; false otherwise
     */
    public boolean isEnabled(ComponentOperator componentOperator) {
        componentOperator.makeComponentVisible();
        componentOperator.getFocus();
        if(systemActionClass != null) {
            return SystemAction.get(systemActionClass).isEnabled();
        } else if(popupPath != null) {
            // Need to wait here to be more reliable.
            // TBD - It can be removed after issue 23663 is solved.
            new EventTool().waitNoEvent(500);
            componentOperator.clickForPopup();
            return new JPopupMenuOperator(componentOperator).showMenuItem(
                                    popupPath, "|", getComparator()).isEnabled();
        } else if(menuPath != null) {
            return Util.getMainMenu().showMenuItem(
                                    menuPath, "|", getComparator()).isEnabled();
        } else {
            throw new UnsupportedOperationException("Cannot detect if "+getClass().getName()+" is enabled.");
        }
    }

    /** This class defines keyboard shortcut for action execution */
    public static class Shortcut extends Object {
        /** key code of shortcut (see KeyEvent) */
        protected int keyCode;
        /** key modifiers of shortcut (see KeyEvent) */
        protected int keyModifiers;

        /** creates new shortcut
         * @param keyCode int key code (see KeyEvent) */
        public Shortcut(int keyCode) {
            this(keyCode, 0);
        }

        /** creates new shortcut
         * @param keyCode int key code (see KeyEvent)
         * @param keyModifiers int key modifiers (see KeyEvent) */
        public Shortcut(int keyCode, int keyModifiers) {
            this.keyCode=keyCode;
            this.keyModifiers=keyModifiers;
        }

        /** getter for key code
         * @return int key code */
        public int getKeyCode() {
            return keyCode;
        }

        /** getter for key modifiers
         * @return int key modifiers */
        public int getKeyModifiers() {
            return keyModifiers;
        }

        /** returns String representation of shortcut
         * @return String representation of shortcut */
        public String toString() {
            String s=KeyEvent.getKeyModifiersText(getKeyModifiers());
            return s+(s.length()>0?"+":"")+KeyEvent.getKeyText(getKeyCode());
        }
    }
}
