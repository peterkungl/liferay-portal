/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.security.sso.cas.portal.settings.web.portlet.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.settings.web.constants.PortalSettingsPortletKeys;
import com.liferay.portal.util.PortalUtil;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Philip Jones
 */
@Component(
	property = {
		"javax.portlet.name=" + PortalSettingsPortletKeys.PORTAL_SETTINGS,
		"mvc.command.name=/portal_settings/test_cas"
	}
)
public class PortalSettingsTestCASMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		RequestDispatcher requestDispatcher =
			_servletContext.getRequestDispatcher(_JSP_PATH);

		try {
			HttpServletRequest request = PortalUtil.getHttpServletRequest(
				renderRequest);

			HttpServletResponse response = PortalUtil.getHttpServletResponse(
				renderResponse);

			requestDispatcher.include(request, response);
		}
		catch (Exception se) {
			_log.error("Unable to include JSP " + _JSP_PATH, se);

			throw new PortletException(
				"Unable to include JSP " + _JSP_PATH, se);
		}

		return DONT_DISPATCH_PATH;
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.portal.security.sso.cas)",
		unbind = "-"
	)
	protected void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	private static final String _JSP_PATH =
		"/com.liferay.portal.settings.web/test_cas.jsp";

	private static final Log _log = LogFactoryUtil.getLog(
		PortalSettingsTestCASMVCRenderCommand.class);

	private volatile ServletContext _servletContext;

}