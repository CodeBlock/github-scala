# github-scala

A GitHub APIv3 client for Scala.

# Usage

Right now, the library can't do much, and you should see the
ScalaDoc for how to use it extensively.

The gist of it, however, is:

```scala
val gh = new GitHub("username", "password")
val auth = gh.acquireToken().token // This is your OAuth token
```

# Hacking

To hack on github-scala, or more specifically, to run the tests,
you'll need to set `GITHUB_USERNAME` and `GITHUB_PASSWORD` in your ENV.

One way to do this without storing your GitHub password in .bash_history is:

```shell
$ export GITHUB_USERNAME=[myusername]
$ read -ersp 'Password: ' GITHUB_PASSWORD
Password: (type...type...type...)
export GITHUB_PASSWORD
```

We assume that you have a working SBT environment.

### Running the tests

Simply run `sbt test` to run the tests, once your environment variables are
set up as above.

# License

github-scala is released under a 3-clause BSD license, which is the same as
Scala itself.
