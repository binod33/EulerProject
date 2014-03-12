## ServerProduct-Tool
ServerProduct-Tool is a mutlinode backend sbin tool that allows you sync cloud server product pricing with DCM. This tool can add new server products if absent in DCM and update existing DCM server product's pricing with the latest from the  source file but it WILL NOT DELETE existing server products.

## Usage

```
root@vagrant:/services/monitor/sbin# ./serverproduct-tool.sh -h
Usage: -update-parameter -source-parameter [-optional-action] [-optional-action] 

Options:
   -h, --help                         show this help message and exit.
   -v, --version                      show the version of the tool.
    Update parameter:
    ---------------------------------------

      -c $cloudId                     id of the cloud to update product.

    Source parameters:
    ---------------------------------------

      -u $url                         read server pricing data from a URL of the JSON file. 
      -m $filepath                    read multiple URLs from a file. 
      -f $filePath                    read server pricing data directly from a JSON file.

    Optional actions:
    ---------------------------------------

      --dryrun                        dry-run pricing update. 
      --printJSON                     prints out custom server products JSON for dasein.

```

## Required JSON schema

This tool parses out pricing data for instances from a JSON file. Here is the schema of the required JSON file.

```
{
    "vers": 0.01,
    "config": {
        "rate": "perhr",
        "valueColumns": [
            "vCPU",
            "ECU",
            "memoryGiB",
            "storageGB",
            "linux"
        ],
        "currencies": [
            "USD"
        ],
        "regions": [
            {
                "region": "us-east",
                "instanceTypes": [
                    {
                        "type": "generalCurrentGen",
                        "sizes": [

                            {
                                "size": "m3.medium",
                                "vCPU": "1",
                                "ECU": "3",
                                "memoryGiB": "3.75",
                                "storageGB": "1 x 4 SSD",
                                "valueColumns": [
                                    {
                                        "name": "linux",
                                        "prices": {
                                            "USD": "0.113"
                                        }
                                    }
                                ]
                            }
                        ]
                    }
                ]
            },
            {
                "region": "us-west-2",
                "instanceTypes": [
                    {
                        "type": "generalCurrentGen",
                        "sizes": [
                            {
                                "size": "m3.medium",
                                "vCPU": "1",
                                "ECU": "3",
                                "memoryGiB": "3.75",
                                "storageGB": "1 x 4 SSD",
                                "valueColumns": [
                                    {
                                        "name": "linux",
                                        "prices": {
                                            "USD": "0.113"
                                        }
                                    }
                                ]
                            }
                        ]                        
                    }
                 ]
            }
        ]
    }
}
                  
                            
```

## Update parameter

### Cloud ID

Should represent the DCM id of the cloud that requires server product pricing update. It is represented by <code>"-c"<code> option that takes in required <code>LONG<code> type argument. 

## Source parameter

Should represent the JSON data source. It can either be  a URL of the JSON file or a file containing multiple URLs or a local filepath of the JSON file.

### Read from a URL
The tool can read and parse out products from a URL of the JSON file. It is acheived by the <code>"-u"<code> option. This option requres an argument. 

Usage example :
```
root@vagrant:/services/monitor/sbin# ./serverproduct-tool.sh -c1 -uhttp://aws-assets-pricing-prod.s3.amazonaws.com/pricing/ec2/linux-od.js 
Cloud Id     : 1
URL          : http://aws-assets-pricing-prod.s3.amazonaws.com/pricing/ec2/linux-od.js
URL filepath : null
Filepath     : null
Dry-run      : true
PrintJsonFile: true

Created server product [ m3.medium [3.75 GB memory/4 GB storage/3 Compute Units]/i386/Generic Unix/]

Updated server product [3018/m3.xlarge/x86_64/Generic Unix/us-east-1]  : Changes : {"standardPricingCurrency":{"new":0.45,"old":0.5}}

Add count    : 1
Update count : 1

```

### Read from a file containing URLS
The tool can also read from multiple URLs at the same time. Create a file containing just the URLS in each line and then specify the path of the file using the option <code>"-m"<code>.

Usage example:
```
root@vagrant:/services/monitor/sbin# cat productUrls 
http://aws-assets-pricing-prod.s3.amazonaws.com/pricing/ec2/linux-od.js
http://aws-assets-pricing-prod.s3.amazonaws.com/pricing/ec2/mswin-od.js
http://aws-assets-pricing-prod.s3.amazonaws.com/pricing/ec2/rhel-od.js
http://aws-assets-pricing-prod.s3.amazonaws.com/pricing/ec2/sles-od.js
http://aws-assets-pricing-prod.s3.amazonaws.com/pricing/ec2/mswinSQL-od.js
http://aws-assets-pricing-prod.s3.amazonaws.com/pricing/ec2/mswinSQLWeb-od.js


root@vagrant:/services/monitor/sbin# ./serverproduct-tool.sh -c1 -m/services/monitor/sbin/productUrls  
Cloud Id     : 1
URL          : null
URL filepath : /services/monitor/sbin/productUrls
Filepath     : null
Dry-run      : true
PrintJsonFile: true


Reading from file : /services/monitor/sbin/productUrls

Extracting data from url : http://aws-assets-pricing-prod.s3.amazonaws.com/pricing/ec2/linux-od.js
Extracting data from url : http://aws-assets-pricing-prod.s3.amazonaws.com/pricing/ec2/mswin-od.js
Extracting data from url : http://aws-assets-pricing-prod.s3.amazonaws.com/pricing/ec2/rhel-od.js
Extracting data from url : http://aws-assets-pricing-prod.s3.amazonaws.com/pricing/ec2/sles-od.js
Extracting data from url : http://aws-assets-pricing-prod.s3.amazonaws.com/pricing/ec2/mswinSQL-od.js
Extracting data from url : http://aws-assets-pricing-prod.s3.amazonaws.com/pricing/ec2/mswinSQLWeb-od.js

Created server product [ m3.medium [3.75 GB memory/4 GB storage/3 Compute Units]/i386/Generic Unix/] 
Created server product [ m3.medium [3.75 GB memory/4 GB storage/3 Compute Units]/i386/Windows/]
Created server product [ m3.medium [3.75 GB memory/4 GB storage/3 Compute Units]/i386/Red Hat/] 
Created server product [ m3.medium [3.75 GB memory/4 GB storage/3 Compute Units]/i386/SUSE/]
Created server product [ m3.medium [3.75 GB memory/4 GB storage/3 Compute Units]/i386/Windows/Standard SQL] 
Created server product [ m3.medium [3.75 GB memory/4 GB storage/3 Compute Units]/i386/Windows/Web SQL] 

Updated server product [7074/t1.micro/x86_64/Generic Unix/sa-east-1]  : Changes : {"standardPricingCurrency":{"new":0.027,"old":0.007}}
Updated server product [3020/m3.xlarge/x86_64/Windows/us-east-1]  : Changes : {"standardPricingCurrency":{"new":0.702,"old":0.78}}

Add count    : 5
Update count : 2

```

### Read directly from a JSON file
The tool can read data out directly from a JSON file by specifying it's path as an argument when using <code>"-f"<code> option.

Usage example :
```
root@vagrant:/services/monitor/sbin# ./serverproduct-tool.sh -c1 -f/services/monitor/sbin/jsonFile.json --dryrun --printJSON 
Cloud Id     : 1
URL          : null
URL filepath : null
Filepath     : /services/monitor/sbin/jsonFile.json
Dry-run      : true
PrintJsonFile: true


Reading from file : /services/monitor/sbin/jsonFile.json

Created server product [ m3.medium [3.75 GB memory/4 GB storage/3 Compute Units]/i386/Generic Unix/] 
Created server product [ m3.medium [3.75 GB memory/4 GB storage/3 Compute Units]/x86_64/Generic Unix/] 
Created server product [ m3.medium [3.75 GB memory/4 GB storage/3 Compute Units]/i386/Generic Unix/] 


Add count    : 3
Update count : 0

```

## Optional parameters

### Dry-run
The Dry-run feature of the tool will list out the new server products will be created/updated during the sync but will not commit to the DCM database. It is highly advised to run this tool using a <code>"--dryrun"<code> option first before actullly commiting to the changes in the database.

Usage example :

```
root@vagrant:/services/monitor/sbin# ./serverproduct-tool.sh -c1 -f/services/monitor/sbin/product.json --dryrun 
Cloud Id     : 1
URL          : null
URL filepath : null
Filepath     : /services/monitor/sbin/jsonFile.json
Dry-run      : true
PrintJsonFile: true


Reading from file : /services/monitor/sbin/jsonFile.json

Will create server product [ m3.medium [3.75 GB memory/4 GB storage/3 Compute Units]/i386/Generic Unix/]
Will create server product [ m3.medium [3.75 GB memory/4 GB storage/3 Compute Units]/x86_64/Generic Unix/] 
Will create server product [ m3.medium [3.75 GB memory/4 GB storage/3 Compute Units]/i386/Generic Unix/] 
Will create server product [ m3.medium [3.75 GB memory/4 GB storage/3 Compute Units]/x86_64/Generic Unix/] 

Will update server product [7074/t1.micro/x86_64/Generic Unix/sa-east-1]  : Changes : {"standardPricingCurrency":{"new":0.027,"old":0.007}}
Will update server product [3020/m3.xlarge/x86_64/Windows/us-east-1]  : Changes : {"standardPricingCurrency":{"new":0.702,"old":0.78}}



Add count    : 4
Update count : 2
```

### Printing JSON file.
The tool also prints out the unique products gathered from the source and compiles a JSON file that a dasein driver can read in order to update the drop down server product list in the server launch form.


```
root@vagrant:/services/monitor/sbin# ./serverproduct-tool.sh -c1 -f/services/monitor/sbin/product.json --dryrun --printJSON 
Cloud Id     : 1
URL          : null
URL filepath : null
Filepath     : /services/monitor/sbin/jsonFile.json
Dry-run      : true
PrintJsonFile: true


Reading from file : /services/monitor/sbin/jsonFile.json

Will create server product [ m3.medium [3.75 GB memory/4 GB storage/3 Compute Units]/i386/Generic Unix/]
Will create server product [ m3.medium [3.75 GB memory/4 GB storage/3 Compute Units]/x86_64/Generic Unix/] 
Will create server product [ m3.medium [3.75 GB memory/4 GB storage/3 Compute Units]/i386/Generic Unix/] 
Will create server product [ m3.medium [3.75 GB memory/4 GB storage/3 Compute Units]/x86_64/Generic Unix/] 

Will update server product [7074/t1.micro/x86_64/Generic Unix/sa-east-1]  : Changes : {"standardPricingCurrency":{"new":0.027,"old":0.007}}
Will update server product [3020/m3.xlarge/x86_64/Windows/us-east-1]  : Changes : {"standardPricingCurrency":{"new":0.702,"old":0.78}}



Add count    : 4
Update count : 2

Created file 'vmproducts-custom.json'. Please copy this file to /services/dispatcher/resources/org/dasein/cloud/aws/ directory to update the drop down list of the server products in the server launch form.
```

