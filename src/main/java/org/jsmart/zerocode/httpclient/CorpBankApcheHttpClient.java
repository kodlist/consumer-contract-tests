package org.jsmart.zerocode.httpclient;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsmart.zerocode.core.httpclient.BasicHttpClient;
import org.jsmart.zerocode.core.utils.HelperJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.ws.rs.core.Response;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import static java.lang.String.format;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;
import static org.jsmart.zerocode.core.utils.HelperJsonUtils.getContentAsItIsJson;

public class CorpBankApcheHttpClient implements BasicHttpClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(CorpBankApcheHttpClient.class);

    @Inject
    @Named("restful.basic.auth.username")
    private String authUser;

    @Inject
    @Named("restful.basic.auth.password")
    private String authPassword;

    private Object COOKIE_JSESSIONID_VALUE;

    @Override
    public Response execute(String httpUrl, String methodName, Map<String, Object> headers, Map<String, Object> queryParams, Object body) throws Exception {

        LOGGER.info("###Used SSL Enabled Http Client for both Http and Https connections");

        /** ---------------------------
         * Get the request body content
         * ----------------------------
         */
        String reqBodyAsString = getContentAsItIsJson(body);

        CloseableHttpClient httpclient = createSslHttpClient();

        /** -----------------------
         * set the query parameters
         * ------------------------
         */
        if (queryParams != null) {
            httpUrl = setQueryParams(httpUrl, queryParams);
        }

        RequestBuilder requestBuilder = RequestBuilder
                .create(methodName)
                .setUri(httpUrl);

        if (reqBodyAsString != null) {
            HttpEntity httpEntity = EntityBuilder.create()
                    .setContentType(APPLICATION_JSON)
                    .setText(reqBodyAsString)
                    .build();
            requestBuilder.setEntity(httpEntity);
        }

        /** --------------
         * set the headers
         * ---------------
         */

        addBasicAuthHeader(requestBuilder);

        if (headers != null) {
            Map headersMap = headers;
            for (Object key : headersMap.keySet()) {
                removeDuplicateHeaders(requestBuilder, (String) key);
                requestBuilder.addHeader((String) key, (String) headersMap.get(key));
                LOGGER.info("Overrode header key:{}, with value:{}", key, headersMap.get(key));
            }
        }

        /** -----------------------------------------------------------------------------------
         * Setting cookies:
         *
         * Highly discouraged to use sessions, but in case of any server dependent upon session,
         * then it's taken care here.
         * ------------------------------------------------------------------------------------
         */
        if (COOKIE_JSESSIONID_VALUE != null) {
            requestBuilder.addHeader("Cookie", (String) COOKIE_JSESSIONID_VALUE);
        }

        /** ----------------------
         * now execute the request
         * -----------------------
         */
        CloseableHttpResponse httpResponse = httpclient.execute(requestBuilder.build());

        Response serverResponse = Response
                .status(httpResponse.getStatusLine().getStatusCode())
                .entity(IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8"))
                .build();


        Header[] allHeaders = httpResponse.getAllHeaders();
        Response.ResponseBuilder responseBuilder = Response.fromResponse(serverResponse);
        for (Header thisHeader : allHeaders) {
            String headerKey = thisHeader.getName();
            responseBuilder = responseBuilder.header(headerKey, thisHeader.getValue());

            /** ---------------
             * Session handled
             * ----------------
             */
            if ("Set-Cookie".equals(headerKey)) {
                COOKIE_JSESSIONID_VALUE = serverResponse.getMetadata().get(headerKey);
            }
        }
        serverResponse = responseBuilder.build();

        return serverResponse;
    }

    private void addBasicAuthHeader(RequestBuilder requestBuilder) {
        String basicAuth = authUser + ":" + authPassword;
        byte[] encodedBasicAuth = Base64.encodeBase64(basicAuth.getBytes(StandardCharsets.ISO_8859_1));
        String basicAuthBase64String = "Basic " + new String(encodedBasicAuth);
        LOGGER.info("Added basic auth header Authorization for, user:{}, password:{} ie base64:{} to header", authUser, authPassword, basicAuthBase64String);
        requestBuilder.addHeader(HttpHeaders.AUTHORIZATION, basicAuthBase64String);
    }

    private void removeDuplicateHeaders(RequestBuilder requestBuilder, String key) {
        if(requestBuilder.getFirstHeader(key) != null) {
            requestBuilder.removeHeaders(key);
        }
    }

    private CloseableHttpClient createSslHttpClient() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(null, (certificate, authType) -> true).build();

        CookieStore cookieStore = new BasicCookieStore();

        return HttpClients.custom()
                .setSSLContext(sslContext)
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .setDefaultCookieStore(cookieStore)
                .build();
    }

    private String setQueryParams(String httpUrl, Map<String, Object> queryParams) {
        String qualifiedQueryParams = createQualifiedQueryParams(queryParams);
        httpUrl = httpUrl + qualifiedQueryParams;
        return httpUrl;
    }

    private String createQualifiedQueryParams(Object queryParams) {
        String qualifiedQueryParam = "?";
        Map queryParamsMap = HelperJsonUtils.readHeadersAsMap(queryParams);
        for (Object key : queryParamsMap.keySet()) {
            if ("?".equals(qualifiedQueryParam)) {
                qualifiedQueryParam = qualifiedQueryParam + format("%s=%s", key, queryParamsMap.get(key));
            } else {
                qualifiedQueryParam = qualifiedQueryParam + format("&%s=%s", key, queryParamsMap.get(key));
            }
        }
        return qualifiedQueryParam;
    }

}

