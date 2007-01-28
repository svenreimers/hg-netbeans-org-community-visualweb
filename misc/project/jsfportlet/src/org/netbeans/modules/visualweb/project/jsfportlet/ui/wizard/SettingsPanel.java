/*
 * {START_JAVA_COPYRIGHT_NOTICE
 * Copyright 2004 Sun Microsystems, Inc. All rights reserved.
 * Use is subject to license terms.
 * END_COPYRIGHT_NOTICE}
 */


package org.netbeans.modules.visualweb.project.jsfportlet.ui.wizard;

import javax.swing.JPanel;
import org.openide.WizardDescriptor;


abstract class SettingsPanel extends JPanel {

    abstract void store (WizardDescriptor settings);

    abstract void read (WizardDescriptor settings);

    abstract boolean valid (WizardDescriptor settings);
}
