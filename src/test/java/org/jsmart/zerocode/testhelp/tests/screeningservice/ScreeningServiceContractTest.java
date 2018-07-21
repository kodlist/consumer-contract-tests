package org.jsmart.zerocode.testhelp.tests.screeningservice;

import org.jsmart.zerocode.core.domain.JsonTestCase;
import org.jsmart.zerocode.core.domain.TargetEnv;
import org.jsmart.zerocode.core.runner.ZeroCodeUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *   + Please make sure you have started the `RunMeFirstMockApiServer.java`
 *   + which acts like a fake RESTful server running at localhost exposing the
 *   + API end points used in these examples.
 */
@TargetEnv("screening_service_host.properties")
@RunWith(ZeroCodeUnitRunner.class)
public class ScreeningServiceContractTest {


    @Test
    @JsonTestCase("contract_tests/screeningservice/get_screening_details_by_custid.json")
    public void testScreeningLocalAndGlobal() throws Exception {

    }

    @Test
    @JsonTestCase("contract_tests/screeningservice/find_element_in_array_via_jsonpath.json")
    public void testFindElementInArray() throws Exception {

    }

}
