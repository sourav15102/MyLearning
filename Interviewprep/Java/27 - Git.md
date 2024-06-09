#### Version Control
- it helps us manage codebase.
- allows devs to collaborate, keep track of each line of code.
- allow them to share, review, synchronize changes, revoke changes.
#### Git
It uses Distributed repository model, it is a distributed version control, DVCS.
CVCS:
- a central repo, no copies with clients, needs to download copy from center, change it and upload.
- subversion, perforce
- push/pull slower
- commits only online
DVCS:
- all brnahces, entire repo with each client, even if server go down, no issue.
- Git, Bazaar
- push/pull faster
- commits offlin too

> Git branch is independent line of developmnt, doesnt affect other memeber work.
   Merging is joining branches
   Trunk-based development is a branching model where most of the work takes place in a single trunk, usually called `trunk`, `master`, or `main`.
   GitFlow make use of branches very heavily, dev branch, feature branch etc.


##### **1. What is the difference between Git and SVN?**

|   |   |
|---|---|
|**Git**|**SVN**|
|Git is a Decentralized Version Control tool|SVN is a  Centralized Version Control tool|
|It belongs to the 3rd generation of Version Control tools|It belongs to the 2nd generation of Version Control tools|
|Clients can clone entire repositories on their local systems|Version history is stored on a server-side repository|
|Commits are possible even if offline|Only online commits are allowed|
|Push/pull operations are faster|Push/pull operations are slower|
|Works are shared automatically by commit|Nothing is shared automatically|

> [GitHub](https://bit.ly/2rVhL7Q) is a Git repository hosting service, plus it adds many of its own features. GitHub provides a Web-based graphical interface.

> Git uses C language.

> **_.git_** folder contains all the required files for tracking the project folder.

> Bare repo is something which only contains the content of .git folder and has no workspace.
> git clone --bare <>
> git init --bare

>git instaweb: **_permits me_** to **_browse_** my `local repositories` on a `localhost` **_via_** something called `GitWeb`.


Q: **Name a few Git commands and explain their usage.**
A:
          Below are some basic Git commands:

|   |   |
|---|---|
|Command|Function|
|`git rm [file]`|deletes the file from your working directory and stages the deletion.|
|`git log`|list the version history for the current branch.|
|`git show [commit]`|shows the metadata and content changes of the specified commit.|
|`git tag [commitID]`|used to give tags to the specified commit.|
|`git checkout [branch name]`<br><br>`git checkout -b [branch name]`|used to switch from one branch to another.<br><br>creates a new branch and also switches to it.|

Q: Local vs remote repo?
A:
A local repository is a copy of the entire project’s history and codebase that resides on a developer’s machine. It allows developers to have a private workflow.
Working dir --> staging area --> local repo
A remote repository serves as a central hub where developers can collaborate and share their work with others.

Q:  **How can you fix a broken commit?**
A:
In order to fix any broken commit, use the command “`git commit --amend`”. 

Q: What is the difference between git pull and git fetch?**

Git pull command pulls new changes or commits from a particular branch from your central repository and updates your target branch in your local repository.

When you perform a git fetch, it pulls all new commits from the desired branch and stores it in a new branch in your local repository. Your target branch will only be updated after merging the target branch and fetched branch. 
Git pull = git fetch + git merge

Q:  **What is ‘staging area’ or ‘index’ in Git?**
A:
an intermediate area known as ‘Staging Area’ or ‘Index’. 
### **![](https://d1jnx9ba8s6j9r.cloudfront.net/blog/wp-content/uploads/2016/12/download.png)**

Q: git commit -a
A:
This option stands for "all." When you use `git commit -a`, Git automatically stages (adds) all the changes to tracked files that have been modified or deleted. It effectively combines the "git add" and "git commit" steps into one.
It's important to note that `git commit -a` doesn't work for untracked files (files that haven't been added to the repository yet)

Q: git revert vs reset
A:
Revert:
```
git revert <bad commit hash>
```
it is just another commit which makes it like the bad commit never happened.

Reset
```
git reset HEAD
```
get back to a specific commit while discarding all the commits made after that one.
it not only changes the head, it also changes the branch refs.
it has
1. --soft
2. --mixed
3. --hard

```
- A - B - C (master)
```

`HEAD`points to `C` and the index matches `C`.

When we run `git reset --soft B`, `master` (and thus `HEAD`) now points to `B`, but the index still has the changes from `C`; `git status` will show them as staged. So if we run `git commit` at this point, we'll get a new commit with the same changes as `C`.

---

Okay, so starting from here again:

```
- A - B - C (master)
```

Now let's do `git reset --mixed B`. (Note: `--mixed` is the default option). Once again, `master` and `HEAD` point to B, but this time the index is also modified to match `B`. If we run `git commit` at this point, nothing will happen since the index matches `HEAD`. We still have the changes in the working directory, but since they're not in the index, `git status` shows them as unstaged. To commit them, you would `git add` and then commit as usual.

---

And finally, `--hard` is the same as `--mixed` (it changes your `HEAD` and index), except that `--hard` also modifies your working directory. If we're at `C` and run `git reset --hard B`, then the changes added in `C`, as well as any uncommitted changes you have, will be removed, and the files in your working copy will match commit `B`. Since you can permanently lose changes this way, you should always run `git status` before doing a hard reset to make sure your working directory is clean or that you're okay with losing your uncommitted changes.

Checkout:
```
git checkout <hash>
```
It only changes the HEAD, getting detached head(A git _detached HEAD_ state occurs when you are not on a branch but directly on a specific commit)

Restore:
On the one hand, the command can be used to undo the effects of `git add`, the `restore` command can also be used to discard local changes in a file, thereby restoring its last committed state.
By default, `git restore` will discard local changes, make it same as staged if present otherwise last committed. 
With the `--staged` option, the file will removed from the Staging Area - but its actual modifications will remain untouched.

Stash:
The `git stash` command takes your uncommitted changes (both staged and unstaged), saves them away for later use. Stashes are not transferred to the server when you push.
`git stash pop`, removes changes from stash and apply to working space.
`git stash apply`, doesnt remove changes and apply to working space.
But it will **not** stash:
- new files in your working copy that have not yet been staged
- files that have been ignored.

Rebase:
![Git tutorial: Git rebase](https://wac-cdn.atlassian.com/dam/jcr:4e576671-1b7f-43db-afb5-cf8db8df8e4a/01%20What%20is%20git%20rebase.svg?cdnVersion=1227)

From a content perspective, rebasing is changing the base of your branch from one commit to another making it appear as if you'd created your branch from a different commit. Internally, Git accomplishes this by creating new commits.
The primary reason for rebasing is to maintain a linear project history. For example, consider a situation where the main branch has progressed since you started working on a feature branch. You want to get the latest updates to the main branch in your feature branch, but you want to keep your branch's history clean so it appears as if you've been working off the latest main branch.
```xml
git rebase <base>
```
This automatically rebases the current branch onto `＜base＞`.

| Aspect           | Merging                                      | Rebasing                                   |
|------------------|----------------------------------------------|--------------------------------------------|
| Operation        | Non-destructive, adds a merge commit         | Rewrites history, creates new commits      |
| Commit History   | May lead to a cluttered commit history       | Tends to produce a cleaner, linear history |
| Use Cases        | Suitable for feature branches with periodic merges | Preferred for maintaining a clean history |

![[squash.jpeg]]

Squashing is the act of consolidating multiple commits into a single commit in git.
```
git checkout main
git merge --squash feature-branch
git commit -m "Merged feature-branch with main"
git push origin main
```

git rebase --interactive:
allows you to modify, reorder, or combine commits in a branch before incorporating them into another branch
- `git rebase -i HEAD~n`
- Git will open a text editor with a list of commits. Each commit has options like `pick`, `edit`, `squash`, `fixup`, and more.

git merge : fastforward
- It happens when you are merging feature branch, it has all its commits directly ahead of the main.
- Git simply moves the pointer of the main branch to the tip of the feature branch, effectively incorporating the changes without a merge commit.
`git merge feature-branch`
If Git detects that a fast forward merge is possible, it will execute it automatically.
`git merge --ff-only feature-branch`
Git to only perform the merge if it can be done as a fast forward, and if not, it will abort the merge.

Hooks:
scripts that run automatically every time a particular event occurs in a Git repository.
Common use cases for Git hooks include encouraging a commit policy, altering the project environment depending on the state of the repository, and implementing continuous integration workflows. But, since scripts are infinitely customizable, you can use Git hooks to automate or optimize virtually any aspect of your development workflow.
can reside in either local or server-side repositories
reside in the `.git/hooks` directory of every Git repository.
`.sample` extension prevents them from executing by default. To “install” a hook, all you have to do is remove the `.sample` extension.

cherry pick:
cherry picks commits from a branch and add to HEAD.
Use: 
- commit is accidently made to the wrong branch. You can switch to the correct branch and cherry-pick the commit to where it should belong.
- another team needs a commit from a diff team.
- hotfix: dev working on feature branch, make a commit for bug fix and cherry picks commit to main.

```css
    a - b - c - d   Main
         \
           e - f - g Feature
```

```undefined
git cherry-pick f
```

```css
    a - b - c - d - f   Main
         \
           e - f - g Feature
```