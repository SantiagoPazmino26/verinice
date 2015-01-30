/*******************************************************************************
 * Copyright (c) 2011 Daniel Murygin.
 *
 * This program is free software: you can redistribute it and/or 
 * modify it under the terms of the GNU Lesser General Public License 
 * as published by the Free Software Foundation, either version 3 
 * of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,    
 * but WITHOUT ANY WARRANTY; without even the implied warranty 
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. 
 * If not, see <http://www.gnu.org/licenses/>.
 * 
 * Contributors:
 *     Benjamin Weißenfels <bw[at]sernet[dot]de> - initial API and implementation
 ******************************************************************************/
package sernet.springclient;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.springframework.remoting.httpinvoker.HttpInvokerClientConfiguration;

import waffle.windows.auth.IWindowsCredentialsHandle;
import waffle.windows.auth.impl.WindowsAccountImpl;
import waffle.windows.auth.impl.WindowsCredentialsHandleImpl;
import waffle.windows.auth.impl.WindowsSecurityContextImpl;

import com.google.common.io.BaseEncoding;
import com.sun.jna.platform.win32.Sspi;
import com.sun.jna.platform.win32.Sspi.SecBufferDesc;

public class KerberosExecuter extends AbstractExecuter {

    private static final String HEADER_NAME_AUTHORIZATION = "Authorization";

    // TODO must be configurable or extracted from the TGT
    private static final String TARGET_NAME = "verinicepro-1.7";

    private static final Logger LOG = Logger.getLogger(KerberosExecuter.class);
    
    private String clientToken;

    final String securityPackage = "NEGOTIATE";

    private IWindowsCredentialsHandle clientCredentials;

    private WindowsSecurityContextImpl clientContext;

    private boolean isClienTokenInit;

    private String testUser = null;

    public KerberosExecuter() {
        super();
    }

    public void init() {
        super.init();
    }

    @Override
    protected void validateResponse(HttpInvokerClientConfiguration config, PostMethod postMethod) throws IOException {

        if (postMethod.getStatusCode() == 200) {
            return;
        }

        if (postMethod.getStatusCode() == 301) {
            return;
        }

        if (postMethod.getStatusCode() == 401) {

            if (isClienTokenInit) {
                updateClientToken(postMethod);
            } else {
                initClientToken();
            }

            LOG.info("client token: " + clientToken);
        } else {
            super.validateResponse(config, postMethod);
        }
    }

    private void updateClientToken(PostMethod postMethod) {

        String negotiate = postMethod.getResponseHeader("WWW-Authenticate").getValue();

        String continueToken = negotiate.substring(securityPackage.length() + 1);
        byte[] continueTokenBytes = BaseEncoding.base64().decode(continueToken);

        if (continueTokenBytes.length > 0) {

            SecBufferDesc continueTokenBuffer = new SecBufferDesc(Sspi.SECBUFFER_TOKEN, continueTokenBytes);

            // FIXME
            clientContext.initialize(clientContext.getHandle(), continueTokenBuffer, TARGET_NAME);

            clientToken = securityPackage + " " + BaseEncoding.base64().encode(clientContext.getToken());

        }
    }

    /**
     * Read the initial token.
     */
    private void initClientToken() {

        clientCredentials = WindowsCredentialsHandleImpl.getCurrent(securityPackage);
        clientCredentials.initialize();
        clientContext = new WindowsSecurityContextImpl();
        clientContext.setPrincipalName(WindowsAccountImpl.getCurrentUsername());
        clientContext.setCredentialsHandle(clientCredentials.getHandle());
        clientContext.setSecurityPackage(securityPackage);
        clientContext.initialize(clientContext.getHandle(), null, TARGET_NAME);

        clientToken = securityPackage + " " + BaseEncoding.base64().encode(clientContext.getToken());
        isClienTokenInit = true;
    }

    @Override
    protected void executePostMethod(HttpInvokerClientConfiguration config, HttpClient httpClient, PostMethod postMethod) throws IOException {
        postMethod.addRequestHeader(HEADER_NAME_AUTHORIZATION, clientToken);
        super.executePostMethod(config, httpClient, postMethod);
    }
}
