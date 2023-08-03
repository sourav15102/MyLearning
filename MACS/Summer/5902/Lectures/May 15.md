
S3 multipart uplaod:
- if >100 MB do multipart

Mib vs MB:
- mebibyte: uses exact 1024
- Megabyte: uses 1000

S3 acceleration:
1. cloudfront
2. optimized network protocol.

> one of security features of S3: presigned URLs:
> give access to users for an bucket for some time


EC2:
- storage to store instance data:
	- instance store
	- ebs

EBS vs AMI:
only way to store data in ec2 instance store is to reboot the instance.

Instance-backed  vs ebs backed AMIs

> accelerated compute optimized === GPU

>JeOS: just enough OS


EC2 Lifycycle:
terminate: removed
stopped/stop-hibernate: can be started again, still charged, very small fee.


>after modifying AMIs, when we create our own AMI from running instance, there can be two type: EBS backed, instance store backed





Q:
1. amazon serevices | aws services
2. services | resources
3. presigned urls
4. linux command: cp | scp
5. does snowfamily works for all storage devices
6. how to restrict data in/out of a particular country
7. S3 fuse: where we can mount s3 bucket on ec2?

Tasks:
1. aws academy, name change