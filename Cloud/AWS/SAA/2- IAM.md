- Groups only contain users, not other groups
- Users don’t have to belong to a group, and user can belong to multiple groups

![[Pasted image 20230815004252.png]]

Account security
- can set password policies.
- MFA
	- virtual: google authen.., authy
		- authy supports multiple devices
		- authy support multiple token on single device
	- u2f: universal 2nd factor, (3rd party):
		- support for multiple root user and IAM users using single account.
	- hardware key fob, (3rd party)
	- hardware key fob for gov cloud, (3rd party)
- Access key ID and secret access key:
	- AWS management console: password+MFA
	- CLI: access key ID(usrname) +Secret access key(psswd)
	- SDK: access key ID(usrname) +Secret access key(psswd)
CLI:
- gives you direct access to public AWS apis of AWS services.
- built on AWS SDK for python BOTO
SDK:
- language specific APIs.

Security tools: summary
- credential report(account-level): users, credential status.
- access advisor(user-level): 
	- services a user was permitted to access
	- when they were last accessed.
	- this can be used to revise policies: Least Privilege principle


