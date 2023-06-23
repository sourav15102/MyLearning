Role: they are temporarily assigned to users, groups or services.
policies: be it IAM ir bucket or any policy, they are for long term
STS: for outside users to get access temporarily.
So, for example if anyone wants to access resource in your account, you would give them STS credentials first and then assign the role.
presigned URL:
