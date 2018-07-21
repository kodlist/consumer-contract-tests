package org.jsmart.zerocode.testhelp.tests.idchecksslservice;

import org.jsmart.zerocode.core.domain.JsonTestCase;
import org.jsmart.zerocode.core.domain.TargetEnv;
import org.jsmart.zerocode.core.domain.UseHttpClient;
import org.jsmart.zerocode.core.httpclient.ssl.SslTrustHttpClient;
import org.jsmart.zerocode.core.runner.ZeroCodeUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *   + Please make sure you have started the `RunMeFirstMockApiServer.java`
 *   + which acts like a fake RESTful server running at localhost exposing the
 *   + API end points used in these examples.
 */
@TargetEnv("identity_service_host.properties")
@UseHttpClient(SslTrustHttpClient.class)
@RunWith(ZeroCodeUnitRunner.class)
public class IdCheckServiceContractTest {

    @Test
    @JsonTestCase("contract_tests/idchecksslservice/identity_check_service_get_by_id.json")
    public void testGetById() throws Exception {

    }

}
