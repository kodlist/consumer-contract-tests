package org.jsmart.zerocode.httpclient;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.RequestBuilder;
import org.jsmart.zerocode.core.httpclient.BasicHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.jsmart.zerocode.core.httpclient.utils.HeaderUtils.processFrameworkDefault;
import static org.jsmart.zerocode.core.httpclient.utils.HeaderUtils.removeDuplicateHeaders;

public class CorpBankApcheHttpClient extends BasicHttpClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(CorpBankApcheHttpClient.class);

    @Inject
    @Named("restful.basic.auth.username")
    private String authUser;

    @Inject
    @Named("restful.basic.auth.password")
    private String authPassword;

    @Override
    public RequestBuilder handleHeaders(Map<String, Object> headers, RequestBuilder requestBuilder) {

        // -=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        // Here you added your project specific headers to the http request
        // -=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        addBasicAuthHeader(requestBuilder);

        processFrameworkDefault(headers, requestBuilder);

        return requestBuilder;
    }

    private void addBasicAuthHeader(RequestBuilder requestBuilder) {
        String basicAuth = authUser + ":" + authPassword;
        byte[] encodedBasicAuth = Base64.encodeBase64(basicAuth.getBytes(StandardCharsets.ISO_8859_1));
        String basicAuthBase64String = "Basic " + new String(encodedBasicAuth);
        LOGGER.info("Added basic auth header Authorization for, user:{}, password:{} ie base64:{} to header", authUser, authPassword, basicAuthBase64String);
        requestBuilder.addHeader(HttpHeaders.AUTHORIZATION, basicAuthBase64String);
    }


}

