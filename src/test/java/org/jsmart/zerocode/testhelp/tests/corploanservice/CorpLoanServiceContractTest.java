package org.jsmart.zerocode.testhelp.tests.corploanservice;

import org.jsmart.zerocode.core.domain.JsonTestCase;
import org.jsmart.zerocode.core.domain.TargetEnv;
import org.jsmart.zerocode.core.runner.ZeroCodeUnitRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@TargetEnv("corploan_server_host.properties")
@RunWith(ZeroCodeUnitRunner.class)
public class CorpLoanServiceContractTest {


    @Test
    @JsonTestCase("contract_tests/corploanservice/create_post_and_get_new_loan.json")
    public void testNewLoan() throws Exception {

    }

    @Test
    @JsonTestCase("contract_tests/corploanservice/amend_put_and_get_existing_loan.json")
    public void tesAmendExistingLoan() throws Exception {

    }

}
