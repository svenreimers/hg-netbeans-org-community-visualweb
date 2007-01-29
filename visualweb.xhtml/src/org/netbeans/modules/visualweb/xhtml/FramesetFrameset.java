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
package org.netbeans.modules.visualweb.xhtml;
/**
 * <b>FramesetFrameset</b> is generated from xhtml.rng by Relaxer.
 * This class is derived from:
 *
 * <!-- for programmer
 * <element name="frameset">
 *     <ref name="frameset.attlist"/>
 *     <interleave>
 *       <oneOrMore>
 * 	<choice>
 * 	  <ref name="frameset"/>
 * 	  <ref name="frame"/>
 * 	</choice>
 *       </oneOrMore>
 *       <optional>
 * 	<ref name="noframes"/>
 *       </optional>
 *     </interleave>
 *   </element>-->
 * <!-- for javadoc -->
 * <pre> &lt;element name="frameset"&gt;
 *     &lt;ref name="frameset.attlist"/&gt;
 *     &lt;interleave&gt;
 *       &lt;oneOrMore&gt;
 * 	&lt;choice&gt;
 * 	  &lt;ref name="frameset"/&gt;
 * 	  &lt;ref name="frame"/&gt;
 * 	&lt;/choice&gt;
 *       &lt;/oneOrMore&gt;
 *       &lt;optional&gt;
 * 	&lt;ref name="noframes"/&gt;
 *       &lt;/optional&gt;
 *     &lt;/interleave&gt;
 *   &lt;/element&gt;</pre>
 *
 * @version xhtml.rng (Tue Apr 20 01:31:09 PDT 2004)
 * @author  Relaxer 1.1b (http://www.relaxer.org)
 */
public class FramesetFrameset {
    private String id_;
    private String classValue_;
    private String title_;
    private String style_;
    private String cols_;
    private String rows_;

    /**
     * Creates a <code>FramesetFrameset</code>.
     *
     */
    public FramesetFrameset() {
    }
    /**
     * Gets the String property <b>id</b>.
     *
     * @return String
     */
    public String getId() {
        return (id_);
    }
    /**
     * Sets the String property <b>id</b>.
     *
     * @param id
     */
    public void setId(String id) {
        this.id_ = id;
    }
    /**
     * Gets the String property <b>classValue</b>.
     *
     * @return String
     */
    public String getClassValue() {
        return classValue_;
    }
    /**
     * Sets the String property <b>classValue</b>.
     *
     * @param classValue
     */
    public void setClassValue(String classValue) {
        this.classValue_ = classValue;
    }
    /**
     * Gets the String property <b>title</b>.
     *
     * @return String
     */
    public String getTitle() {
        return (title_);
    }
    /**
     * Sets the String property <b>title</b>.
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title_ = title;
    }
    /**
     * Gets the String property <b>style</b>.
     *
     * @return String
     */
    public String getStyle() {
        return (style_);
    }
    /**
     * Sets the String property <b>style</b>.
     *
     * @param style
     */
    public void setStyle(String style) {
        this.style_ = style;
    }
    /**
     * Gets the String property <b>cols</b>.
     *
     * @return String
     */
    public String getCols() {
        return (cols_);
    }
    /**
     * Sets the String property <b>cols</b>.
     *
     * @param cols
     */
    public void setCols(String cols) {
        this.cols_ = cols;
    }
    /**
     * Gets the String property <b>rows</b>.
     *
     * @return String
     */
    public String getRows() {
        return (rows_);
    }
    /**
     * Sets the String property <b>rows</b>.
     *
     * @param rows
     */
    public void setRows(String rows) {
        this.rows_ = rows;
    }
}
