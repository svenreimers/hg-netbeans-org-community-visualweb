//GEN-BEGIN:BeanInfo
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
package org.netbeans.modules.visualweb.web.ui.dt.component;

import java.awt.Image;
import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.Locale;
import java.util.ResourceBundle;

import com.sun.rave.designtime.CategoryDescriptor;
import com.sun.rave.designtime.Constants;
import com.sun.rave.designtime.faces.FacetDescriptor;
import com.sun.rave.designtime.markup.AttributeDescriptor;

import java.beans.SimpleBeanInfo;

/**
 * <p>Auto-generated design time metadata class.
 * Do <strong>NOT</strong> modify; all changes
 * <strong>will</strong> be lost!</p>
 */

abstract class AddRemoveBeanInfoBase extends SimpleBeanInfo {

    protected static ResourceBundle resources = ResourceBundle.getBundle("org.netbeans.modules.visualweb.web.ui.dt.component.Bundle-JSF", Locale.getDefault(), AddRemoveBeanInfoBase.class.getClassLoader());

    /**
     * <p>Construct a new <code>AddRemoveBeanInfoBase</code>.</p>
     */
    public AddRemoveBeanInfoBase() {

        beanClass = com.sun.rave.web.ui.component.AddRemove.class;
        iconFileName_C16 = "/org/netbeans/modules/visualweb/web/ui/dt/component/AddRemove_C16";
        iconFileName_C32 = "/org/netbeans/modules/visualweb/web/ui/dt/component/AddRemove_C32";
        iconFileName_M16 = "/org/netbeans/modules/visualweb/web/ui/dt/component/AddRemove_M16";
        iconFileName_M32 = "/org/netbeans/modules/visualweb/web/ui/dt/component/AddRemove_M32";

    }

    /**
     * <p>The bean class that this BeanInfo represents.
     */
    protected Class beanClass;

    /**
     * <p>The cached BeanDescriptor.</p>
     */
    protected BeanDescriptor beanDescriptor;

    /**
     * <p>The index of the default property.</p>
     */
    protected int defaultPropertyIndex = -2;

    /**
     * <p>The name of the default property.</p>
     */
    protected String defaultPropertyName;

    /**
     * <p>The 16x16 color icon.</p>
     */
    protected String iconFileName_C16;

    /**
     * <p>The 32x32 color icon.</p>
     */
    protected String iconFileName_C32;

    /**
     * <p>The 16x16 monochrome icon.</p>
     */
    protected String iconFileName_M16;

    /**
     * <p>The 32x32 monochrome icon.</p>
     */
    protected String iconFileName_M32;

    /**
     * <p>The cached property descriptors.</p>
     */
    protected PropertyDescriptor[] propDescriptors;

    /**
     * <p>Return the <code>BeanDescriptor</code> for this bean.</p>
     */
    public BeanDescriptor getBeanDescriptor() {

        if (beanDescriptor != null) {
            return beanDescriptor;
        }

        beanDescriptor = new BeanDescriptor(beanClass);
        beanDescriptor.setDisplayName(resources.getString("AddRemove_DisplayName"));
        beanDescriptor.setShortDescription(resources.getString("AddRemove_Description"));
        beanDescriptor.setExpert(false);
        beanDescriptor.setHidden(false);
        beanDescriptor.setPreferred(false);
        beanDescriptor.setValue(Constants.BeanDescriptor.FACET_DESCRIPTORS,getFacetDescriptors());
        beanDescriptor.setValue(Constants.BeanDescriptor.HELP_KEY,"projrave_ui_elements_palette_bh_add_remove_list");
        beanDescriptor.setValue(Constants.BeanDescriptor.INSTANCE_NAME,"addRemoveList");
        beanDescriptor.setValue(Constants.BeanDescriptor.IS_CONTAINER,Boolean.TRUE);
        beanDescriptor.setValue(Constants.BeanDescriptor.PROPERTIES_HELP_KEY,"projrave_ui_elements_palette_bh_propsheets_bh_addremovelist_props");
        beanDescriptor.setValue(Constants.BeanDescriptor.PROPERTY_CATEGORIES,getCategoryDescriptors());
        beanDescriptor.setValue(Constants.BeanDescriptor.TAG_NAME,"addRemove");
        beanDescriptor.setValue(Constants.BeanDescriptor.TAGLIB_PREFIX,"ui");
        beanDescriptor.setValue(Constants.BeanDescriptor.TAGLIB_URI,"http://www.sun.com/web/ui");

        return beanDescriptor;

    }

    /**
     * <p>Return the <code>CategoryDescriptor</code> array for the property categories of this component.</p>
     */
    private CategoryDescriptor[] getCategoryDescriptors() {

        return com.sun.rave.designtime.base.CategoryDescriptors.getDefaultCategoryDescriptors();

    }

    /**
     * <p>Return the index of the default property, or
     * -1 if there is no default property.</p>
     */
    public int getDefaultPropertyIndex() {

        if (defaultPropertyIndex > -2) {
            return defaultPropertyIndex;
        } else {
            if (defaultPropertyName == null) {
                defaultPropertyIndex = -1;
            } else {
                PropertyDescriptor pd[] = getPropertyDescriptors();
                for (int i = 0; i < pd.length; i++) {
                    if (defaultPropertyName.equals(pd[i].getName())) {
                        defaultPropertyIndex = i;
                        break;
                    }
                }
            }
        }
        return defaultPropertyIndex;
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
        try {

            FacetDescriptor facet_addAllButton = new FacetDescriptor("addAllButton");
            facet_addAllButton.setDisplayName(resources.getString("AddRemove_addAllButton_DisplayName"));
            facet_addAllButton.setShortDescription(resources.getString("AddRemove_addAllButton_Description"));

            FacetDescriptor facet_addButton = new FacetDescriptor("addButton");
            facet_addButton.setDisplayName(resources.getString("AddRemove_addButton_DisplayName"));
            facet_addButton.setShortDescription(resources.getString("AddRemove_addButton_Description"));

            FacetDescriptor facet_footer = new FacetDescriptor("footer");
            facet_footer.setDisplayName(resources.getString("AddRemove_footer_DisplayName"));
            facet_footer.setShortDescription(resources.getString("AddRemove_footer_Description"));

            FacetDescriptor facet_header = new FacetDescriptor("header");
            facet_header.setDisplayName(resources.getString("AddRemove_header_DisplayName"));
            facet_header.setShortDescription(resources.getString("AddRemove_header_Description"));

            FacetDescriptor facet_moveDownButton = new FacetDescriptor("moveDownButton");
            facet_moveDownButton.setDisplayName(resources.getString("AddRemove_moveDownButton_DisplayName"));
            facet_moveDownButton.setShortDescription(resources.getString("AddRemove_moveDownButton_Description"));

            FacetDescriptor facet_moveUpButton = new FacetDescriptor("moveUpButton");
            facet_moveUpButton.setDisplayName(resources.getString("AddRemove_moveUpButton_DisplayName"));
            facet_moveUpButton.setShortDescription(resources.getString("AddRemove_moveUpButton_Description"));

            FacetDescriptor facet_removeAllButton = new FacetDescriptor("removeAllButton");
            facet_removeAllButton.setDisplayName(resources.getString("AddRemove_removeAllButton_DisplayName"));
            facet_removeAllButton.setShortDescription(resources.getString("AddRemove_removeAllButton_Description"));

            FacetDescriptor facet_removeButton = new FacetDescriptor("removeButton");
            facet_removeButton.setDisplayName(resources.getString("AddRemove_removeButton_DisplayName"));
            facet_removeButton.setShortDescription(resources.getString("AddRemove_removeButton_Description"));

            facetDescriptors = new FacetDescriptor[] {
                facet_addAllButton,
                facet_addButton,
                facet_footer,
                facet_header,
                facet_moveDownButton,
                facet_moveUpButton,
                facet_removeAllButton,
                facet_removeButton,
            };
            return facetDescriptors;

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
            throw e;
        }

    }

    /**
     * <p>Return the specified image (if any)
     * for this component class.</p>
     */
    public Image getIcon(int kind) {

        String name;
        switch (kind) {
            case ICON_COLOR_16x16:
                name = iconFileName_C16;
                break;
            case ICON_COLOR_32x32:
                name = iconFileName_C32;
                break;
            case ICON_MONO_16x16:
                name = iconFileName_M16;
                break;
            case ICON_MONO_32x32:
                name = iconFileName_M32;
                break;
            default:
                name = null;
                break;
        }
        if (name == null) {
            return null;
        }

        Image image = loadImage(name + ".png");
        if (image == null) {
            image = loadImage(name + ".gif");
        }
        return image;

    }

    /**
     * <p>Return a class loaded by name via the class loader that loaded this class.</p>
     */
    private java.lang.Class loadClass(java.lang.String name) {

        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * <p>Return the <code>PropertyDescriptor</code>s for this bean.</p>
     */
    public PropertyDescriptor[] getPropertyDescriptors() {

        if (propDescriptors != null) {
            return propDescriptors;
        }
        AttributeDescriptor attrib = null;

        try {

            PropertyDescriptor prop_availableItemsLabel = new PropertyDescriptor("availableItemsLabel",beanClass,"getAvailableItemsLabel","setAvailableItemsLabel");
            prop_availableItemsLabel.setDisplayName(resources.getString("AddRemove_availableItemsLabel_DisplayName"));
            prop_availableItemsLabel.setShortDescription(resources.getString("AddRemove_availableItemsLabel_Description"));
            prop_availableItemsLabel.setPropertyEditorClass(loadClass("com.sun.rave.propertyeditors.StringPropertyEditor"));
            prop_availableItemsLabel.setExpert(false);
            prop_availableItemsLabel.setHidden(false);
            prop_availableItemsLabel.setPreferred(false);
            attrib = new AttributeDescriptor("availableItemsLabel",false,null,true);
            prop_availableItemsLabel.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_availableItemsLabel.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.APPEARANCE);

            PropertyDescriptor prop_duplicateSelections = new PropertyDescriptor("duplicateSelections",beanClass,"isDuplicateSelections","setDuplicateSelections");
            prop_duplicateSelections.setDisplayName(resources.getString("AddRemove_duplicateSelections_DisplayName"));
            prop_duplicateSelections.setShortDescription(resources.getString("AddRemove_duplicateSelections_Description"));
            prop_duplicateSelections.setExpert(false);
            prop_duplicateSelections.setHidden(false);
            prop_duplicateSelections.setPreferred(false);
            attrib = new AttributeDescriptor("duplicateSelections",false,null,true);
            prop_duplicateSelections.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_duplicateSelections.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.DATA);

            PropertyDescriptor prop_moveButtons = new PropertyDescriptor("moveButtons",beanClass,"isMoveButtons","setMoveButtons");
            prop_moveButtons.setDisplayName(resources.getString("AddRemove_moveButtons_DisplayName"));
            prop_moveButtons.setShortDescription(resources.getString("AddRemove_moveButtons_Description"));
            prop_moveButtons.setExpert(false);
            prop_moveButtons.setHidden(false);
            prop_moveButtons.setPreferred(false);
            attrib = new AttributeDescriptor("moveButtons",false,null,true);
            prop_moveButtons.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_moveButtons.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.APPEARANCE);

            PropertyDescriptor prop_selectAll = new PropertyDescriptor("selectAll",beanClass,"isSelectAll","setSelectAll");
            prop_selectAll.setDisplayName(resources.getString("AddRemove_selectAll_DisplayName"));
            prop_selectAll.setShortDescription(resources.getString("AddRemove_selectAll_Description"));
            prop_selectAll.setExpert(false);
            prop_selectAll.setHidden(false);
            prop_selectAll.setPreferred(false);
            attrib = new AttributeDescriptor("selectAll",false,null,true);
            prop_selectAll.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_selectAll.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.APPEARANCE);

            PropertyDescriptor prop_selectedItemsLabel = new PropertyDescriptor("selectedItemsLabel",beanClass,"getSelectedItemsLabel","setSelectedItemsLabel");
            prop_selectedItemsLabel.setDisplayName(resources.getString("AddRemove_selectedItemsLabel_DisplayName"));
            prop_selectedItemsLabel.setShortDescription(resources.getString("AddRemove_selectedItemsLabel_Description"));
            prop_selectedItemsLabel.setPropertyEditorClass(loadClass("com.sun.rave.propertyeditors.StringPropertyEditor"));
            prop_selectedItemsLabel.setExpert(false);
            prop_selectedItemsLabel.setHidden(false);
            prop_selectedItemsLabel.setPreferred(false);
            attrib = new AttributeDescriptor("selectedItemsLabel",false,null,true);
            prop_selectedItemsLabel.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_selectedItemsLabel.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.APPEARANCE);

            PropertyDescriptor prop_sorted = new PropertyDescriptor("sorted",beanClass,"isSorted","setSorted");
            prop_sorted.setDisplayName(resources.getString("AddRemove_sorted_DisplayName"));
            prop_sorted.setShortDescription(resources.getString("AddRemove_sorted_Description"));
            prop_sorted.setExpert(false);
            prop_sorted.setHidden(false);
            prop_sorted.setPreferred(false);
            attrib = new AttributeDescriptor("sorted",false,null,true);
            prop_sorted.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_sorted.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.DATA);

            PropertyDescriptor prop_toolTip = new PropertyDescriptor("toolTip",beanClass,"getToolTip","setToolTip");
            prop_toolTip.setDisplayName(resources.getString("AddRemove_toolTip_DisplayName"));
            prop_toolTip.setShortDescription(resources.getString("AddRemove_toolTip_Description"));
            prop_toolTip.setPropertyEditorClass(loadClass("com.sun.rave.propertyeditors.StringPropertyEditor"));
            prop_toolTip.setExpert(false);
            prop_toolTip.setHidden(false);
            prop_toolTip.setPreferred(false);
            attrib = new AttributeDescriptor("toolTip",false,null,true);
            prop_toolTip.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_toolTip.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.BEHAVIOR);

            PropertyDescriptor prop_vertical = new PropertyDescriptor("vertical",beanClass,"isVertical","setVertical");
            prop_vertical.setDisplayName(resources.getString("AddRemove_vertical_DisplayName"));
            prop_vertical.setShortDescription(resources.getString("AddRemove_vertical_Description"));
            prop_vertical.setExpert(false);
            prop_vertical.setHidden(false);
            prop_vertical.setPreferred(false);
            attrib = new AttributeDescriptor("vertical",false,null,true);
            prop_vertical.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_vertical.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.APPEARANCE);

            PropertyDescriptor prop_labelOnTop = new PropertyDescriptor("labelOnTop",beanClass,"isLabelOnTop","setLabelOnTop");
            prop_labelOnTop.setDisplayName(resources.getString("AddRemove_labelOnTop_DisplayName"));
            prop_labelOnTop.setShortDescription(resources.getString("AddRemove_labelOnTop_Description"));
            prop_labelOnTop.setExpert(false);
            prop_labelOnTop.setHidden(false);
            prop_labelOnTop.setPreferred(false);
            attrib = new AttributeDescriptor("labelOnTop",false,null,true);
            prop_labelOnTop.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_labelOnTop.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.APPEARANCE);

            PropertyDescriptor prop_rows = new PropertyDescriptor("rows",beanClass,"getRows","setRows");
            prop_rows.setDisplayName(resources.getString("AddRemove_rows_DisplayName"));
            prop_rows.setShortDescription(resources.getString("AddRemove_rows_Description"));
            prop_rows.setPropertyEditorClass(loadClass("com.sun.rave.propertyeditors.IntegerPropertyEditor"));
            prop_rows.setExpert(false);
            prop_rows.setHidden(false);
            prop_rows.setPreferred(false);
            attrib = new AttributeDescriptor("rows",false,"12",true);
            prop_rows.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_rows.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.APPEARANCE);
            prop_rows.setValue("com.sun.rave.propertyeditors.MIN_VALUE", "1");

            PropertyDescriptor prop_separators = new PropertyDescriptor("separators",beanClass,"isSeparators","setSeparators");
            prop_separators.setDisplayName(resources.getString("AddRemove_separators_DisplayName"));
            prop_separators.setShortDescription(resources.getString("AddRemove_separators_Description"));
            prop_separators.setExpert(false);
            prop_separators.setHidden(true);
            prop_separators.setPreferred(false);
            prop_separators.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.APPEARANCE);

            PropertyDescriptor prop_visible = new PropertyDescriptor("visible",beanClass,"isVisible","setVisible");
            prop_visible.setDisplayName(resources.getString("AddRemove_visible_DisplayName"));
            prop_visible.setShortDescription(resources.getString("AddRemove_visible_Description"));
            prop_visible.setExpert(false);
            prop_visible.setHidden(false);
            prop_visible.setPreferred(false);
            attrib = new AttributeDescriptor("visible",false,"true",true);
            prop_visible.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_visible.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.BEHAVIOR);

            PropertyDescriptor prop_disabled = new PropertyDescriptor("disabled",beanClass,"isDisabled","setDisabled");
            prop_disabled.setDisplayName(resources.getString("AddRemove_disabled_DisplayName"));
            prop_disabled.setShortDescription(resources.getString("AddRemove_disabled_Description"));
            prop_disabled.setExpert(false);
            prop_disabled.setHidden(false);
            prop_disabled.setPreferred(false);
            attrib = new AttributeDescriptor("disabled",false,null,true);
            prop_disabled.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_disabled.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.BEHAVIOR);

            PropertyDescriptor prop_items = new PropertyDescriptor("items",beanClass,"getItems","setItems");
            prop_items.setDisplayName(resources.getString("AddRemove_items_DisplayName"));
            prop_items.setShortDescription(resources.getString("AddRemove_items_Description"));
            prop_items.setPropertyEditorClass(loadClass("com.sun.rave.propertyeditors.binding.ValueBindingPropertyEditor"));
            prop_items.setExpert(false);
            prop_items.setHidden(false);
            prop_items.setPreferred(false);
            attrib = new AttributeDescriptor("items",false,null,true);
            prop_items.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_items.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.DATA);
            prop_items.setValue("bindingPanelClassNames", new Class[] {org.netbeans.modules.visualweb.propertyeditors.binding.data.BindOptionsToDataProviderPanel.class, org.netbeans.modules.visualweb.propertyeditors.binding.data.BindValueToObjectPanel.class});

            PropertyDescriptor prop_label = new PropertyDescriptor("label",beanClass,"getLabel","setLabel");
            prop_label.setDisplayName(resources.getString("AddRemove_label_DisplayName"));
            prop_label.setShortDescription(resources.getString("AddRemove_label_Description"));
            prop_label.setPropertyEditorClass(loadClass("com.sun.rave.propertyeditors.StringPropertyEditor"));
            prop_label.setExpert(false);
            prop_label.setHidden(false);
            prop_label.setPreferred(false);
            attrib = new AttributeDescriptor("label",false,null,true);
            prop_label.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_label.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.APPEARANCE);

            PropertyDescriptor prop_labelLevel = new PropertyDescriptor("labelLevel",beanClass,"getLabelLevel","setLabelLevel");
            prop_labelLevel.setDisplayName(resources.getString("AddRemove_labelLevel_DisplayName"));
            prop_labelLevel.setShortDescription(resources.getString("AddRemove_labelLevel_Description"));
            prop_labelLevel.setPropertyEditorClass(loadClass("com.sun.rave.propertyeditors.SelectOneDomainEditor"));
            prop_labelLevel.setExpert(false);
            prop_labelLevel.setHidden(true);
            prop_labelLevel.setPreferred(false);
            prop_labelLevel.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.APPEARANCE);
            prop_labelLevel.setValue("com.sun.rave.propertyeditors.DOMAIN_CLASS", org.netbeans.modules.visualweb.web.ui.dt.component.propertyeditors.LabelLevelsDomain.class);

            PropertyDescriptor prop_onBlur = new PropertyDescriptor("onBlur",beanClass,"getOnBlur","setOnBlur");
            prop_onBlur.setDisplayName(resources.getString("AddRemove_onBlur_DisplayName"));
            prop_onBlur.setShortDescription(resources.getString("AddRemove_onBlur_Description"));
            prop_onBlur.setPropertyEditorClass(loadClass("com.sun.rave.propertyeditors.JavaScriptPropertyEditor"));
            prop_onBlur.setExpert(false);
            prop_onBlur.setHidden(true);
            prop_onBlur.setPreferred(false);
            prop_onBlur.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.JAVASCRIPT);

            PropertyDescriptor prop_onChange = new PropertyDescriptor("onChange",beanClass,"getOnChange","setOnChange");
            prop_onChange.setDisplayName(resources.getString("AddRemove_onChange_DisplayName"));
            prop_onChange.setShortDescription(resources.getString("AddRemove_onChange_Description"));
            prop_onChange.setPropertyEditorClass(loadClass("com.sun.rave.propertyeditors.JavaScriptPropertyEditor"));
            prop_onChange.setExpert(false);
            prop_onChange.setHidden(true);
            prop_onChange.setPreferred(false);
            prop_onChange.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.JAVASCRIPT);

            PropertyDescriptor prop_onClick = new PropertyDescriptor("onClick",beanClass,"getOnClick","setOnClick");
            prop_onClick.setDisplayName(resources.getString("AddRemove_onClick_DisplayName"));
            prop_onClick.setShortDescription(resources.getString("AddRemove_onClick_Description"));
            prop_onClick.setPropertyEditorClass(loadClass("com.sun.rave.propertyeditors.JavaScriptPropertyEditor"));
            prop_onClick.setExpert(false);
            prop_onClick.setHidden(true);
            prop_onClick.setPreferred(false);
            prop_onClick.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.JAVASCRIPT);

            PropertyDescriptor prop_onDblClick = new PropertyDescriptor("onDblClick",beanClass,"getOnDblClick","setOnDblClick");
            prop_onDblClick.setDisplayName(resources.getString("AddRemove_onDblClick_DisplayName"));
            prop_onDblClick.setShortDescription(resources.getString("AddRemove_onDblClick_Description"));
            prop_onDblClick.setPropertyEditorClass(loadClass("com.sun.rave.propertyeditors.JavaScriptPropertyEditor"));
            prop_onDblClick.setExpert(false);
            prop_onDblClick.setHidden(true);
            prop_onDblClick.setPreferred(false);
            prop_onDblClick.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.JAVASCRIPT);

            PropertyDescriptor prop_onFocus = new PropertyDescriptor("onFocus",beanClass,"getOnFocus","setOnFocus");
            prop_onFocus.setDisplayName(resources.getString("AddRemove_onFocus_DisplayName"));
            prop_onFocus.setShortDescription(resources.getString("AddRemove_onFocus_Description"));
            prop_onFocus.setPropertyEditorClass(loadClass("com.sun.rave.propertyeditors.JavaScriptPropertyEditor"));
            prop_onFocus.setExpert(false);
            prop_onFocus.setHidden(true);
            prop_onFocus.setPreferred(false);
            prop_onFocus.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.JAVASCRIPT);

            PropertyDescriptor prop_onKeyDown = new PropertyDescriptor("onKeyDown",beanClass,"getOnKeyDown","setOnKeyDown");
            prop_onKeyDown.setDisplayName(resources.getString("AddRemove_onKeyDown_DisplayName"));
            prop_onKeyDown.setShortDescription(resources.getString("AddRemove_onKeyDown_Description"));
            prop_onKeyDown.setPropertyEditorClass(loadClass("com.sun.rave.propertyeditors.JavaScriptPropertyEditor"));
            prop_onKeyDown.setExpert(false);
            prop_onKeyDown.setHidden(true);
            prop_onKeyDown.setPreferred(false);
            prop_onKeyDown.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.JAVASCRIPT);

            PropertyDescriptor prop_onKeyPress = new PropertyDescriptor("onKeyPress",beanClass,"getOnKeyPress","setOnKeyPress");
            prop_onKeyPress.setDisplayName(resources.getString("AddRemove_onKeyPress_DisplayName"));
            prop_onKeyPress.setShortDescription(resources.getString("AddRemove_onKeyPress_Description"));
            prop_onKeyPress.setPropertyEditorClass(loadClass("com.sun.rave.propertyeditors.JavaScriptPropertyEditor"));
            prop_onKeyPress.setExpert(false);
            prop_onKeyPress.setHidden(true);
            prop_onKeyPress.setPreferred(false);
            prop_onKeyPress.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.JAVASCRIPT);

            PropertyDescriptor prop_onKeyUp = new PropertyDescriptor("onKeyUp",beanClass,"getOnKeyUp","setOnKeyUp");
            prop_onKeyUp.setDisplayName(resources.getString("AddRemove_onKeyUp_DisplayName"));
            prop_onKeyUp.setShortDescription(resources.getString("AddRemove_onKeyUp_Description"));
            prop_onKeyUp.setPropertyEditorClass(loadClass("com.sun.rave.propertyeditors.JavaScriptPropertyEditor"));
            prop_onKeyUp.setExpert(false);
            prop_onKeyUp.setHidden(true);
            prop_onKeyUp.setPreferred(false);
            prop_onKeyUp.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.JAVASCRIPT);

            PropertyDescriptor prop_onMouseDown = new PropertyDescriptor("onMouseDown",beanClass,"getOnMouseDown","setOnMouseDown");
            prop_onMouseDown.setDisplayName(resources.getString("AddRemove_onMouseDown_DisplayName"));
            prop_onMouseDown.setShortDescription(resources.getString("AddRemove_onMouseDown_Description"));
            prop_onMouseDown.setPropertyEditorClass(loadClass("com.sun.rave.propertyeditors.JavaScriptPropertyEditor"));
            prop_onMouseDown.setExpert(false);
            prop_onMouseDown.setHidden(true);
            prop_onMouseDown.setPreferred(false);
            prop_onMouseDown.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.JAVASCRIPT);

            PropertyDescriptor prop_onMouseMove = new PropertyDescriptor("onMouseMove",beanClass,"getOnMouseMove","setOnMouseMove");
            prop_onMouseMove.setDisplayName(resources.getString("AddRemove_onMouseMove_DisplayName"));
            prop_onMouseMove.setShortDescription(resources.getString("AddRemove_onMouseMove_Description"));
            prop_onMouseMove.setPropertyEditorClass(loadClass("com.sun.rave.propertyeditors.JavaScriptPropertyEditor"));
            prop_onMouseMove.setExpert(false);
            prop_onMouseMove.setHidden(true);
            prop_onMouseMove.setPreferred(false);
            prop_onMouseMove.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.JAVASCRIPT);

            PropertyDescriptor prop_onMouseOut = new PropertyDescriptor("onMouseOut",beanClass,"getOnMouseOut","setOnMouseOut");
            prop_onMouseOut.setDisplayName(resources.getString("AddRemove_onMouseOut_DisplayName"));
            prop_onMouseOut.setShortDescription(resources.getString("AddRemove_onMouseOut_Description"));
            prop_onMouseOut.setPropertyEditorClass(loadClass("com.sun.rave.propertyeditors.JavaScriptPropertyEditor"));
            prop_onMouseOut.setExpert(false);
            prop_onMouseOut.setHidden(true);
            prop_onMouseOut.setPreferred(false);
            prop_onMouseOut.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.JAVASCRIPT);

            PropertyDescriptor prop_onMouseOver = new PropertyDescriptor("onMouseOver",beanClass,"getOnMouseOver","setOnMouseOver");
            prop_onMouseOver.setDisplayName(resources.getString("AddRemove_onMouseOver_DisplayName"));
            prop_onMouseOver.setShortDescription(resources.getString("AddRemove_onMouseOver_Description"));
            prop_onMouseOver.setPropertyEditorClass(loadClass("com.sun.rave.propertyeditors.JavaScriptPropertyEditor"));
            prop_onMouseOver.setExpert(false);
            prop_onMouseOver.setHidden(true);
            prop_onMouseOver.setPreferred(false);
            prop_onMouseOver.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.JAVASCRIPT);

            PropertyDescriptor prop_onMouseUp = new PropertyDescriptor("onMouseUp",beanClass,"getOnMouseUp","setOnMouseUp");
            prop_onMouseUp.setDisplayName(resources.getString("AddRemove_onMouseUp_DisplayName"));
            prop_onMouseUp.setShortDescription(resources.getString("AddRemove_onMouseUp_Description"));
            prop_onMouseUp.setPropertyEditorClass(loadClass("com.sun.rave.propertyeditors.JavaScriptPropertyEditor"));
            prop_onMouseUp.setExpert(false);
            prop_onMouseUp.setHidden(true);
            prop_onMouseUp.setPreferred(false);
            prop_onMouseUp.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.JAVASCRIPT);

            PropertyDescriptor prop_onSelect = new PropertyDescriptor("onSelect",beanClass,"getOnSelect","setOnSelect");
            prop_onSelect.setDisplayName(resources.getString("AddRemove_onSelect_DisplayName"));
            prop_onSelect.setShortDescription(resources.getString("AddRemove_onSelect_Description"));
            prop_onSelect.setPropertyEditorClass(loadClass("com.sun.rave.propertyeditors.JavaScriptPropertyEditor"));
            prop_onSelect.setExpert(false);
            prop_onSelect.setHidden(true);
            prop_onSelect.setPreferred(false);
            prop_onSelect.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.JAVASCRIPT);

            PropertyDescriptor prop_readOnly = new PropertyDescriptor("readOnly",beanClass,"isReadOnly","setReadOnly");
            prop_readOnly.setDisplayName(resources.getString("AddRemove_readOnly_DisplayName"));
            prop_readOnly.setShortDescription(resources.getString("AddRemove_readOnly_Description"));
            prop_readOnly.setExpert(false);
            prop_readOnly.setHidden(false);
            prop_readOnly.setPreferred(false);
            attrib = new AttributeDescriptor("readOnly",false,null,true);
            prop_readOnly.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_readOnly.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.BEHAVIOR);

            PropertyDescriptor prop_selected = new PropertyDescriptor("selected",beanClass,"getSelected","setSelected");
            prop_selected.setDisplayName(resources.getString("AddRemove_selected_DisplayName"));
            prop_selected.setShortDescription(resources.getString("AddRemove_selected_Description"));
            prop_selected.setPropertyEditorClass(loadClass("com.sun.rave.propertyeditors.binding.ValueBindingPropertyEditor"));
            prop_selected.setExpert(false);
            prop_selected.setHidden(false);
            prop_selected.setPreferred(false);
            attrib = new AttributeDescriptor("selected",false,null,true);
            prop_selected.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_selected.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.DATA);
            prop_selected.setValue("bindingPanelClassNames", new Class[] {org.netbeans.modules.visualweb.propertyeditors.binding.data.BindValueToDataProviderPanel.class, org.netbeans.modules.visualweb.propertyeditors.binding.data.BindValueToObjectPanel.class});

            PropertyDescriptor prop_style = new PropertyDescriptor("style",beanClass,"getStyle","setStyle");
            prop_style.setDisplayName(resources.getString("AddRemove_style_DisplayName"));
            prop_style.setShortDescription(resources.getString("AddRemove_style_Description"));
            prop_style.setPropertyEditorClass(loadClass("com.sun.rave.propertyeditors.css.CssStylePropertyEditor"));
            prop_style.setExpert(false);
            prop_style.setHidden(false);
            prop_style.setPreferred(false);
            attrib = new AttributeDescriptor("style",false,null,true);
            prop_style.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_style.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.APPEARANCE);

            PropertyDescriptor prop_styleClass = new PropertyDescriptor("styleClass",beanClass,"getStyleClass","setStyleClass");
            prop_styleClass.setDisplayName(resources.getString("AddRemove_styleClass_DisplayName"));
            prop_styleClass.setShortDescription(resources.getString("AddRemove_styleClass_Description"));
            prop_styleClass.setPropertyEditorClass(loadClass("com.sun.rave.propertyeditors.StyleClassPropertyEditor"));
            prop_styleClass.setExpert(false);
            prop_styleClass.setHidden(false);
            prop_styleClass.setPreferred(false);
            attrib = new AttributeDescriptor("styleClass",false,null,true);
            prop_styleClass.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_styleClass.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.APPEARANCE);

            PropertyDescriptor prop_tabIndex = new PropertyDescriptor("tabIndex",beanClass,"getTabIndex","setTabIndex");
            prop_tabIndex.setDisplayName(resources.getString("AddRemove_tabIndex_DisplayName"));
            prop_tabIndex.setShortDescription(resources.getString("AddRemove_tabIndex_Description"));
            prop_tabIndex.setPropertyEditorClass(loadClass("com.sun.rave.propertyeditors.IntegerPropertyEditor"));
            prop_tabIndex.setExpert(false);
            prop_tabIndex.setHidden(false);
            prop_tabIndex.setPreferred(false);
            attrib = new AttributeDescriptor("tabIndex",false,null,true);
            prop_tabIndex.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_tabIndex.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.ACCESSIBILITY);
            prop_tabIndex.setValue("com.sun.rave.propertyeditors.MIN_VALUE", "1");

            PropertyDescriptor prop_attributes = new PropertyDescriptor("attributes",beanClass,"getAttributes",null);
            prop_attributes.setDisplayName(resources.getString("AddRemove_attributes_DisplayName"));
            prop_attributes.setShortDescription(resources.getString("AddRemove_attributes_Description"));
            prop_attributes.setExpert(false);
            prop_attributes.setHidden(true);
            prop_attributes.setPreferred(false);
            prop_attributes.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.INTERNAL);

            PropertyDescriptor prop_childCount = new PropertyDescriptor("childCount",beanClass,"getChildCount",null);
            prop_childCount.setDisplayName(resources.getString("AddRemove_childCount_DisplayName"));
            prop_childCount.setShortDescription(resources.getString("AddRemove_childCount_Description"));
            prop_childCount.setExpert(false);
            prop_childCount.setHidden(true);
            prop_childCount.setPreferred(false);
            prop_childCount.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.INTERNAL);

            PropertyDescriptor prop_children = new PropertyDescriptor("children",beanClass,"getChildren",null);
            prop_children.setDisplayName(resources.getString("AddRemove_children_DisplayName"));
            prop_children.setShortDescription(resources.getString("AddRemove_children_Description"));
            prop_children.setExpert(false);
            prop_children.setHidden(true);
            prop_children.setPreferred(false);
            prop_children.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.INTERNAL);

            PropertyDescriptor prop_converter = new PropertyDescriptor("converter",beanClass,"getConverter","setConverter");
            prop_converter.setDisplayName(resources.getString("AddRemove_converter_DisplayName"));
            prop_converter.setShortDescription(resources.getString("AddRemove_converter_Description"));
            prop_converter.setPropertyEditorClass(loadClass("org.netbeans.modules.visualweb.web.ui.dt.component.propertyeditors.JSF1_1ConverterPropertyEditor"));
            prop_converter.setExpert(false);
            prop_converter.setHidden(false);
            prop_converter.setPreferred(false);
            attrib = new AttributeDescriptor("converter",false,null,true);
            prop_converter.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_converter.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.DATA);

            PropertyDescriptor prop_facets = new PropertyDescriptor("facets",beanClass,"getFacets",null);
            prop_facets.setDisplayName(resources.getString("AddRemove_facets_DisplayName"));
            prop_facets.setShortDescription(resources.getString("AddRemove_facets_Description"));
            prop_facets.setExpert(false);
            prop_facets.setHidden(true);
            prop_facets.setPreferred(false);
            prop_facets.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.INTERNAL);

            PropertyDescriptor prop_family = new PropertyDescriptor("family",beanClass,"getFamily",null);
            prop_family.setDisplayName(resources.getString("AddRemove_family_DisplayName"));
            prop_family.setShortDescription(resources.getString("AddRemove_family_Description"));
            prop_family.setExpert(false);
            prop_family.setHidden(true);
            prop_family.setPreferred(false);
            prop_family.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.INTERNAL);

            PropertyDescriptor prop_id = new PropertyDescriptor("id",beanClass,"getId","setId");
            prop_id.setDisplayName(resources.getString("AddRemove_id_DisplayName"));
            prop_id.setShortDescription(resources.getString("AddRemove_id_Description"));
            prop_id.setExpert(false);
            prop_id.setHidden(true);
            prop_id.setPreferred(false);
            attrib = new AttributeDescriptor("id",false,null,true);
            prop_id.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_id.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.GENERAL);

            PropertyDescriptor prop_immediate = new PropertyDescriptor("immediate",beanClass,"isImmediate","setImmediate");
            prop_immediate.setDisplayName(resources.getString("AddRemove_immediate_DisplayName"));
            prop_immediate.setShortDescription(resources.getString("AddRemove_immediate_Description"));
            prop_immediate.setExpert(false);
            prop_immediate.setHidden(false);
            prop_immediate.setPreferred(false);
            attrib = new AttributeDescriptor("immediate",false,null,true);
            prop_immediate.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_immediate.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.ADVANCED);

            PropertyDescriptor prop_localValue = new PropertyDescriptor("localValue",beanClass,"getLocalValue",null);
            prop_localValue.setDisplayName(resources.getString("AddRemove_localValue_DisplayName"));
            prop_localValue.setShortDescription(resources.getString("AddRemove_localValue_Description"));
            prop_localValue.setExpert(false);
            prop_localValue.setHidden(true);
            prop_localValue.setPreferred(false);
            prop_localValue.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.INTERNAL);

            PropertyDescriptor prop_localValueSet = new PropertyDescriptor("localValueSet",beanClass,"isLocalValueSet","setLocalValueSet");
            prop_localValueSet.setDisplayName(resources.getString("AddRemove_localValueSet_DisplayName"));
            prop_localValueSet.setShortDescription(resources.getString("AddRemove_localValueSet_Description"));
            prop_localValueSet.setExpert(false);
            prop_localValueSet.setHidden(true);
            prop_localValueSet.setPreferred(false);
            prop_localValueSet.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.INTERNAL);

            PropertyDescriptor prop_parent = new PropertyDescriptor("parent",beanClass,"getParent",null);
            prop_parent.setDisplayName(resources.getString("AddRemove_parent_DisplayName"));
            prop_parent.setShortDescription(resources.getString("AddRemove_parent_Description"));
            prop_parent.setExpert(false);
            prop_parent.setHidden(true);
            prop_parent.setPreferred(false);
            prop_parent.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.INTERNAL);

            PropertyDescriptor prop_rendered = new PropertyDescriptor("rendered",beanClass,"isRendered","setRendered");
            prop_rendered.setDisplayName(resources.getString("AddRemove_rendered_DisplayName"));
            prop_rendered.setShortDescription(resources.getString("AddRemove_rendered_Description"));
            prop_rendered.setExpert(false);
            prop_rendered.setHidden(false);
            prop_rendered.setPreferred(false);
            attrib = new AttributeDescriptor("rendered",false,null,true);
            prop_rendered.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_rendered.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.ADVANCED);

            PropertyDescriptor prop_rendererType = new PropertyDescriptor("rendererType",beanClass,"getRendererType","setRendererType");
            prop_rendererType.setDisplayName(resources.getString("AddRemove_rendererType_DisplayName"));
            prop_rendererType.setShortDescription(resources.getString("AddRemove_rendererType_Description"));
            prop_rendererType.setExpert(false);
            prop_rendererType.setHidden(true);
            prop_rendererType.setPreferred(false);
            prop_rendererType.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.INTERNAL);

            PropertyDescriptor prop_rendersChildren = new PropertyDescriptor("rendersChildren",beanClass,"getRendersChildren",null);
            prop_rendersChildren.setDisplayName(resources.getString("AddRemove_rendersChildren_DisplayName"));
            prop_rendersChildren.setShortDescription(resources.getString("AddRemove_rendersChildren_Description"));
            prop_rendersChildren.setExpert(false);
            prop_rendersChildren.setHidden(true);
            prop_rendersChildren.setPreferred(false);
            prop_rendersChildren.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.INTERNAL);

            PropertyDescriptor prop_required = new PropertyDescriptor("required",beanClass,"isRequired","setRequired");
            prop_required.setDisplayName(resources.getString("AddRemove_required_DisplayName"));
            prop_required.setShortDescription(resources.getString("AddRemove_required_Description"));
            prop_required.setExpert(false);
            prop_required.setHidden(false);
            prop_required.setPreferred(false);
            attrib = new AttributeDescriptor("required",false,null,true);
            prop_required.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_required.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.DATA);

            PropertyDescriptor prop_submittedValue = new PropertyDescriptor("submittedValue",beanClass,"getSubmittedValue","setSubmittedValue");
            prop_submittedValue.setDisplayName(resources.getString("AddRemove_submittedValue_DisplayName"));
            prop_submittedValue.setShortDescription(resources.getString("AddRemove_submittedValue_Description"));
            prop_submittedValue.setExpert(false);
            prop_submittedValue.setHidden(true);
            prop_submittedValue.setPreferred(false);
            prop_submittedValue.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.INTERNAL);

            PropertyDescriptor prop_validator = new PropertyDescriptor("validator",beanClass,"getValidator","setValidator");
            prop_validator.setDisplayName(resources.getString("AddRemove_validator_DisplayName"));
            prop_validator.setShortDescription(resources.getString("AddRemove_validator_Description"));
            prop_validator.setPropertyEditorClass(loadClass("org.netbeans.modules.visualweb.web.ui.dt.component.propertyeditors.JSF1_1ValidatorPropertyEditor"));
            prop_validator.setExpert(false);
            prop_validator.setHidden(false);
            prop_validator.setPreferred(false);
            attrib = new AttributeDescriptor("validator",false,null,true);
            prop_validator.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_validator.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.DATA);

            PropertyDescriptor prop_value = new PropertyDescriptor("value",beanClass,"getValue","setValue");
            prop_value.setDisplayName(resources.getString("AddRemove_value_DisplayName"));
            prop_value.setShortDescription(resources.getString("AddRemove_value_Description"));
            prop_value.setExpert(false);
            prop_value.setHidden(true);
            prop_value.setPreferred(false);
            prop_value.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.DATA);

            PropertyDescriptor prop_valueChangeListener = new PropertyDescriptor("valueChangeListener",beanClass,"getValueChangeListener","setValueChangeListener");
            prop_valueChangeListener.setDisplayName(resources.getString("AddRemove_valueChangeListener_DisplayName"));
            prop_valueChangeListener.setShortDescription(resources.getString("AddRemove_valueChangeListener_Description"));
            prop_valueChangeListener.setPropertyEditorClass(loadClass("com.sun.rave.propertyeditors.MethodBindingPropertyEditor"));
            prop_valueChangeListener.setExpert(false);
            prop_valueChangeListener.setHidden(false);
            prop_valueChangeListener.setPreferred(false);
            attrib = new AttributeDescriptor("valueChangeListener",false,null,true);
            prop_valueChangeListener.setValue(Constants.PropertyDescriptor.ATTRIBUTE_DESCRIPTOR,attrib);
            prop_valueChangeListener.setValue(Constants.PropertyDescriptor.CATEGORY,com.sun.rave.designtime.base.CategoryDescriptors.ADVANCED);

            propDescriptors = new PropertyDescriptor[] {
                prop_attributes,
                prop_availableItemsLabel,
                prop_childCount,
                prop_children,
                prop_converter,
                prop_disabled,
                prop_duplicateSelections,
                prop_facets,
                prop_family,
                prop_id,
                prop_immediate,
                prop_items,
                prop_label,
                prop_labelLevel,
                prop_labelOnTop,
                prop_localValue,
                prop_localValueSet,
                prop_moveButtons,
                prop_onBlur,
                prop_onChange,
                prop_onClick,
                prop_onDblClick,
                prop_onFocus,
                prop_onKeyDown,
                prop_onKeyPress,
                prop_onKeyUp,
                prop_onMouseDown,
                prop_onMouseMove,
                prop_onMouseOut,
                prop_onMouseOver,
                prop_onMouseUp,
                prop_onSelect,
                prop_parent,
                prop_readOnly,
                prop_rendered,
                prop_rendererType,
                prop_rendersChildren,
                prop_required,
                prop_rows,
                prop_selectAll,
                prop_selected,
                prop_selectedItemsLabel,
                prop_separators,
                prop_sorted,
                prop_style,
                prop_styleClass,
                prop_submittedValue,
                prop_tabIndex,
                prop_toolTip,
                prop_validator,
                prop_value,
                prop_valueChangeListener,
                prop_vertical,
                prop_visible,
            };
            return propDescriptors;

        } catch (IntrospectionException e) {
            e.printStackTrace();
            return null;
        }

    }

}
//GEN-END:BeanInfo
