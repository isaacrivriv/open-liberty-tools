/*******************************************************************************
 * Copyright (c) 2016 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package com.ibm.ws.st.core.internal;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.wst.server.core.IServer;

import com.ibm.ws.st.common.core.ext.internal.servertype.AbstractServerExtension;

/**
 *
 */
public class BaseLibertyServerExtension extends AbstractServerExtension {
    public Map<String, String> getServiceInfo() {
        return new HashMap<String, String>();
    }

    /** {@inheritDoc} */
    @Override
    public String getServerDisplayName(IServer server) {
        WebSphereServer wsServer = WebSphereUtil.getWebSphereServer(server);
        if (wsServer != null) {
            return wsServer.getServerName();
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void serverConfigChanged(IServer server, IProgressMonitor monitor) {
        // By default, only add the localConnector feature as the user may have
        // changed the application update trigger setting on purpose.  Some
        // server extensions may want to override this default behaviour.
        WebSphereServer wsServer = WebSphereUtil.getWebSphereServer(server);
        if (wsServer != null) {
            wsServer.addLocalConnectorFeature(monitor);
        }
    }

}
