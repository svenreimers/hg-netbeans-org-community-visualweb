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
/**
 *	This generated bean class SupportsType
 *	matches the schema element 'supportsType'.
 *  The root bean class is PortletApp
 *
 *	===============================================================
 *	
 *				Supports indicates the portlet modes a
 *				portlet supports for a specific content type. All portlets must
 *				support the view mode.
 *				Used in: portlet
 *				
 *	===============================================================
 *	Generated on Tue Apr 26 19:08:25 MDT 2005
 * @Generated
 */

package org.netbeans.modules.visualweb.api.portlet.dd;

public class SupportsType implements org.netbeans.modules.visualweb.api.portlet.dd.SupportsTypeInterface, org.netbeans.modules.visualweb.api.portlet.dd.CommonBean {
	public static final String ID = "Id";	// NOI18N
	public static final String MIME_TYPE = "MimeType";	// NOI18N
	public static final String PORTLET_MODE = "PortletMode";	// NOI18N

	private java.lang.String _Id;
	private java.lang.String _MimeType;
	private java.util.List _PortletMode = new java.util.ArrayList();	// List<java.lang.String>

	/**
	 * Normal starting point constructor.
	 */
	public SupportsType() {
		_MimeType = "";
	}

	/**
	 * Required parameters constructor
	 */
	public SupportsType(java.lang.String mimeType) {
		_MimeType = mimeType;
	}

	/**
	 * Deep copy
	 */
	public SupportsType(org.netbeans.modules.visualweb.api.portlet.dd.SupportsType source) {
		this(source, false);
	}

	/**
	 * Deep copy
	 * @param justData just copy the XML relevant data
	 */
	public SupportsType(org.netbeans.modules.visualweb.api.portlet.dd.SupportsType source, boolean justData) {
		_Id = source._Id;
		_MimeType = source._MimeType;
		for (java.util.Iterator it = source._PortletMode.iterator();
			it.hasNext(); ) {
			java.lang.String srcElement = (java.lang.String)it.next();
			_PortletMode.add(srcElement);
		}
	}

	// This attribute is optional
	public void setId(java.lang.String value) {
		_Id = value;
	}

	public java.lang.String getId() {
		return _Id;
	}

	// This attribute is mandatory
	public void setMimeType(java.lang.String value) {
		_MimeType = value;
	}

	public java.lang.String getMimeType() {
		return _MimeType;
	}

	// This attribute is an array, possibly empty
	public void setPortletMode(java.lang.String[] value) {
		if (value == null)
			value = new java.lang.String[0];
		_PortletMode.clear();
		((java.util.ArrayList) _PortletMode).ensureCapacity(value.length);
		for (int i = 0; i < value.length; ++i) {
			_PortletMode.add(value[i]);
		}
	}

	public void setPortletMode(int index, java.lang.String value) {
		_PortletMode.set(index, value);
	}

	public java.lang.String[] getPortletMode() {
		java.lang.String[] arr = new java.lang.String[_PortletMode.size()];
		return (java.lang.String[]) _PortletMode.toArray(arr);
	}

	public java.util.List fetchPortletModeList() {
		return _PortletMode;
	}

	public java.lang.String getPortletMode(int index) {
		return (java.lang.String)_PortletMode.get(index);
	}

	// Return the number of portletMode
	public int sizePortletMode() {
		return _PortletMode.size();
	}

	public int addPortletMode(java.lang.String value) {
		_PortletMode.add(value);
		int positionOfNewItem = _PortletMode.size()-1;
		return positionOfNewItem;
	}

	/**
	 * Search from the end looking for @param value, and then remove it.
	 */
	public int removePortletMode(java.lang.String value) {
		int pos = _PortletMode.indexOf(value);
		if (pos >= 0) {
			_PortletMode.remove(pos);
		}
		return pos;
	}

	public void writeNode(java.io.Writer out) throws java.io.IOException {
		String myName;
		myName = "supportsType";
		writeNode(out, myName, "");	// NOI18N
	}

	public void writeNode(java.io.Writer out, String nodeName, String indent) throws java.io.IOException {
		writeNode(out, nodeName, null, indent, new java.util.HashMap());
	}

	/**
	 * It's not recommended to call this method directly.
	 */
	public void writeNode(java.io.Writer out, String nodeName, String namespace, String indent, java.util.Map namespaceMap) throws java.io.IOException {
		out.write(indent);
		out.write("<");
		if (namespace != null) {
			out.write((String)namespaceMap.get(namespace));
			out.write(":");
		}
		out.write(nodeName);
		// id is an attribute with namespace http://java.sun.com/xml/ns/portlet/portlet-app_1_0.xsd
		if (_Id != null) {
			out.write(" id='");
			org.netbeans.modules.visualweb.api.portlet.dd.PortletApp.writeXML(out, _Id, true);
			out.write("'");	// NOI18N
		}
		out.write(">\n");
		String nextIndent = indent + "	";
		if (_MimeType != null) {
			out.write(nextIndent);
			out.write("<mime-type");	// NOI18N
			out.write(">");	// NOI18N
			org.netbeans.modules.visualweb.api.portlet.dd.PortletApp.writeXML(out, _MimeType, false);
			out.write("</mime-type>\n");	// NOI18N
		}
		for (java.util.Iterator it = _PortletMode.iterator(); 
			it.hasNext(); ) {
			java.lang.String element = (java.lang.String)it.next();
			if (element != null) {
				out.write(nextIndent);
				out.write("<portlet-mode");	// NOI18N
				out.write(">");	// NOI18N
				org.netbeans.modules.visualweb.api.portlet.dd.PortletApp.writeXML(out, element, false);
				out.write("</portlet-mode>\n");	// NOI18N
			}
		}
		out.write(indent);
		out.write("</");
		if (namespace != null) {
			out.write((String)namespaceMap.get(namespace));
			out.write(":");
		}
		out.write(nodeName);
		out.write(">\n");
	}

	public void readNode(org.w3c.dom.Node node) {
		readNode(node, new java.util.HashMap());
	}

	public void readNode(org.w3c.dom.Node node, java.util.Map namespacePrefixes) {
		if (node.hasAttributes()) {
			org.w3c.dom.NamedNodeMap attrs = node.getAttributes();
			org.w3c.dom.Attr attr;
			java.lang.String attrValue;
			boolean firstNamespaceDef = true;
			for (int attrNum = 0; attrNum < attrs.getLength(); ++attrNum) {
				attr = (org.w3c.dom.Attr) attrs.item(attrNum);
				String attrName = attr.getName();
				if (attrName.startsWith("xmlns:")) {
					if (firstNamespaceDef) {
						firstNamespaceDef = false;
						// Dup prefix map, so as to not write over previous values, and to make it easy to clear out our entries.
						namespacePrefixes = new java.util.HashMap(namespacePrefixes);
					}
					String attrNSPrefix = attrName.substring(6, attrName.length());
					namespacePrefixes.put(attrNSPrefix, attr.getValue());
				}
			}
			attr = (org.w3c.dom.Attr) attrs.getNamedItem("id");
			if (attr != null) {
				attrValue = attr.getValue();
				_Id = attrValue;
			}
		}
		org.w3c.dom.NodeList children = node.getChildNodes();
		for (int i = 0, size = children.getLength(); i < size; ++i) {
			org.w3c.dom.Node childNode = children.item(i);
			String childNodeName = (childNode.getLocalName() == null ? childNode.getNodeName().intern() : childNode.getLocalName().intern());
			String childNodeValue = "";
			if (childNode.getFirstChild() != null) {
				childNodeValue = childNode.getFirstChild().getNodeValue();
			}
			if (childNodeName == "mime-type") {
				_MimeType = childNodeValue;
			}
			else if (childNodeName == "portlet-mode") {
				java.lang.String aPortletMode;
				aPortletMode = childNodeValue;
				_PortletMode.add(aPortletMode);
			}
			else {
				// Found extra unrecognized childNode
			}
		}
	}

	public void validate() throws org.netbeans.modules.visualweb.api.portlet.dd.PortletApp.ValidateException {
		boolean restrictionFailure = false;
		boolean restrictionPassed = false;
		// Validating property id
		// Validating property mimeType
		if (getMimeType() == null) {
			throw new org.netbeans.modules.visualweb.api.portlet.dd.PortletApp.ValidateException("getMimeType() == null", org.netbeans.modules.visualweb.api.portlet.dd.PortletApp.ValidateException.FailureType.NULL_VALUE, "mimeType", this);	// NOI18N
		}
		// Validating property portletMode
	}

	public void changePropertyByName(String name, Object value) {
		if (name == null) return;
		name = name.intern();
		if (name == "id")
			setId((java.lang.String)value);
		else if (name == "mimeType")
			setMimeType((java.lang.String)value);
		else if (name == "portletMode")
			addPortletMode((java.lang.String)value);
		else if (name == "portletMode[]")
			setPortletMode((java.lang.String[]) value);
		else
			throw new IllegalArgumentException(name+" is not a valid property name for SupportsType");
	}

	public Object fetchPropertyByName(String name) {
		if (name == "id")
			return getId();
		if (name == "mimeType")
			return getMimeType();
		if (name == "portletMode[]")
			return getPortletMode();
		throw new IllegalArgumentException(name+" is not a valid property name for SupportsType");
	}

	public String nameSelf() {
		return "SupportsType";
	}

	public String nameChild(Object childObj) {
		return nameChild(childObj, false, false);
	}

	/**
	 * @param childObj  The child object to search for
	 * @param returnSchemaName  Whether or not the schema name should be returned or the property name
	 * @return null if not found
	 */
	public String nameChild(Object childObj, boolean returnConstName, boolean returnSchemaName) {
		return nameChild(childObj, returnConstName, returnSchemaName, false);
	}

	/**
	 * @param childObj  The child object to search for
	 * @param returnSchemaName  Whether or not the schema name should be returned or the property name
	 * @return null if not found
	 */
	public String nameChild(Object childObj, boolean returnConstName, boolean returnSchemaName, boolean returnXPathName) {
		if (childObj instanceof java.lang.String) {
			java.lang.String child = (java.lang.String) childObj;
			if (child == _Id) {
				if (returnConstName) {
					return ID;
				} else if (returnSchemaName) {
					return "id";
				} else if (returnXPathName) {
					return "@id";
				} else {
					return "Id";
				}
			}
			if (child == _MimeType) {
				if (returnConstName) {
					return MIME_TYPE;
				} else if (returnSchemaName) {
					return "mime-type";
				} else if (returnXPathName) {
					return "mime-type";
				} else {
					return "MimeType";
				}
			}
			int index = 0;
			for (java.util.Iterator it = _PortletMode.iterator(); 
				it.hasNext(); ) {
				java.lang.String element = (java.lang.String)it.next();
				if (child == element) {
					if (returnConstName) {
						return PORTLET_MODE;
					} else if (returnSchemaName) {
						return "portlet-mode";
					} else if (returnXPathName) {
						return "portlet-mode[position()="+index+"]";
					} else {
						return "PortletMode."+Integer.toHexString(index);
					}
				}
				++index;
			}
		}
		return null;
	}

	/**
	 * Return an array of all of the properties that are beans and are set.
	 */
	public org.netbeans.modules.visualweb.api.portlet.dd.CommonBean[] childBeans(boolean recursive) {
		java.util.List children = new java.util.LinkedList();
		childBeans(recursive, children);
		org.netbeans.modules.visualweb.api.portlet.dd.CommonBean[] result = new org.netbeans.modules.visualweb.api.portlet.dd.CommonBean[children.size()];
		return (org.netbeans.modules.visualweb.api.portlet.dd.CommonBean[]) children.toArray(result);
	}

	/**
	 * Put all child beans into the beans list.
	 */
	public void childBeans(boolean recursive, java.util.List beans) {
	}

	public boolean equals(Object o) {
		return o instanceof org.netbeans.modules.visualweb.api.portlet.dd.SupportsType && equals((org.netbeans.modules.visualweb.api.portlet.dd.SupportsType) o);
	}

	public boolean equals(org.netbeans.modules.visualweb.api.portlet.dd.SupportsType inst) {
		if (inst == this) {
			return true;
		}
		if (inst == null) {
			return false;
		}
		if (!(_Id == null ? inst._Id == null : _Id.equals(inst._Id))) {
			return false;
		}
		if (!(_MimeType == null ? inst._MimeType == null : _MimeType.equals(inst._MimeType))) {
			return false;
		}
		if (sizePortletMode() != inst.sizePortletMode())
			return false;
		// Compare every element.
		for (java.util.Iterator it = _PortletMode.iterator(), it2 = inst._PortletMode.iterator(); 
			it.hasNext() && it2.hasNext(); ) {
			java.lang.String element = (java.lang.String)it.next();
			java.lang.String element2 = (java.lang.String)it2.next();
			if (!(element == null ? element2 == null : element.equals(element2))) {
				return false;
			}
		}
		return true;
	}

	public int hashCode() {
		int result = 17;
		result = 37*result + (_Id == null ? 0 : _Id.hashCode());
		result = 37*result + (_MimeType == null ? 0 : _MimeType.hashCode());
		result = 37*result + (_PortletMode == null ? 0 : _PortletMode.hashCode());
		return result;
	}

	public String toString() {
		java.io.StringWriter sw = new java.io.StringWriter();
		try {
			writeNode(sw);
		} catch (java.io.IOException e) {
			// How can we actually get an IOException on a StringWriter?
			throw new RuntimeException(e);
		}
		return sw.toString();
	}

}

