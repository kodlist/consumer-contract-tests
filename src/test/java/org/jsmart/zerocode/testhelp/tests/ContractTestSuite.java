package org.jsmart.zerocode.testhelp.tests;

import org.jsmart.zerocode.testhelp.tests.corploanservice.CorpLoanServiceContractTest;
import org.jsmart.zerocode.testhelp.tests.idchecksslservice.IdCheckServiceContractTest;
import org.jsmart.zerocode.testhelp.tests.regulatoryservice.RegulatoryServiceContractTest;
import org.jsmart.zerocode.testhelp.tests.screeningservice.ScreeningServiceContractTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@Suite.SuiteClasses({
        RegulatoryServiceContractTest.class,
        IdCheckServiceContractTest.class,
        CorpLoanServiceContractTest.class,
        ScreeningServiceContractTest.class
})
@RunWith(Suite.class)
public class ContractTestSuite {

}
