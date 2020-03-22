
package SickOrNotBackend.request.handlers.caselisting;

import java.util.List;

import SickOrNotBackend.datatypes.Case;

/**
 * CaseListingResult
 */
public class CaseListingResult {

    public final List<Case> cases;


    public CaseListingResult(List<Case> cases) {
        this.cases = cases;
    }
    
}