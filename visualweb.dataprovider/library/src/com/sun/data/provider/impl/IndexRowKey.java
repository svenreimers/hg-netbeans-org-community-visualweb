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

import com.sun.data.provider.RowKey;

/**
 * <p>IndexRowKey uses an int index as the identifier for a data row in a
 * {@link com.sun.data.provider.TableDataProvider}.</p>
 *
 * @author Joe Nuxoll
 *         Winston Prakash (Buf Fixes and clean up)
 */
public class IndexRowKey extends RowKey {

    /**
     * Constructs a new IndexRowKey from the passed rowId String
     *
     * @param rowId The canonical row ID string to parse into an int
     * @return An IndexRowKey representing the passed rowId
     * @throws java.lang.NumberFormatException If the passed String is not
     *         parsable into an int
     */
    public static IndexRowKey create(String rowId) throws NumberFormatException {
        return new IndexRowKey(Integer.parseInt(rowId));
    }

    /**
     * Constructs an IndexRowKey using the specified index
     *
     * @param index The desired index
     */
    public IndexRowKey(int index) {
        super(String.valueOf(index));
        this.index = index;
    }

    /**
     * Returns the index of this IndexRowKey
     *
     * @return This IndexRowKey's index value
     */
    public int getIndex() {
        return index;
    }

    /**
     * <p>Compare this instance to another {@link IndexRowKey} instance.</p>
     *
     * {@inheritDoc}
     */
    public int compareTo(Object o) {
        IndexRowKey instance = (IndexRowKey) o;
        int oindex = instance.getIndex();
        if (this.index < oindex) {
            return -1;
        } else if (this.index > oindex) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Standard equals implementation.  This method compares the IndexRowKey
     * index values for == equality.  If the passed Object is not an
     * IndexRowKey instance, the superclass (RowKey) gets a chance to evaluate
     * the Object for equality.
     *
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (o instanceof IndexRowKey) {
            return ((IndexRowKey)o).getIndex() == this.index;
        }
        return super.equals(o);
    }

    private int index;

    /**
     * <p>Return a printable version of this instance.</p>
     *
     * {@inheritDoc}
     */
    public String toString() {
        return "RowKey[" + index + "]"; //NOI18N
    }
}
