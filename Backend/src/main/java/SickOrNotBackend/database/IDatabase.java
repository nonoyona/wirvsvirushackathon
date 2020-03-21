
package SickOrNotBackend.database;

import SickOrNotBackend.datatypes.Case;
import SickOrNotBackend.datatypes.HealthType;

/**
 * IDatabase
 */
public interface IDatabase {

    /**
     * Inserts a case into the database
     * 
     * @return true if case was inserted successfully, false otherwise (e.g. key duplicate)
     */
    public boolean insertCase(Case c);

    /**
     * Gets the health state of case wit id [id].
     * 
     * REQUIRES case with id [id] to be existent in database
     * 
     * @param id id of the case
     * @return health state of the patient
     * @throws NullpointerException if no case wit id [id] exists
     */
    public HealthType getState(String id);

    /**
     * Determinates whether a case exists in the database.
     * 
     * @return true if the case exists, false otherwise
     */
    public boolean caseExists(String id);

    /**
     * Updates the health status of a case
     * 
     * Updates the health status of a case with id [id]
     * 
     * REQUIRES case with id [id] to be existent in database
     * 
     * @param status new status of the case
     * @param id case id
     */
    public void updateHealthStatus(HealthType status, String id);

    /**
     * Gets a case with id [id]
     * 
     * REQUIRES case with id [id] to be existent in database
     * 
     * This method should only be called by an admin or an institution with
     * access rights for this case.
     * 
     * @return case with the specific id
     */
    public Case getCase(String id);

}