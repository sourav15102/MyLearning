
***Q) Is there a link to filter out git repos?***
A) https://seart-ghs.si.usi.ch/

***Q) I accidently deleted some files, and committed to the remote branch? how to fix this?***
A) First of all figure out the commit hash with the latest version of file that got deleted, the version that you want to restore.
then run the following command:
```git
git checkout <commit hash> <path-to-file> 
```
it will restore the file to current directory in current branch, then we can add-commit-push to restore the damage.

***Q) I had committed to origin multiple times, created another remote, checked out hash and committed to another remote, why does everything went to new remote, all commits, why not just the one I checked out.***
A) 

***Q) I had .idea in my folder, now I have added that to .gitignore, now i want to delete it from git, deleting it actually is not a good idea, so, how to delete in git without deleting actually?***
```git
git rm filename.txt
```

***Q) How to see and set global and local username and email using configuration?***
A) print list
```git
git config --global --list
git config --local --list
```
set the username and email
```git
git config --global user.name "Sourav"
git config --global user.email "sghai@dal.ca"
//similarly for --local
```

