package com.blackboard.test.wsclient.wrappers;

import blackboard.platform.ws.xsd.VersionVO;
import blackboard.ws.user.*;
import blackboard.ws.user.xsd.*;

import blackboard.ws.user.xsd.AddressBookEntryVO;
import blackboard.ws.user.xsd.ObserverAssociationVO;
import blackboard.ws.user.xsd.PortalRoleVO;
import blackboard.ws.user.xsd.UserFilter;
import blackboard.ws.user.xsd.UserRoleVO;
import blackboard.ws.user.xsd.UserVO;
import com.blackboard.test.wsclient.ClientWrapper;

import java.rmi.RemoteException;

/*
 * Refer to LICENSE_for_samples.txt for license details relating to this file
 */
public class UserWrapper extends BaseClientWrapper
{

  private UserWSStub _userWS = null;

 //User Filter for User Web Service
  public static final int GET_ALL_USERS_WITH_AVAILABILITY = 1;
  public static final int GET_USER_BY_ID_WITH_AVAILABILITY = 2;
  public static final int GET_USER_BY_BATCH_ID_WITH_AVAILABILITY = 3;
  public static final int GET_USER_BY_COURSE_ID_WITH_AVAILABILITY = 4;
  public static final int GET_USER_BY_GROUP_ID_WITH_AVAILABILITY = 5;
  public static final int GET_USER_BY_NAME_WITH_AVAILABILITY = 6;
  public static final int GET_USER_BY_SYSTEM_ROLE = 7;
  public static final int GET_ADDRESS_BOOK_ENTRY_BY_ID = 8;
  public static final int GET_ADDRESS_BOOK_ENTRY_BY_CURRENT_USERID = 9;

  public UserWrapper( ClientWrapper client, String password, String userId, String vendorId, String programId, UserWSStub userWS )
  {
    super( client, password, userId, vendorId, programId );
    _userWS = userWS;
  }

  /**
   * Returns the current version of this web service on the server
   * @param unused - this is an optional parameter put here to make the generation of .net client
   *                 applications from the wsdl 'cleaner' (0-argument methods do not generate clean stubs and
   *                 are much harder to have the same method name across multiple Web Services in the same .net client)
   * @since 1 
   */
  public VersionVO getServerVersion( VersionVO unused )
  {
    try
    {
      GetServerVersion getServerVersionParam = new GetServerVersion();
      getServerVersionParam.setUnused( unused );

      return _userWS.getServerVersion( getServerVersionParam ).get_return();
    }
    catch ( RemoteException re )
    {
      saveAndLogError( "Failed to get server version for user webservice", re );
    }
    return null;
  }

  /**
   * sets the client version to version 1 and returns an appropriate session. 
   * With each release of this web service we will implement a new initializeVersionXXX method 
   * @param ignore - this is an optional parameter put here to make the generation of .net client
   *                 applications from the wsdl 'cleaner' (0-argument methods do not generate clean stubs and
   *                 are much harder to have the same method name across multiple Web Services in the same .net client)
   * @return true to indicate that the session has been initialized for the user ws
   * @since 1
   */
  public boolean initializeUserWS( boolean ignore )
  {
    try
    {
      InitializeUserWS initializeUserWSParam = new InitializeUserWS();
      initializeUserWSParam.setIgnore( ignore );

      return _userWS.initializeUserWS( initializeUserWSParam ).get_return();
    }
    catch ( RemoteException re )
    {
      saveAndLogError( "Failed to initialize user webservice", re );
    }
    return false;
  }

  public String[] saveUser( UserVO[] user )
  {
    try
    {
      SaveUser saveUserParam = new SaveUser();
      saveUserParam.setUser( user );

      return _userWS.saveUser( saveUserParam ).get_return();
    }
    catch ( RemoteException re )
    {
      saveAndLogError( "Failed to save a user", re );
    }
    return null;
  }

  /**
   * Add observer to observee( user) association 
   * @param association represent the observee ids to be associated with observer id
   *  @return array of userIds that were successfully associated with observer
   *                        - a null array indicates no operation
   */
  public String[] saveObserverAssociation( ObserverAssociationVO[] association )
  {
    try
    {
      SaveObserverAssociation saveObserverAssociationParam = new SaveObserverAssociation();
      saveObserverAssociationParam.setAssociation( association );

      return _userWS.saveObserverAssociation( saveObserverAssociationParam ).get_return();
    }
    catch ( RemoteException re )
    {
      saveAndLogError( "Failed to save observer association", re );
    }
    return null;
  }

  /**
   * Create/ Update  address book entries for the given user
   * @param entry address book entry array to be added/updated
   * @return The array of address book entries ids that was created/updated
   *                              - a null array indicates no operation
   */
  public String[] saveAddressBookEntry( AddressBookEntryVO[] entry )
  {
    try
    {
      SaveAddressBookEntry saveAddressBookEntryParam = new SaveAddressBookEntry();
      saveAddressBookEntryParam.setUser( entry );

      return _userWS.saveAddressBookEntry( saveAddressBookEntryParam ).get_return();
    }
    catch ( RemoteException re )
    {
      saveAndLogError( "Failed to save address book entry", re );
    }
    return null;
  }

  /**
   * Removes the user with the given id
   * @param userId array of user ids to be removed   
   * @return array of userIds
   *                                   - a null array indicates no operation
   *                                   - a valid array value indicates success for that corresponding userId
   *                                   - a empty/null string value indicates user has no permission to delete
   */
  public String[] deleteUser( String[] userId )
  {
    try
    {
      DeleteUser deleteUserParam = new DeleteUser();
      deleteUserParam.setUserId( userId );

      return _userWS.deleteUser( deleteUserParam ).get_return();
    }
    catch ( RemoteException re )
    {
      saveAndLogError( "Failed to delete a user", re );
    }
    return null;
  }

  /**
  * Removes the user with the given institution/portal role
  * @param insRoleId
  * @return array of userIds that was successfully removed
  *             - null value indicates no-operation since no users qualified to be removed based on the given values
  */
  public String[] deleteUserByInstitutionRole( String[] insRoleId )
  {
    try
    {
      DeleteUserByInstitutionRole deleteUserByInstitutionRoleParam = new DeleteUserByInstitutionRole();
      deleteUserByInstitutionRoleParam.setInsRoleId( insRoleId );

      return _userWS.deleteUserByInstitutionRole( deleteUserByInstitutionRoleParam ).get_return();
    }
    catch ( RemoteException re )
    {
      saveAndLogError( "Failed to delete user by institution role", re );
    }
    return null;
  }

  /**
   *  Removes the address book entries for the given user
   * @param entryId array of address book entries to be removed
   * @return array of address book entryIds
   *                                   - a null array indicates no operation
   *                                   - a valid array value indicates success for that corresponding entryId
   *                                   - a empty/null string value indicates user has no permission to delete
   */
  public String[] deleteAddressBookEntry( String[] entryId )
  {
    try
    {
      DeleteAddressBookEntry deleteAddressBookEntryParam = new DeleteAddressBookEntry();
      deleteAddressBookEntryParam.setEntryId( entryId );

      return _userWS.deleteAddressBookEntry( deleteAddressBookEntryParam ).get_return();
    }
    catch ( RemoteException re )
    {
      saveAndLogError( "Failed to delete an addressbook entry", re );
    }
    return null;
  }

  /**
   * Remove observer to observee association 
   * @param association represent the observee ids to be removed with observer id
   * @return array of user ids that were removed from the observer association
   *                                   - a null array indicates no operation
   */
  public String[] deleteObserverAssociation( ObserverAssociationVO[] association )
  {
    try
    {
      DeleteObserverAssociation deleteObserverAssociationParam = new DeleteObserverAssociation();
      deleteObserverAssociationParam.setAssociation( association );

      return _userWS.deleteObserverAssociation( deleteObserverAssociationParam ).get_return();
    }
    catch ( RemoteException re )
    {
      saveAndLogError( "Failed to delete observer association", re );
    }
    return null;
  }

  /**
  * load users based on the filter parameters
  * @param filter
  * @return an array of users  matching the UserFilter
  */
  public UserVO[] getUser( UserFilter filter )
  {
    try
    {
      GetUser getUserParam = new GetUser();
      getUserParam.setFilter( filter );
      
      return _userWS.getUser( getUserParam ).get_return();
    }
    catch ( RemoteException re )
    {
      saveAndLogError( "Failed to get users", re );
    }
    return null;
  }

  /**
   * load address book entries for the given user based on the filter parameters
   * @param filter
   * @return an array of address book entries  matching the UserFilter
   */
  public AddressBookEntryVO[] getAddressBookEntry( UserFilter filter )
  {
    try
    {
      GetAddressBookEntry getAddressBookEntryParam = new GetAddressBookEntry();
      getAddressBookEntryParam.setFilter( filter );
      
      return _userWS.getAddressBookEntry( getAddressBookEntryParam ).get_return();
    }
    catch ( RemoteException re )
    {
      saveAndLogError( "Failed to get address book entry", re );
    }
    return null;
  }

  /**
  * load observee for the given observer ids in the filter
  * @param ids a list of observer ids
  * @return an array of observer/observee association  matching the UserFilter
  */
  public ObserverAssociationVO[] getObservee( String[] ids)
  {
    try
    {
      GetObservee getObserveeParam = new GetObservee();
      getObserveeParam.setObserverId( ids );
      
      return _userWS.getObservee( getObserveeParam ).get_return();
    }
    catch ( RemoteException re )
    {
      saveAndLogError( "Failed to get observee", re );
    }
    return null;
  }

  /**
   * Change a user's batch id
   * @param originalBatchUid the user's original batch uid
   * @param batchUid new batch uid
   * @return true if save successfully, otherwise false
   */
  public boolean changeUserBatchUid( String originalBatchUid, String batchUid )
  {
    try
    {
      ChangeUserBatchUid changeUserBatchUidParam = new ChangeUserBatchUid();
      changeUserBatchUidParam.setOriginalBatchUid( originalBatchUid );
      changeUserBatchUidParam.setBatchUid( batchUid );
      
      return _userWS.changeUserBatchUid( changeUserBatchUidParam ).get_return();
    }
    catch ( RemoteException re )
    {
      saveAndLogError( "Failed to change external user key", re );
    }
    return false;
  }

  public boolean changeUserDataSourceId( String userId, String newDataSourceId )
  {
    try
    {
      ChangeUserDataSourceId changeUserDataSourceIdParam = new ChangeUserDataSourceId();
      changeUserDataSourceIdParam.setUserId( userId );
      changeUserDataSourceIdParam.setDataSourceId( newDataSourceId );
      
      return _userWS.changeUserDataSourceId( changeUserDataSourceIdParam ).get_return();
    }
    catch ( RemoteException re )
    {
      saveAndLogError( "Failed to change user data source", re );
    }
   
    return false;
  }
  
  public PortalRoleVO[] getInstitutionRoles( String[] ids )
  {
    try
    {
      GetInstitutionRoles getInstitutionRolesParam = new GetInstitutionRoles();
      getInstitutionRolesParam.setIds( ids );
      
      return _userWS.getInstitutionRoles( getInstitutionRolesParam ).get_return();

    }
    catch ( RemoteException re )
    {
      saveAndLogError( "Failed to get institution roles", re );
    }
    
    return null;
  }

  public String[] getSystemRoles( String[] filter )
  {

    try
    {
      GetSystemRoles getSystemRolesParam = new GetSystemRoles();
      getSystemRolesParam.setFilter( filter );
      
      return _userWS.getSystemRoles( getSystemRolesParam ).get_return();

    }
    catch ( RemoteException re )
    {
      saveAndLogError( "Failed to get system roles", re );
    }
    
    return null;
  
  }
  
  public UserRoleVO[] getUserInstitutionRoles( String userId[] )
  {
    try
    {
      GetUserInstitutionRoles getUserInstitutionRolesParam = new GetUserInstitutionRoles();
      getUserInstitutionRolesParam.setUserId( userId );
      
      return _userWS.getUserInstitutionRoles( getUserInstitutionRolesParam ).get_return();

    }
    catch ( RemoteException re )
    {
      saveAndLogError( "Failed to get user institution roles", re );
    }
    
    return null;
  }


}

