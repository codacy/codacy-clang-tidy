# codacy-clang-tidy

A standalone tool that converts [Clang-Tidy](https://clang.llvm.org/extra/clang-tidy/)
reports to Codacy's format. It allows the integration of Clang-Tidy into your Codacy workflow.

## Usage

TODO

## Building

##### Compile

`sbt compile`

##### Format

`sbt ";scalafmt;test:scalafmt;sbt:scalafmt"`

##### Tests

`sbt test`

##### Build native image (requires docker)

`sbt "graalvm-native-image:packageBin"`

##### Build fat-jar

`sbt assembly`

## What is Codacy?

[Codacy](https://www.codacy.com/) is an Automated Code Review Tool that monitors your technical debt, helps you improve your code quality, teaches best practices to your developers, and helps you save time in Code Reviews.

### Among Codacy’s features:

- Identify new Static Analysis issues
- Commit and Pull Request Analysis with GitHub, BitBucket/Stash, GitLab (and also direct git repositories)
- Auto-comments on Commits and Pull Requests
- Integrations with Slack, HipChat, Jira, YouTrack
- Track issues Code Style, Security, Error Proneness, Performance, Unused Code and other categories

Codacy also helps keep track of Code Coverage, Code Duplication, and Code Complexity.

Codacy supports PHP, Python, Ruby, Java, JavaScript, and Scala, among others.

### Free for Open Source

Codacy is free for Open Source projects.
