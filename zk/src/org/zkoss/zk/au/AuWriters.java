/* AuWriters.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Tue Dec  4 11:20:33     2007, Created by tomyeh
}}IS_NOTE

Copyright (C) 2007 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 3.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.zk.au;

import java.io.Writer;
import java.io.IOException;

import org.zkoss.idom.Verifier;

import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.au.http.HttpAuWriter;

/**
 * Utilities to instantiate an implmentation of {@link AuWriter}.
 *
 * @author tomyeh
 * @since 3.0.1
 */
public class AuWriters {
	/** The implementation class of {@link AuWriter}. */
	private static Class _awCls;

	/** Returns the implementation class of {@link AuWriter} that
	 * will be used to generate the output to the client.
	 *
	 * <p>Default: {@link HttpAuWriter}.
	 */
	public static Class getImplementationClass() {
		return _awCls != null ? _awCls: HttpAuWriter.class;
	}
	/** Sets the implementation class of {@link AuWriter} that
	 * will be used to generate the output to the client.
	 */
	public static void setImplementationClass(Class cls) {
		if (cls != null)
			if (cls.equals(HttpAuWriter.class))
				cls = null;
			else if (!AuWriter.class.isAssignableFrom(cls))
				throw new IllegalArgumentException(cls+" must implement "+AuWriter.class.getName());
		_awCls = cls;
	}
	/** Creates an instance of {@link AuWriter}.
	 */
	public static AuWriter newInstance() throws UiException {
		if (_awCls != null) {
			try {
				return (AuWriter)_awCls.newInstance();
			} catch (Exception ex) {
				throw UiException.Aide.wrap(ex);
			}
		}
		return new HttpAuWriter();
	}

	/** The content type of the output.
	 * @since 3.5.0
	 */
	public static final String CONTENT_TYPE = "text/plain;charset=UTF-8";
}
