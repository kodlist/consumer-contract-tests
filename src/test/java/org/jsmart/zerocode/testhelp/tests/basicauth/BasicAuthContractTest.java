package org.jsmart.zerocode.testhelp.tests.basicauth;

import org.jsmart.zerocode.core.domain.JsonTestCase;
import org.jsmart.zerocode.core.domain.TargetEnv;
import org.jsmart.zerocode.core.domain.UseHttpClient;
import org.jsmart.zerocode.core.runner.ZeroCodeUnitRunner;
import org.jsmart.zerocode.httpclient.CorpBankApcheHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 *
 * Note-
 * Before running any tests-
 * Please make sure you have run the RunMeFirstMockApiServer.java which acts like a REST web server.
 *
 *
 */
@TargetEnv("basicauth_server_host.properties")
@UseHttpClient(CorpBankApcheHttpClient.class)
@RunWith(ZeroCodeUnitRunner.class)
public class BasicAuthContractTest {

    @Test
    @JsonTestCase("contract_tests/basic_auth/basic_auth_get_api_happy.json")
    public void test_basicAuthHappy() throws Exception {

    }

    @Test
    @JsonTestCase("contract_tests/basic_auth/basic_auth_get_api_sad.json")
    public void test_basicAuthNegative() throws Exception {

    }

}
