/*
 * Copyright (c) 2014 AsyncHttpClient Project. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at
 *     http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */
package org.asynchttpclient.providers.netty.commons.util;

import java.util.List;

import org.asynchttpclient.uri.Uri;

public final class HttpUtils {

    public static final String HTTP = "http";
    public static final String HTTPS = "https";
    public static final String WEBSOCKET = "ws";
    public static final String WEBSOCKET_SSL = "wss";

    private HttpUtils() {
    }

    public static String getNTLM(List<String> authenticateHeaders) {
        if (authenticateHeaders != null) {
            for (String authenticateHeader: authenticateHeaders) {
                if (authenticateHeader.startsWith("NTLM"))
                    return authenticateHeader;
            }
        }

        return null;
    }

    public static boolean isWebSocket(String scheme) {
        return WEBSOCKET.equals(scheme) || WEBSOCKET_SSL.equalsIgnoreCase(scheme);
    }

    public static boolean isSecure(String scheme) {
        return HTTPS.equals(scheme) || WEBSOCKET_SSL.equals(scheme);
    }

    public static boolean isSecure(Uri uri) {
        return isSecure(uri.getScheme());
    }

    public static boolean useProxyConnect(Uri uri) {
        return isSecure(uri) || isWebSocket(uri.getScheme());
    }
}
