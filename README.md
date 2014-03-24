.. raw:: latex
  
      \newpage

.. _integration_active_directory_testing:

Testing
-------

.. note:: Make sure you have ldap utilities installed in order to perform
   command line ldapsearch queries.

For RHEL : openldap-clients package must be installed.

For UBUNTU : ldap-utils package must be installed.

We can use the core information to generate LDAP search queries in order to
test the connection with AD server and extract more information. Running a
successful ldap search query is one way of validating the correctness of core
information.

Environment Variables
~~~~~~~~~~~~~~~~~~~~~

Set the following environmental variables for test ldapsearch query

.. code-block:: bash

   #Access credentials
   export LDAP_URI="<LDAP_ENDPOINT>"
   export LDAP_PRIN="<DN_OF_PRINCIPAL_USER>"
   export LDAP_PASS="<PRINCIPAL_PASSWORD>"
   
   # Access Groups. LDAP_GROUP will have the CN of group you want to query on (Admin/Non-Admin).
   export LDAP_GROUP="<CN_OF_GROUP>"  

Once you have the environmental variables setup, run the following query.

.. code-block:: bash

   ldapsearch -v -x -D "$LDAP_PRIN" -w "$LDAP_PASS" -s sub -H $LDAP_URI "(&(objectClass=group)(cn=*$LDAP_GROUP*))" 

The results should list out the members belonging to that group along with the group information.

Extract Group Base
~~~~~~~~~~~~~~~~~~

If the group base was not provided, run the same query mentioned above with a
'dn' filter at the end.

.. code-block:: bash

   ldapsearch -v -x -D "$LDAP_PRIN" -w "$LDAP_PASS" -s sub -H $LDAP_URI "(&(objectClass=group)(cn=*$LDAP_GROUP*))" dn

This should provide you with the distinguished name of the group. So for example if the dn value is

.. code-block:: bash

   dn: CN=CSEAdmins,OU=CSE Groups,DC=cse,DC=Enstratius,DC=com

then your group base will be combination of attributes after CN

.. code-block:: bash

   OU=CSE Groups,DC=cse,DC=Enstratius,DC=com
 
Extract User Base
~~~~~~~~~~~~~~~~~

If the user base was not provided, execute same query mentioned above with a
'member' filter at the end.

.. code-block:: bash

   ldapsearch -v -x -D "$LDAP_PRIN" -w "$LDAP_PASS" -s sub -b "$LDAP_BASE" -H $LDAP_URI "(&(objectClass=group)(cn=*$LDAP_GROUP*))" member

This should provide you with the full distinguished name of members/users
belonging to that group. Now select the most common denominator from resultant
member attribute values. For example if the results contain:

.. code-block:: bash

   member: CN=New Admin,CN=Users,DC=cse,DC=Enstratius,DC=com
   member: CN=Binod Kc,CN=Users,DC=cse,DC=Enstratius,DC=com
   member: CN=Dave Mao,CN=Users,DC=cse,DC=Enstratius,DC=com
   member: CN=jimsander,CN=Users,DC=cse,DC=Enstratius,DC=com

Then the user base will be :

.. code-block:: bash

   CN=Users,DC=cse,DC=Enstratius,DC=com


Testing using DirsyncTool
~~~~~~~~~~~~~~~~~~~~~~~~~

Once all the LDAP confiugration values has been added and a directory service has been created, the user can validate the LDAP configuration by using the '-c $serviceId' function of the tool. Using '-c' on service will attempt to query LDAP server for groups (both admin and standard) specified for that service and also attempt to list out users in those group.

For a service where DCM is successfully able to query LDAP using the provided configuration:

.. code-block:: bash
   root@vagrant:/services/console/sbin# ./dirsync-tool.sh -c400

   Using LDAP configuration of service : 400 to list groups from LDAP server:
   ******************************************************************************************
   Found : CSEAdmins
   Found : CSEUsers
   
   Not Found : phoneyGroup
   
   Using LDAP configuration of service : 400 to list identities from LDAP server:
   ******************************************************************************************
   Found : Binod Kc
   Found : Dave Mao
   Found : Jim Sander
   Found : Jim Sander
   Found : Keith Hudgins
   Found : New Admin

   Was successfully able to authenticate and query for groups and users for service 400.

For a service where DCM fails to query LDAP using the provided configuration:

.. code-block:: bash  
   root@vagrant:/services/console/sbin# ./dirsync-tool.sh -c400

   Using LDAP configuration of service : 400 to list groups from LDAP server:
   ******************************************************************************************
   
   ERROR: Failed to perform queries using current LDAP configuration for service : 400. Please check LDAP credentials and    configuration.

It is highly advised to run this step right after adding/updating a directory service.


   

