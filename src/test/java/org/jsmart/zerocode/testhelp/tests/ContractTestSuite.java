package org.jsmart.zerocode.testhelp.tests;

import org.jsmart.zerocode.testhelp.tests.basicauth.BasicAuthContractTest;
import org.jsmart.zerocode.testhelp.tests.corploanservice.CorpLoanServiceContractTest;
import org.jsmart.zerocode.testhelp.tests.idchecksslservice.IdCheckServiceContractTest;
import org.jsmart.zerocode.testhelp.tests.regulatoryservice.RegulatoryServiceContractTest;
import org.jsmart.zerocode.testhelp.tests.screeningservice.ScreeningServiceContractTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *   + Please make sure you have started the `RunMeFirstMockApiServer.java`
 *   + which acts like a fake RESTful server running at localhost exposing the
 *   + API end points used in these examples.
 */
@Suite.SuiteClasses({
        RegulatoryServiceContractTest.class,
        IdCheckServiceContractTest.class,
        CorpLoanServiceContractTest.class,
        ScreeningServiceContractTest.class,
        BasicAuthContractTest.class
})
@RunWith(Suite.class)
public class ContractTestSuite {

}
