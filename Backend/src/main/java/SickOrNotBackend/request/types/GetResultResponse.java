
package SickOrNotBackend.request.types;

import SickOrNotBackend.datatypes.TestResult;

/**
 * GetResultResponse
 */
public class GetResultResponse {

    public TestResult testResult;

    public GetResultResponse(TestResult testResult){
        this.testResult = testResult;
    }
}