
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
     * @return true if case was inserted successfully, false otherwise
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
}