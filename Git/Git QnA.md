
Q) Is there a link to filter out git repos?
A) https://seart-ghs.si.usi.ch/

Q) I accidently deleted some files, and committed to the remote branch? how to fix this?
A) First of all figure out the commit hash with the latest version of file that got deleted, the version that you want to restore.
then run the following command:
```git
git checkout <commit hash> <path-to-file> 
```
it will restore the file to current directory in current branch, then we can add-commit-push to restore the damage.


 
