## Key Management Tool
Key Management Tool is a multinode backend sbin tool that will allow an admin user to manage DCM keys in the key management database. This tool touches sensitive components of the DCM system and should be used with caution. It is highly advised that any command performed by this tool should be carried out only after a successful dry-run of that command. This will allow you to validate all operations before commiting the changes to the key management database. 

### Usage :

```
root@vagrant:/services/backend/sbin# ./keymanagement-tool.sh -h

Usage: [options] <command> [<command options>]
Valid Commands : [update]
Option         Description                        
------         -----------                        
-d, --dryrun   Perform a dry run of encryption    
                 updates.                         
-f <filePath>  Path of the file to read the new   
                 encryption key from.             
-h             Show help message.                 
-p [propKey]   Property name of the encryption key
                 field when reading from a file.  
-v             Run operation in verbose mode.    

```

## Command
### Update [<code>update</code>]

A command recognized by the key management tool that performs encryption key updates on existing key-pairs inside the key management database by decrypting them using the existing encryption key and re-encrypting them using the new encryption key. Make sure all the services are stopped before executing the update command operation. (Mandatory : Dispatcher and Backend service). The update command iterates through the customer's resources and then updates the key-pairs created for those resources in the key management database. Here is the list of the entities/resources whose key-pairs are updated by the update command.

```
Account Accesses
Api Keys
Block Volumes
Configuration Management (Automation)
Cloud Accounts
Customer
Datasources (Automation)
Enstratus Users
Launch Configuration (Automation)
Regions
Relational Databases
Server Passwords
Service (Automation)
SMS Configs
SSL Certificates

```

Once the command has been run be sure to update the encryption key references in the following files with the new encryption key.

```
/services/backend/resources/enstratus-provisioning.cfg
/services/dispatcher/resources/enstratus-provisioning.cfg

```
  

## Options
### Help [<code>-h</code>]
Displays the usage, available commands and options of the tool.


### Dry-run [<code>-d, --dryrun</code>]

Perform a dry-run of the key management command operations. Running the tool in a dry-run mode will not commit any changes to the database. It is a good way to verify that a command will be able to perform key management operations such as encrypting and decrypting keys without any complications. It is highly advised to initially perform a dry-run of command opertaions.

### Read from a file [<code>-f \<filePath></code>]
A valid and mandatory option for the update command of the tool that reads and parses out the new encryption key from the specified file path. It will parse out the first line of the file as the new encryption key.

Usage example :

```
root@vagrant:/services/backend/sbin# ./keymanagement-tool.sh update -d -f keyFile 

Reading encryption key from file : keyFile

=====================================
Dry-run             : true
Verbose             : false
File                : keyFile
PropKey             : null
New encryption key  : @!#$%^&*()_+
=====================================

Will update ApiKeys encryption for customer :enStratus Networks LLC [#100]
Will update Relational Database encryption for customer :enStratus Networks LLC [#100]
Will update Server password encryption for customer :enStratus Networks LLC [#100]
Will update DataSource encryption for customer :enStratus Networks LLC [#100]
Will update Service encryption for customer :enStratus Networks LLC [#100]
Will update SSL certificate encryption for customer :enStratus Networks LLC [#100]
Will update Region Encryption for customer :enStratus Networks LLC [#100]
Will update Cloud Account encryption for customer :enStratus Networks LLC [#100]
Will update Block Volume encryption for customer :enStratus Networks LLC [#100]
Will update Enstratus user encryption for customer :enStratus Networks LLC [#100]
Will update Account Access encryption for customer :enStratus Networks LLC [#100]
Will update Customer SMS Config encryption for customer :enStratus Networks LLC [#100]
Will update Configuration Management encryption for customer :enStratus Networks LLC [#100]
Will update Launch Configuration encryption for customer :enStratus Networks LLC [#100]
Will update customer management keys and wsAccess keys for customer :enStratus Networks LLC [#100]
Will update encryption for customer : enStratus Networks LLC [#100](Dry-run)


=====================================================================================

Complete (Dry-run)

```

### Read from a file with prop key [<code>-p \[propkey]</code>]
An option for the update command of the tool that reads and parses out the encryption key from the file using the specified property key. Not specifying any argument will default back to parsing out the first line from the file as an ecnryption key.

Usage example :

```
root@vagrant:/services/backend/sbin# ./keymanagement-tool.sh update -d -f keyFile -p newKey

Reading encryption key from file : keyFile

=====================================
Dry-run             : true
Verbose             : false
File                : keyFile
PropKey             : newKey
New encryption key  : )(*&^%$#@!~
=====================================

Will update ApiKeys encryption for customer :enStratus Networks LLC [#100]
Will update Relational Database encryption for customer :enStratus Networks LLC [#100]
Will update Server password encryption for customer :enStratus Networks LLC [#100]
Will update DataSource encryption for customer :enStratus Networks LLC [#100]
Will update Service encryption for customer :enStratus Networks LLC [#100]
Will update SSL certificate encryption for customer :enStratus Networks LLC [#100]
Will update Region Encryption for customer :enStratus Networks LLC [#100]
Will update Cloud Account encryption for customer :enStratus Networks LLC [#100]
Will update Block Volume encryption for customer :enStratus Networks LLC [#100]
Will update Enstratus user encryption for customer :enStratus Networks LLC [#100]
Will update Account Access encryption for customer :enStratus Networks LLC [#100]
Will update Customer SMS Config encryption for customer :enStratus Networks LLC [#100]
Will update Configuration Management encryption for customer :enStratus Networks LLC [#100]
Will update Launch Configuration encryption for customer :enStratus Networks LLC [#100]
Will update customer management keys and wsAccess keys for customer :enStratus Networks LLC [#100]
Will update encryption for customer : enStratus Networks LLC [#100](Dry-run)


=====================================================================================

Complete (Dry-run)

```

### Verbose [<code>-v</code>]
Run the selected command operation in verbose mode.

Usage Example 1 :

```
root@vagrant:/services/backend/sbin# ./keymanagement-tool.sh update -d -f keyFile -p newKey -v

Reading encryption key from file : keyFile

=====================================
Dry-run             : true
Verbose             : true
File                : keyFile
PropKey             : newKey
New encryption key  : !@@##%$%^&%^&$###$%
=====================================

Will update ApiKeys encryption for customer :Enstratius Inc. [#200]

     Processing key : 104
          New Encrypted keys : [6b0deb198....]
          Will update keys for key pair 104

Will update Relational Database encryption for customer :Enstratius Inc. [#200]
Will update Server password encryption for customer :Enstratius Inc. [#200]
Will update DataSource encryption for customer :Enstratius Inc. [#200]
Will update Service encryption for customer :Enstratius Inc. [#200]
Will update SSL certificate encryption for customer :Enstratius Inc. [#200]
Will update Region Encryption for customer :Enstratius Inc. [#200]
Will update Cloud Account encryption for customer :Enstratius Inc. [#200]

     Processing key : 102
          New Encrypted keys : [6de015ab4....]
          Will update keys for key pair 102


     Processing key : 101
          New Encrypted keys : [d17560efa....]
          Will update keys for key pair 101

Will update Block Volume encryption for customer :Enstratius Inc. [#200]
Will update Enstratus user encryption for customer :Enstratius Inc. [#200]

     Processing key : 105
          New Encrypted keys : [04de9d6c8....]
          Will update keys for key pair 105


     Processing key : 106
          New Encrypted keys : [d87f50ad9....]
          Will update keys for key pair 106

Will update Account Access encryption for customer :Enstratius Inc. [#200]
Will update Customer SMS Config encryption for customer :Enstratius Inc. [#200]
Will update Configuration Management encryption for customer :Enstratius Inc. [#200]
Will update Launch Configuration encryption for customer :Enstratius Inc. [#200]
Will update customer management keys and wsAccess keys for customer :Enstratius Inc. [#200]

     New encrypted management key : [0ecf6....]
     New encrypted wsAccess key   : [c4411....]

Will update encryption for customer : Enstratius Inc. [#200](Dry-run)


=====================================================================================


Complete (Dry-run)

```

Usage Example 2 (with dry-run set to false):

```
root@vagrant:/services/backend/sbin# ./keymanagement-tool.sh updateEncryption  -v -f keyFile -p newKey

Reading encryption key from file : testFile

=====================================
Dry-run             : false
Verbose             : true
File                : keyFile
PropKey             : newKey
New encryption key  : !@@##%$%^&%^&$###$%
=====================================


Updating ApiKeys encryption for customer :Enstratius Inc. [#200]

     Processing key : 104
          New Encrypted keys : [c05552c01....]
          Updated keys for key pair 104

Updating Relational Database encryption for customer :Enstratius Inc. [#200]
Updating Server password encryption for customer :Enstratius Inc. [#200]
Updating DataSource encryption for customer :Enstratius Inc. [#200]
Updating Service encryption for customer :Enstratius Inc. [#200]
Updating SSL certificate encryption for customer :Enstratius Inc. [#200]
Updating Region Encryption for customer :Enstratius Inc. [#200]
Updating Cloud Account encryption for customer :Enstratius Inc. [#200]

     Processing key : 102
          New Encrypted keys : [ac1236819....]
          Updated keys for key pair 102


     Processing key : 101
          New Encrypted keys : [afc78e9cf....]
          Updated keys for key pair 101

Updating Block Volume encryption for customer :Enstratius Inc. [#200]
Updating Enstratus user encryption for customer :Enstratius Inc. [#200]
Updating Account Access encryption for customer :Enstratius Inc. [#200]
Updating Customer SMS Config encryption for customer :Enstratius Inc. [#200]
Updating Configuration Management encryption for customer :Enstratius Inc. [#200]
Updating Launch Configuration encryption for customer :Enstratius Inc. [#200]
Updating customer management keys and wsAccess keys for customer :Enstratius Inc. [#200]

     New encrypted management key : [43c0a....]
     New encrypted wsAccess key   : [c282f....]

Updated encryption for customer : Enstratius Inc. [#200]


=====================================================================================


Complete 
```



