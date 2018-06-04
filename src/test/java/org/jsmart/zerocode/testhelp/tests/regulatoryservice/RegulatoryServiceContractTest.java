package org.jsmart.zerocode.testhelp.tests.regulatoryservice;

import org.jsmart.zerocode.core.domain.JsonTestCase;
import org.jsmart.zerocode.core.domain.TargetEnv;
import org.jsmart.zerocode.core.runner.ZeroCodeUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@TargetEnv("regulatory_server_host.properties")
@RunWith(ZeroCodeUnitRunner.class)
public class RegulatoryServiceContractTest {


    @Test
    @JsonTestCase("contract_tests/regulatoryservice/get_details_by_id.json")
    public void testGet() throws Exception {

    }

}
