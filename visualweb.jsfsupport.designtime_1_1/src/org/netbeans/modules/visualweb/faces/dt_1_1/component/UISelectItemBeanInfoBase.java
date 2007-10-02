/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 1997-2007 Sun Microsystems, Inc. All rights reserved.
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

package org.netbeans.modules.visualweb.faces.dt_1_1.component;

import java.awt.Image;
import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.Locale;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.sun.rave.designtime.CategoryDescriptor;
import com.sun.rave.designtime.Constants;
import com.sun.rave.designtime.faces.FacetDescriptor;
import com.sun.rave.designtime.markup.AttributeDescriptor;

import org.netbeans.modules.visualweb.faces.dt.HtmlBeanInfoBase;
import org.netbeans.modules.visualweb.faces.dt.BeanDescriptorBase;
import org.netbeans.modules.visualweb.faces.dt.PropertyDescriptorBase;

/**
 * <p>Auto-generated design time metadata class.
 * Do <strong>NOT</strong> modify; all changes
 * <strong>will</strong> be lost!</p>
 */

abstract class UISelectItemBeanInfoBase extends HtmlBeanInfoBase {

    protected static ResourceBundle resources = ResourceBundle.getBundle("org.netbeans.modules.visualweb.faces.dt_1_1.component.Bundle-JSF", Locale.getDefault(), UISelectItemBeanInfoBase.class.getClassLoader());

    /**
     * <p>Construct a new <code>UISelectItemBeanInfoBase</code>.</p>
     */
    public UISelectItemBeanInfoBase() {

        beanClass = javax.faces.component.UISelectItem.class;
        iconFileName_C16 = "/org/netbeans/modules/visualweb/faces/dt_1_1/component/UISelectItem_C16";
        iconFileName_C32 = "/org/netbeans/modules/visualweb/faces/dt_1_1/component/UISelectItem_C32";
        iconFileName_M16 = "/org/netbeans/modules/visualweb/faces/dt_1_1/component/UISelectItem_M16";
        iconFileName_M32 = "/org/netbeans/modules/visualweb/faces/dt_1_1/component/UISelectItem_M32";

    }

    private BeanDescriptor beanDescriptor;

    /**
     * <p>Return the <code>BeanDescriptor</code> for this bean.</p>
     */
    public BeanDescriptor getBeanDescriptor() {

        if (beanDescriptor != null) {
            return beanDescriptor;
        }

        beanDescriptor = new BeanDescriptorBase(beanClass);
        beanDescriptor.setDisplayName(resources.getString("UISelectItem_DisplayName"));
        beanDescriptor.setShortDescription(resources.getString("UISelectItem_Description"));
        beanDescriptor.setExpert(false);
        beanDescriptor.setHidden(false);
        beanDescriptor.setPreferred(false);
        beanDescriptor.setValue(Constants.BeanDescriptor.FACET_DESCRIPTORS,getFacetDescriptors());
        beanDescriptor.setValue(Constants.BeanDescriptor.INSTANCE_NAME,"selectItem");
        beanDescriptor.setValue(Constants.BeanDescriptor.IS_CONTAINER,Boolean.TRUE);
        beanDescriptor.setValue(Constants.BeanDescriptor.PROPERTY_CATEGORIES,getCategoryDescriptors());
        beanDescriptor.setValue(Constants.BeanDescriptor.TAG_NAME,"selectItem");

        return beanDescriptor;

    }

    /**
     * <p>Return the <code>CategoryDescriptor</code> array for the property categories of this component.</p>
     */
    private CategoryDescriptor[] getCategoryDescriptors() {

        return com.sun.rave.designtime.base.CategoryDescriptors.getDefaultCategoryDescriptors();

    }

    /**
     * <p>The cached facet descriptors.</p>
     */
    protected FacetDescriptor[] facetDescriptors;

    /**
     * <p>Return the <code>FacetDescriptor</code>s for this bean.</p>
     */
    public FacetDescriptor[] getFacetDescriptors() {

        if (facetDescriptors != null) {
            return facetDescriptors;
        }
        facetDescriptors = new FacetDescriptor[] {
        };
        return facetDescriptors;

    }

    /**
     * <p>Return a class loaded by name via the class loader that loaded this class.</p>
     */

    private PropertyDescriptor[] propertyDescriptors;

    /**
     * <p>Return the <code>PropertyDescriptor</code>s for this bean.</p>
     */
    public PropertyDescriptor[] getPropertyDescriptors() {

        if (propertyDescriptors != null) {
            return propertyDescriptors;
        }
        AttributeDescriptor attrib = null;

        try {

            PropertyDescriptor prop_attributes = new PropertyDescriptorBase("attributes",beanClass,"getAttributes",null);
            prop_attributes.setDisplayName(resources.getString("UISelectItem_attributes_DisplayName"));
            prop_attributes.setShortDescription(resources.getString("UISelectItem_attributes_Description"));
            prop_attributes.setExpert(false);
            prop_attributes.setHidden(true);
            prop_attributes.setPreferred(false);
            prop_attributes.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.INTERNAL);

            PropertyDescriptor prop_childCount = new PropertyDescriptorBase("childCount",beanClass,"getChildCount",null);
            prop_childCount.setDisplayName(resources.getString("UISelectItem_childCount_DisplayName"));
            prop_childCount.setShortDescription(resources.getString("UISelectItem_childCount_Description"));
            prop_childCount.setExpert(false);
            prop_childCount.setHidden(true);
            prop_childCount.setPreferred(false);
            prop_childCount.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.INTERNAL);

            PropertyDescriptor prop_children = new PropertyDescriptorBase("children",beanClass,"getChildren",null);
            prop_children.setDisplayName(resources.getString("UISelectItem_children_DisplayName"));
            prop_children.setShortDescription(resources.getString("UISelectItem_children_Description"));
            prop_children.setExpert(false);
            prop_children.setHidden(true);
            prop_children.setPreferred(false);
            prop_children.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.INTERNAL);

            PropertyDescriptor prop_facets = new PropertyDescriptorBase("facets",beanClass,"getFacets",null);
            prop_facets.setDisplayName(resources.getString("UISelectItem_facets_DisplayName"));
            prop_facets.setShortDescription(resources.getString("UISelectItem_facets_Description"));
            prop_facets.setExpert(false);
            prop_facets.setHidden(true);
            prop_facets.setPreferred(false);
            prop_facets.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.INTERNAL);

            PropertyDescriptor prop_family = new PropertyDescriptorBase("family",beanClass,"getFamily",null);
            prop_family.setDisplayName(resources.getString("UISelectItem_family_DisplayName"));
            prop_family.setShortDescription(resources.getString("UISelectItem_family_Description"));
            prop_family.setExpert(false);
            prop_family.setHidden(true);
            prop_family.setPreferred(false);
            prop_family.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.INTERNAL);

            PropertyDescriptor prop_id = new PropertyDescriptorBase("id",beanClass,"getId","setId");
            prop_id.setDisplayName(resources.getString("UISelectItem_id_DisplayName"));
            prop_id.setShortDescription(resources.getString("UISelectItem_id_Description"));
            prop_id.setExpert(false);
            prop_id.setHidden(true);
            prop_id.setPreferred(false);
            attrib = new AttributeDescriptor("id",false,null,true);
            prop_id.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_id.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.GENERAL);

            PropertyDescriptor prop_itemDescription = new PropertyDescriptorBase("itemDescription",beanClass,"getItemDescription","setItemDescription");
            prop_itemDescription.setDisplayName(resources.getString("UISelectItem_itemDescription_DisplayName"));
            prop_itemDescription.setShortDescription(resources.getString("UISelectItem_itemDescription_Description"));
            prop_itemDescription.setExpert(false);
            prop_itemDescription.setHidden(false);
            prop_itemDescription.setPreferred(false);
            attrib = new AttributeDescriptor("itemDescription",false,null,true);
            prop_itemDescription.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_itemDescription.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.GENERAL);

            PropertyDescriptor prop_itemDisabled = new PropertyDescriptorBase("itemDisabled",beanClass,"isItemDisabled","setItemDisabled");
            prop_itemDisabled.setDisplayName(resources.getString("UISelectItem_itemDisabled_DisplayName"));
            prop_itemDisabled.setShortDescription(resources.getString("UISelectItem_itemDisabled_Description"));
            prop_itemDisabled.setExpert(false);
            prop_itemDisabled.setHidden(false);
            prop_itemDisabled.setPreferred(false);
            attrib = new AttributeDescriptor("itemDisabled",false,null,true);
            prop_itemDisabled.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_itemDisabled.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.GENERAL);

            PropertyDescriptor prop_itemLabel = new PropertyDescriptorBase("itemLabel",beanClass,"getItemLabel","setItemLabel");
            prop_itemLabel.setDisplayName(resources.getString("UISelectItem_itemLabel_DisplayName"));
            prop_itemLabel.setShortDescription(resources.getString("UISelectItem_itemLabel_Description"));
            prop_itemLabel.setExpert(false);
            prop_itemLabel.setHidden(false);
            prop_itemLabel.setPreferred(false);
            attrib = new AttributeDescriptor("itemLabel",false,null,true);
            prop_itemLabel.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_itemLabel.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.GENERAL);

            PropertyDescriptor prop_itemValue = new PropertyDescriptorBase("itemValue",beanClass,"getItemValue","setItemValue");
            prop_itemValue.setDisplayName(resources.getString("UISelectItem_itemValue_DisplayName"));
            prop_itemValue.setShortDescription(resources.getString("UISelectItem_itemValue_Description"));
            prop_itemValue.setExpert(false);
            prop_itemValue.setHidden(false);
            prop_itemValue.setPreferred(false);
            attrib = new AttributeDescriptor("itemValue",false,null,true);
            prop_itemValue.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_itemValue.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.GENERAL);

            PropertyDescriptor prop_parent = new PropertyDescriptorBase("parent",beanClass,"getParent",null);
            prop_parent.setDisplayName(resources.getString("UISelectItem_parent_DisplayName"));
            prop_parent.setShortDescription(resources.getString("UISelectItem_parent_Description"));
            prop_parent.setExpert(false);
            prop_parent.setHidden(true);
            prop_parent.setPreferred(false);
            prop_parent.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.INTERNAL);

            PropertyDescriptor prop_rendered = new PropertyDescriptorBase("rendered",beanClass,"isRendered","setRendered");
            prop_rendered.setDisplayName(resources.getString("UISelectItem_rendered_DisplayName"));
            prop_rendered.setShortDescription(resources.getString("UISelectItem_rendered_Description"));
            prop_rendered.setExpert(false);
            prop_rendered.setHidden(false);
            prop_rendered.setPreferred(false);
            attrib = new AttributeDescriptor("rendered",false,null,true);
            prop_rendered.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_rendered.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.ADVANCED);

            PropertyDescriptor prop_rendererType = new PropertyDescriptorBase("rendererType",beanClass,"getRendererType","setRendererType");
            prop_rendererType.setDisplayName(resources.getString("UISelectItem_rendererType_DisplayName"));
            prop_rendererType.setShortDescription(resources.getString("UISelectItem_rendererType_Description"));
            prop_rendererType.setExpert(false);
            prop_rendererType.setHidden(true);
            prop_rendererType.setPreferred(false);
            prop_rendererType.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.INTERNAL);

            PropertyDescriptor prop_rendersChildren = new PropertyDescriptorBase("rendersChildren",beanClass,"getRendersChildren",null);
            prop_rendersChildren.setDisplayName(resources.getString("UISelectItem_rendersChildren_DisplayName"));
            prop_rendersChildren.setShortDescription(resources.getString("UISelectItem_rendersChildren_Description"));
            prop_rendersChildren.setExpert(false);
            prop_rendersChildren.setHidden(true);
            prop_rendersChildren.setPreferred(false);
            prop_rendersChildren.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.INTERNAL);

            PropertyDescriptor prop_value = new PropertyDescriptorBase("value",beanClass,"getValue","setValue");
            prop_value.setDisplayName(resources.getString("UISelectItem_value_DisplayName"));
            prop_value.setShortDescription(resources.getString("UISelectItem_value_Description"));
            prop_value.setPropertyEditorClass(org.netbeans.modules.visualweb.faces.dt.std.ValueBindingPropertyEditor.class);
            prop_value.setExpert(false);
            prop_value.setHidden(false);
            prop_value.setPreferred(false);
            attrib = new AttributeDescriptor("value",false,null,true);
            prop_value.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_value.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.DATA);
            prop_value.setValue("ignoreIsBound", "true");

            List<PropertyDescriptor> propertyDescriptorList = new ArrayList<PropertyDescriptor>();
            propertyDescriptorList.add(prop_attributes);
            propertyDescriptorList.add(prop_childCount);
            propertyDescriptorList.add(prop_children);
            propertyDescriptorList.add(prop_facets);
            propertyDescriptorList.add(prop_family);
            propertyDescriptorList.add(prop_id);
            propertyDescriptorList.add(prop_itemDescription);
            propertyDescriptorList.add(prop_itemDisabled);
            propertyDescriptorList.add(prop_itemLabel);
            propertyDescriptorList.add(prop_itemValue);
            propertyDescriptorList.add(prop_parent);
            propertyDescriptorList.add(prop_rendered);
            propertyDescriptorList.add(prop_rendererType);
            propertyDescriptorList.add(prop_rendersChildren);
            propertyDescriptorList.add(prop_value);

            propertyDescriptorList.addAll(Arrays.asList(super.getPropertyDescriptors()));
            propertyDescriptors = propertyDescriptorList.toArray(new PropertyDescriptor[propertyDescriptorList.size()]);
            return propertyDescriptors;

        } catch (IntrospectionException e) {
            e.printStackTrace();
            return null;
        }

    }

}

