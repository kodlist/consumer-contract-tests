# consumer-contract-tests
Sample Consumer Contract Tests - Banking

+ To run all the tests, use:
```java
org.jsmart.zerocode.testhelp.tests.ContractTestSuite
```

+ To run a particular contract test, use:
```java
org...tests.corploanservice.CorpLoanServiceContractTest
org...tests.idchecksslservice.IdCheckServiceContractTest
org...tests.regulatoryservice.RegulatoryServiceContractTest
org...tests.screeningservice.ScreeningServiceContractTest
```

+ See also contract tests without any hardcoded value, the test-steps has been chained from earlier `request/response`:
```java
@TargetEnv("corploan_server_host.properties")
@UseHttpClient(SslTrustHttpClient.class)
@RunWith(ZeroCodeUnitRunner.class)
public class CorpLoanServiceContractTest {

    @Test
    @JsonTestCase("contract_tests/corploanservice/create_post_and_get_new_loan.json")
    public void testNewLoan_crudOperations() throws Exception {

    }

}

```
 and the test:
```javaScript
        {
            "name": "get_loan_details_without_hard_coding",
            "url": "/api/v1/corp-loan/ids/${$.create_approved_loan.response.body.id}",
            "operation": "GET",
            "request": {
            },
            "assertions": {
                "status": 200,
                "body":{
                    "id" : "${$.create_approved_loan.response.body.id}",
                    "currency": "${$.create_approved_loan.request.body.currency}",
                    "dateSanctioned":"${$.create_approved_loan.request.body.dateSanctioned}",
                    "amountApplied": "$EQ.${$.create_approved_loan.request.body.amountApplied}",
                    "amountSanctioned": "$EQ.${$.create_approved_loan.request.body.amountSanctioned}"
                }
            }
        }

```

+ corploan_server_host.properties contains your target server details:
```properties
# Web Server host and port
restful.application.endpoint.host=http://localhost
restful.application.endpoint.port=8088

# Web Service context; Leave it blank in case you do not have a common context
restful.application.endpoint.context=

```