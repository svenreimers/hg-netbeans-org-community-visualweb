/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2009 Sun Microsystems, Inc. All rights reserved.
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
 * nbbuild/licenses/CDDL-GPL-2-CP.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the GPL Version 2 section of the License file that
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
package com.sun.rave.designtime.ext;


/**
 * <p>These are the common constants used in several places within the Visual Web Design-Time API.
 * These constants are separated into different sub-interfaces to better associate them with where
 * they are used in the API.  See the separate sub-interfaces for detailed documentation.</p>
 */
public interface ConstantsExt {
    /**
     * These constants are used as attribute keys for the BeanDescriptor.  Use the 'setValue' method
     * on the BeanDescriptor to set these values, and the 'getValue' method to retrieve them.
     *
     * @see java.beans.BeanDescriptor#setValue(String, Object)
     * @see java.beans.BeanDescriptor#getValue(String)
     */
    public interface BeanDescriptor {
        
        /**
         * This enum describes the different ways in which the Bean writer
         * wants the tool to handle the instance initialization code.
         */
        enum INSTANCE_INITIALIZATION_PERSISTENCE_MODES {
            /** This indicates that the tool should not generate any code. This
             * is the default in case of Interface and Abstract typed Beans
             * when the <code>INSTANCE_INITIALIZATION_PERSISTENCE_MODE</code> value is
             * not specified in the BeanDescriptor. */
            NONE,
            /** This indicates that the tool should generate the
             * initialization code using the default constructor of the Bean's
             * type. This is the default for Beans of concrete type
             * that have default constructors, when the
             * <code>INSTANCE_INITIALIZATION_PERSISTENCE_MODE</code> value is not
             * specified in the BeanDescriptor. */
            DEFAULT_CONSTRUCTOR,
            /** This indicates that the tool should generate the initialization
             * code using the value of <code>INITIALIZATION_EXPRESSION</code>.
             * The String value of <code>INITIALIZATION_EXPRESSION</code> 
             * should be a syntactically correct expression that
             * returns an instance that can initialize the Bean instance.
             * Otherwise a syntax error may result. If the value is <code>null</code>
             * no initialization code is generated. 
             * 
             * If the value of <code>INITIALIZATION_EXPRESSION</code>
             * is null no code is generated by the tool.
             * 
             * The tool is responsible for cleaning up the code when the Design Bean
             * is deleted. 
             * 
             * The tool is responsible for updating the code when the Design
             * Bean's instance name is changed. */
            INITIALIZATION_EXPRESSION,
            /** This indicates that the tool should generate the
             * initialization code using the factory specified by 
             * <code>INITIALIZATION_STATEMENT_FACTORY</code>. The
             * factory must implement the interface
             * <code>InstanceInitializationStatementFactory</code>.
             * The factory is used to create the statement which is inserted in 
             * the method that initializes the Bean instances at run
             * time e.g. <code>_init()</code> method. The String
             * value should be syntactically correct statement or a statement
             * block that initializes the instance of the Bean. Otherwise a
             * syntax error may result. If the block fails to initilize the
             * instance, a <codejava.lang.NullPointerException</code> may be
             * thrown at run time when the Bean instance is dereferenced.
             * If the value of <code>INITIALIZATION_STATEMENT_FACTORY</code>
             * is null no code is generated by the tool.
             * 
             * The tool is responsible for cleaning up the code when the Design
             * Bean is deleted. 
             * 
             * The tool is responsible for updating the code when the Design
             * Bean's instance name is changed. */
            INITIALIZATION_STATEMENT,
        }
        
        /** This is used by the tool to discover how the Design Bean writer wants
         * the tool to handle the instance initialization code. The allowed
         * values are the set enum constants of the enum
         * <code>INSTANCE_INITIALIZATION_PERSISTENCE_MODES</code> */
        public static final String INSTANCE_INITIALIZATION_PERSISTENCE_MODE =
            "instanceInitializationPersistenceMode";
        
        /**
         * This is used by the tool to discover the intialization expression that 
         * is used to initialize the Bean instance.
         * 
         * @see INSTANCE_INITIALIZATION_PERSISTENCE_MODES.INITIALIZATION_EXPRESSION
         */
        public static final String INITIALIZATION_EXPRESSION =
            "initializationExpression"; // NOI18N
        
        /**
         * This is used by the tool to discover the intialization statement that 
         * is used to initialize the Bean instance.
         * 
         * @see INSTANCE_INITIALIZATION_PERSISTENCE_MODES.INITIALIZATION_STATEMENT
         */
        public static final String INITIALIZATION_STATEMENT_FACTORY =
            "initializationStatementFactory"; // NOI18N
        
        /**
         * This is used by the tool to discover design time instance creation
         * factory. The value should be an instance of 
         * <code>com.sun.rave.designtime.ext.ConstantsExt.DesigntimeInstanceFactory</code>. 
         * 
         * If not <code>null</code> the factory is used to instantiate the instance. 
         */
        public static final String DESIGNTIME_INSTANCE_FACTORY = 
        	"designtimeInstanceFactory"; // NOI18N
    }
}
