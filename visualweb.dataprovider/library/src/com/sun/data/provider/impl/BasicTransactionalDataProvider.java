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

package com.sun.data.provider.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import com.sun.data.provider.DataListener;
import com.sun.data.provider.DataProvider;
import com.sun.data.provider.DataProviderException;
import com.sun.data.provider.FieldKey;
import com.sun.data.provider.TransactionalDataListener;
import com.sun.data.provider.TransactionalDataProvider;
import java.util.ResourceBundle;

/**
 *
 * @author Joe Nuxoll
 *         Winston Prakash (Buf Fixes and clean up)
 */
public class BasicTransactionalDataProvider extends AbstractDataProvider
    implements TransactionalDataProvider {

    private transient ResourceBundle bundle = null;
    /**
     *
     */
    protected DataProvider provider;

    /**
     *
     * @param provider DataProvider
     */
    public void setDataProvider(DataProvider provider) {
        if (this.provider != null) {
            this.provider.removeDataListener(ears);
        }
        this.provider = provider;
        this.provider.addDataListener(ears);
        changeMap.clear();
        fireProviderChanged();
    }
    
    /**
     * <p>Return the resource bundle containing our localized messages.</p>
     */
    private ResourceBundle getBundle() {

        if (bundle == null) {
            bundle = ResourceBundle.getBundle("com/sun/data/provider/impl/Bundle");
        }
        return bundle;

    }

    /**
     *
     * @return DataProvider
     */
    public DataProvider getDataProvider() {
        return provider;
    }

    /** {@inheritDoc} */
    public FieldKey[] getFieldKeys() throws DataProviderException {
        if (provider == null) {
            return FieldKey.EMPTY_ARRAY;
        }
        return provider.getFieldKeys();
    }

    /** {@inheritDoc} */
    public FieldKey getFieldKey(String fieldId) throws DataProviderException {
        if (provider == null) {
            throw new DataProviderException(getBundle().getString("NO_DATAPROVIDER_SET"));
        }
        return provider.getFieldKey(fieldId);
    }

    /**
     *
     * @param fieldKey FieldKey
     * @return Class
     */
    public Class getType(FieldKey fieldKey) throws DataProviderException {
        if (provider == null) {
            throw new DataProviderException(getBundle().getString("arbitraryCharData"));
        }
        return provider.getType(fieldKey);
    }

    /** {@inheritDoc} */
    public Object getValue(FieldKey fieldKey) throws DataProviderException {
        if (provider == null) {
            throw new DataProviderException(getBundle().getString("NO_DATAPROVIDER_SET"));
        }
        if (changeMap.containsKey(fieldKey)) {
            return changeMap.get(fieldKey);
        }
        return provider.getValue(fieldKey);
    }

    /** {@inheritDoc} */
    public boolean isReadOnly(FieldKey fieldKey) throws DataProviderException {
        if (provider == null) {
            throw new DataProviderException(getBundle().getString("NO_DATAPROVIDER_SET"));
        }
        return provider.isReadOnly(fieldKey);
    }

    /** {@inheritDoc} */
    public void setValue(FieldKey fieldKey, Object value) throws DataProviderException {
        if (provider == null) {
            throw new DataProviderException(getBundle().getString("NO_DATAPROVIDER_SET"));
        }
        if (provider.isReadOnly(fieldKey)) {
            throw new DataProviderException(getBundle().getString("FK_READ_ONLY"));
        }
        Object oldValue = getValue(fieldKey);
        changeMap.put(fieldKey, value);
        fireValueChanged(fieldKey, oldValue, value);
    }

    /**
     *
     */
    protected HashMap changeMap = new HashMap();

    /**
     *
     */
    public void commitChanges() throws DataProviderException {
        Iterator i = changeMap.keySet().iterator();
        while (i.hasNext()) {
            FieldKey key = (FieldKey)i.next();
            provider.setValue(key, changeMap.get(key));
        }
        changeMap.clear();
        fireChangesCommitted();
    }

    /**
     *
     */
    public void revertChanges() throws DataProviderException {
        changeMap.clear();
        fireChangesReverted();
        fireProviderChanged();
    }

    // ----------------------------------------------------------- Event Methods

    /** {@inheritDoc} */
    public void addTransactionalDataListener(TransactionalDataListener l) {
        super.addDataListener(l);
    }

    /** {@inheritDoc} */
    public void removeTransactionalDataListener(TransactionalDataListener l) {
        super.removeDataListener(l);
    }

    /** {@inheritDoc} */
    public TransactionalDataListener[] getTransactionalDataListeners() {
        if (dpListeners == null) {
            return new TransactionalDataListener[0];
        } else {
            ArrayList cdpList = new ArrayList();
            for (int i = 0; i < dpListeners.length; i++) {
                if (dpListeners[i] instanceof TransactionalDataListener) {
                    cdpList.add(dpListeners[i]);
                }
            }
            return (TransactionalDataListener[])cdpList.toArray(
                new TransactionalDataListener[cdpList.size()]);
        }
    }

    /**
     * Fires a changesCommtted event to each registered {@link
     * TransactionalDataListener}
     *
     * @see TransactionalDataListener#changesCommitted(TransactionalDataProvider)
     */
    protected void fireChangesCommitted() {
        TransactionalDataListener[] cdls = getTransactionalDataListeners();
        for (int i = 0; i < cdls.length; i++) {
            cdls[i].changesCommitted(this);
        }
    }

    /**
     * Fires a changesReverted event to each registered {@link
     * TransactionalDataListener}
     *
     * @see TransactionalDataListener#changesReverted(TransactionalDataProvider)
     */
    protected void fireChangesReverted() {
        TransactionalDataListener[] cdls = getTransactionalDataListeners();
        for (int i = 0; i < cdls.length; i++) {
            cdls[i].changesReverted(this);
        }
    }

    /**
     *
     */
    protected DataListener ears = new DataListener() {
        public void valueChanged(DataProvider provider, FieldKey fieldKey,
            Object oldValue, Object newValue) {
            fireValueChanged(fieldKey, oldValue, newValue);
        }
        public void providerChanged(DataProvider provider) {
            fireProviderChanged();
        }
    };
}
