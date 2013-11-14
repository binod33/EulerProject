# Iara 3.x

## High level changes

* SQL schema changes ARE necessary
* todo: data migration
* todo: conf changes
* todo: major features
* Bug fixes

## Minor updates

* <code>i.3</code>
    * Initial Iara 3 release, 2013-11-??

Exact diffs can be located via the [comparison links](https://docs.int.enstratus.com/qa/namedreleases.html)

## Dasein-cloud driver updates

* TODO


## Bug Fixes

* TODO

## Upgrade process

Upgrade steps for <code>i.2</code> to <code>i.3</code>

### 1. Update application code

TODO

## Encryption Changes

### Encryption Version THREE

A new version of encryption “THREE” is introduced in Iara 3.0. It uses AES encryption with Cipher Block Chaining (CBC) mode along with PKCS5 Padding to encrypt and decrypt credentials from the key management database. Old credentials will continue using old encryption/decryption methods. New credentials introduced to the key management database will start using the new version for encryption and decryption.

### Encryption Version in License Keys

Encryption version for license keys is now tracked separately in the database by a field <code>encryption_version</code> in the <code>backend_configuration</code> table of the provisioning database. The default version of encryption for license keys is ‘TWO’. Starting Iara 3.0 and onwards, please make sure to ask for the value of encryption version along with the license key whenever renewing/updating license keys of a system.

### Encryption for customer LDAP access password 

LDAP service passwords were encrypted using an outdated algorithm prior to Iara 3.0. Any new LDAP service added to the system will now use the latest encryption version to encrypt the password and generate a secret key. The encryption version for LDAP service is now tracked in the database separately by a field <code>encryption_version</code> in the <code>customer_ldap_directory</code> table of the enstratus console database. The Dirsync Tool also provides new functions in Iara 3.0 that can generate new secret keys using the latest encryption version and update existing customer’s LDAP service secret key.[Check <code>dirsync-tool.sh —h</code> for more info]

