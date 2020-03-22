
package SickOrNotBackend.request.handlers.casecount;

/**
 * CaseCountResponse
 */
public class CaseCountResponse {

    public final long count;
    public final long untestedCount;
    public final long positiveCount;
    public final long negativeCount;


    public CaseCountResponse(long count, long untestedCount, long positiveCount, long negativeCount) {
        this.count = count;
        this.untestedCount = untestedCount;
        this.positiveCount = positiveCount;
        this.negativeCount = negativeCount;
    }
    
    
}