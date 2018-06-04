package org.jsmart.zerocode.testhelp.tests.screeningservice;

import org.jsmart.zerocode.core.domain.JsonTestCase;
import org.jsmart.zerocode.core.domain.TargetEnv;
import org.jsmart.zerocode.core.runner.ZeroCodeUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@TargetEnv("screening_service_host.properties")
@RunWith(ZeroCodeUnitRunner.class)
public class ScreeningServiceContractTest {


    @Test
    @JsonTestCase("contract_tests/screeningservice/get_screening_details_by_custid.json")
    public void testScreeningLocalAndGlobal() throws Exception {

    }

}
